package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.penale.Penale;
import it.altran.harraga.model.penale.ProcedimentoPenale;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PenaleDAO {

	private static final String PATH_PENALE = "penale";

	private static final boolean JSON = true;

	private static PenaleDAO instance;

	private PenaleDAO() {
	}

	public static PenaleDAO getInstance() {
		if (instance == null) {
			instance = new PenaleDAO();
		}
		return instance;
	}

	public Penale getPenaleBySocialCardId(Long socialCardId, Long timestamp) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();
			SQLQuery query = null;

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO
				String sql = "SELECT PEN_DES_JSON FROM HAR_PENALE WHERE PEN_COD_ID = :socialCardId AND PEN_DES_TYPE = :type";
				query = session.createSQLQuery(sql);
				query.setParameter("type", "C");

			} else {
				// RECUPERA VALORE in base al timestamp
				String sql = "SELECT PEN_DES_JSON FROM HAR_PENALE WHERE PEN_COD_ID = :socialCardId AND PEN_NUM_TIMESTAMP = :timestamp";
				query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
			}
			query.setParameter("socialCardId", socialCardId);
			List results = query.list();
			if (results != null && results.size() > 0) {
				Object res = results.get(0);
				Gson gson = new Gson();
				Penale pen = gson.fromJson(res.toString(), Penale.class);
				return pen;
			}

			session.close();
			return null;
		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.getPenaleById(socialCardId);
		}

		return null;

	}

	public Penale updatePenale(Long socialCardId, Penale penale, User user) {
		
		
		if (penale.getProcedimentiPenali()  != null) {
			for (ProcedimentoPenale procedimento : penale.getProcedimentiPenali()) {
				if (procedimento.getPei() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, procedimento.getPei().getAllegato(), PATH_PENALE);
				}
			}
		}
		

		if (JSON) {

			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();
			// start transaction
			session.beginTransaction();

			Query queryUp = session.createSQLQuery("update HAR_PENALE set PEN_DES_TYPE = :type  where PEN_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(penale);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_PENALE" + "(PEN_COD_ID, PEN_NUM_TIMESTAMP, PEN_DES_USERNAME, PEN_DES_TYPE, PEN_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return penale;

		}

		if (Utils.DUMMY) {

			return DummySocialCardDB.updatePenale(socialCardId, penale);
		}

		return null;
	}

	public List<SectionHistory> getPenaleHistBySocialCardId(Long socialCardId) {
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT PEN_COD_ID, PEN_NUM_TIMESTAMP, PEN_DES_USERNAME FROM HAR_PENALE WHERE PEN_COD_ID = :socialCardId";// AND PEN_DES_TYPE = :type";
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
