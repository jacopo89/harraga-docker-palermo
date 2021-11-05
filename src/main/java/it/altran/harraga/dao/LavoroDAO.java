package it.altran.harraga.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.lavoro.Certificazione;
import it.altran.harraga.model.lavoro.Lavoro;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LavoroDAO {

	private static final String PATH_LAVORO = "lavoro";

	private static final boolean JSON = true;

	private static LavoroDAO instance;

	private LavoroDAO() {
	}

	public static LavoroDAO getInstance() {
		if (instance == null) {
			instance = new LavoroDAO();
		}
		return instance;
	}

	public List<Lavoro> getLavoroBySocialCardId(Long socialCardId, Long timestamp) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();
			SQLQuery query= null;

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO
				String sql = "SELECT LAV_DES_JSON FROM HAR_LAVORO WHERE LAV_COD_ID = :socialCardId AND LAV_DES_TYPE = :type";
				query = session.createSQLQuery(sql);
				query.setParameter("type", "C");

			} else {
				// RECUPERA VALORE in base al timestamp
				String sql = "SELECT LAV_DES_JSON FROM HAR_LAVORO WHERE LAV_COD_ID = :socialCardId AND LAV_NUM_TIMESTAMP = :timestamp";
				query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
			}
			query.setParameter("socialCardId", socialCardId);
			List results = query.list();
			if (results != null && results.size() > 0) {
				Object res = results.get(0);
				Gson gson = new Gson();
				List<Lavoro> lavor = gson.fromJson(res.toString(), new TypeToken<List<Lavoro>>(){}.getType());
				return lavor;
			}
			
			session.close();
			return null;
		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.getLavoroById(socialCardId);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Lavoro where ANA_COD_ID = :id");
		query.setParameter("id", socialCardId);
		List<Lavoro> workList = query.list();

		if (workList != null && workList.size() > 0) {
			for (Lavoro lavoro : workList) {
				List<Allegato> certificazioni = getCertificazioni(lavoro.getId());
				// TODO lavoro.setCertificazioni((ArrayList<Allegato>) certificazioni);

			}
			session.close();
			return workList;
		}

		session.close();
		return null;
	}

	private List<Allegato> getCertificazioni(Long lavId) {

		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// TODO recuperare la lista degli id dalla cross table e poi gli allegati;
		// Query query = session.createQuery("from Lavoro where ANA_COD_ID IN (SELECT ALL_COD_ID FROM har_lavxall WHERE LAV_COD_ID = :idLav)");
		// query.setParameter("id", lavId);
		// List<Allegato> allList = query.list();
		Query query = session.createSQLQuery("SELECT ALL_COD_ID FROM HAR_LAVXALL WHERE LAV_COD_ID = :idLav");
		query.setParameter("idLav", lavId);
		List<Long> allIdList = query.list();

		List<Allegato> allList = new ArrayList<Allegato>();
		if (allIdList != null && allIdList.size() > 0) {
			Query queryAllegati = session.createQuery("from Allegato where ALL_COD_ID IN (:idsAll)");
			queryAllegati.setParameterList("idsAll", allIdList);
			allList = queryAllegati.list();
		}

		return allList;
	}

	public ArrayList<Lavoro> updateLavoro(Long socialCardId, ArrayList<Lavoro> lavoro, User user) {

		for (Lavoro lavoroItem : lavoro) {
			if (lavoroItem.getCertificazioni() != null) {
				for (Certificazione certItem : lavoroItem.getCertificazioni()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, certItem.getAllegato(), PATH_LAVORO);
				}
			}
		}

		if (JSON) {

			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();
			// start transaction
			session.beginTransaction();

			Query queryUp = session.createSQLQuery("update HAR_LAVORO set LAV_DES_TYPE = :type  where LAV_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(lavoro);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_LAVORO" + "(LAV_COD_ID, LAV_NUM_TIMESTAMP, LAV_DES_USERNAME, LAV_DES_TYPE, LAV_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return lavoro;

		}

		if (Utils.DUMMY) {

			return DummySocialCardDB.updateLavoro(socialCardId, lavoro);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		ArrayList<Long> lavIdList = new ArrayList<Long>();

		for (Lavoro lavItem : lavoro) {
			lavItem.setIdAnagrafica(socialCardId);
			session.saveOrUpdate(lavItem);
			lavIdList.add(lavItem.getId());
			// TODO updateCertificazioni(lavItem.getCertificazioni());
		}

		// Commit the transaction
		session.getTransaction().commit();

		// RECUPERO LISTA DI DOCUMENTI DA ELIMINARE
		String hql = "from Lavoro where ANA_COD_ID = :id";
		if (lavIdList.size() > 0) {
			hql = "from Lavoro where ANA_COD_ID = :id AND LAV_COD_ID not in (:lavIdList)";
		}
		Query q = session.createQuery(hql);
		q.setParameter("id", socialCardId);
		if (lavIdList.size() > 0) {
			q.setParameterList("lavIdList", lavIdList);
		}

		List<Lavoro> deleteList = q.list();
		for (Lavoro deleteItem : deleteList) {
			deleteLavoro(deleteItem);
		}
		return null;
	}

	private void deleteLavoro(Lavoro deleteItem) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		// TODO eliminare le certificazioni allegate

		// session.delete(deleteItem);

		session.delete(deleteItem);

		session.getTransaction().commit();

	}

	private void updateCertificazioni(ArrayList<Allegato> certificazioni) {
		System.out.println(certificazioni.toString());

	}

	public List<SectionHistory> getLavoroHistBySocialCardId(Long socialCardId) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT LAV_COD_ID, LAV_NUM_TIMESTAMP, LAV_DES_USERNAME FROM HAR_LAVORO WHERE LAV_COD_ID = :socialCardId";//AND LAV_DES_TYPE = :type";
		SQLQuery query = session.createSQLQuery(sql);
//		query.setParameter("type", "H");
		query.setParameter("socialCardId", socialCardId);
		List<Object[]> secHistList = query.list();

		final List<SectionHistory> historyList = new ArrayList<SectionHistory>();

		for (int i = 0; i < secHistList.size(); i++) {
			Object[] obj = secHistList.get(i);
			SectionHistory sh = new SectionHistory();
			sh.setSocialCardId(Long.valueOf(obj[0].toString()));
			sh.setTimestamp(Long.valueOf(obj[1].toString()));
			sh.setUsername(obj[2].toString());
			historyList.add(sh);

		}
		session.close();
		return historyList;
	}

}
