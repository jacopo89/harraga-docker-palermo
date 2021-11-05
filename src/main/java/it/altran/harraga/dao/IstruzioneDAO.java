package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.istruzione.Istruzione;
import it.altran.harraga.model.istruzione.IstruzioneItaliaConculso;
import it.altran.harraga.model.istruzione.IstruzioneItaliainCorso;
import it.altran.harraga.model.istruzione.IstruzioneOrigine;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class IstruzioneDAO {

	private static final String PATH_ISTRUZIONE = "istruzione";

	private static final boolean JSON = true;

	private static IstruzioneDAO instance;

	private IstruzioneDAO() {
	}

	public static IstruzioneDAO getInstance() {
		if (instance == null) {
			instance = new IstruzioneDAO();
		}
		return instance;
	}

	public Istruzione getIstruzioneBySocialCardId(Long socialCardId, Long timestamp) {
		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT IST_DES_JSON FROM HAR_ISTRUZIONE WHERE IST_COD_ID = :socialCardId AND IST_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Istruzione istr = gson.fromJson(res.toString(), Istruzione.class);
					return istr;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT IST_DES_JSON FROM HAR_ISTRUZIONE WHERE IST_COD_ID = :socialCardId AND IST_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Istruzione istr = gson.fromJson(res.toString(), Istruzione.class);
					return istr;
				}

			}

			// Commit the transaction
			// session.getTransaction().commit();

			session.close();
			return null;
		}
		if (Utils.DUMMY) {
			return DummySocialCardDB.getIstruzioneById(socialCardId);
		}

		return null;

	}

	public Istruzione updateIstruzione(Long socialCardId, Istruzione istruzione, User user) {

		if (istruzione.getIstruzioneOrigine() != null) {
			for (IstruzioneOrigine item : istruzione.getIstruzioneOrigine()) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getAllegato(), PATH_ISTRUZIONE);
			}
		}
		if (istruzione.getIstruzioneItaliainCorso() != null) {
			for (IstruzioneItaliainCorso item : istruzione.getIstruzioneItaliainCorso()) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getPianoCPIA(), PATH_ISTRUZIONE);
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getPattoCPIA(), PATH_ISTRUZIONE);
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getProgettoFormativo(), PATH_ISTRUZIONE);
			}
		}
		if (istruzione.getIstruzioneItaliaConculsi() != null) {
			for (IstruzioneItaliaConculso item : istruzione.getIstruzioneItaliaConculsi()) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getAllegato(), PATH_ISTRUZIONE);
			}
		}
		
		if(JSON) {
			
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();
			// start transaction
			session.beginTransaction();

			Query queryUp = session.createSQLQuery("update HAR_ISTRUZIONE set IST_DES_TYPE = :type  where IST_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(istruzione);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_ISTRUZIONE" + "(IST_COD_ID, IST_NUM_TIMESTAMP, IST_DES_USERNAME, IST_DES_TYPE, IST_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return istruzione;
			
			
		}
		if (Utils.DUMMY) {
			return DummySocialCardDB.updateIstruzione(socialCardId, istruzione);
		}

		return null;
	}

	public List<SectionHistory> getIstruzioneHistBySocialCardId(Long socialCardId) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT IST_COD_ID, IST_NUM_TIMESTAMP, IST_DES_USERNAME FROM HAR_ISTRUZIONE WHERE IST_COD_ID = :socialCardId ";//AND IST_DES_TYPE = :type";
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
