package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.sociale.Esperienza;
import it.altran.harraga.model.sociale.Sociale;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SocialeDAO {

	private static final String PATH_SOCIALE = "sociale";

	private static final boolean JSON = true;
	
	private static SocialeDAO instance;

	private SocialeDAO() {
	}

	public static SocialeDAO getInstance() {
		if (instance == null) {
			instance = new SocialeDAO();
		}
		return instance;
	}

	public Sociale getSocialeBySocialCardId(Long socialCardId, Long timestamp) {
		
		
		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT SOC_DES_JSON FROM HAR_SOCIALE WHERE SOC_COD_ID = :socialCardId AND SOC_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Sociale soc = gson.fromJson(res.toString(), Sociale.class);
					return soc;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT SOC_DES_JSON FROM HAR_SOCIALE WHERE SOC_COD_ID = :socialCardId AND SOC_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Sociale soc  = gson.fromJson(res.toString(), Sociale.class);
					return soc;
				}

			}
		}
			
		
		if(Utils.DUMMY) {
			return DummySocialCardDB.getSocialeById(socialCardId);
		}
		
		return null;
		
	}

	
	public Sociale updateSociale(Long socialCardId , Sociale sociale, User user) {
			
			if(sociale.getVolontariato()!=null) {
				for( Esperienza volont : sociale.getVolontariato()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, volont.getCertificazione(), PATH_SOCIALE);
				}
			}
			
			if(sociale.getLaboratori()!=null) {
				for( Esperienza item : sociale.getLaboratori()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getCertificazione(), PATH_SOCIALE);
				}
			}
			
			if(sociale.getSport()!=null) {
				for( Esperienza item : sociale.getSport()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getCertificazione(), PATH_SOCIALE);
				}
			}
			
			if(sociale.getGruppiAss()!=null) {
				for( Esperienza item : sociale.getGruppiAss()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, item.getCertificazione(), PATH_SOCIALE);
				}
			}

			
			if(JSON) {
				// get hibernate session from the servlet context
				SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

				Session session = sessionFactory.openSession();
				// start transaction
				session.beginTransaction();

				Query queryUp = session.createSQLQuery("update HAR_SOCIALE set SOC_DES_TYPE = :type  where SOC_COD_ID = :socialCardId");
				queryUp.setParameter("socialCardId", socialCardId);
				queryUp.setParameter("type", "H");
				int result = queryUp.executeUpdate();

				Gson gson = new Gson();
				String jsonValue = gson.toJson(sociale);

				Query query = session
						.createSQLQuery("INSERT INTO HAR_SOCIALE" + "(SOC_COD_ID, SOC_NUM_TIMESTAMP, SOC_DES_USERNAME, SOC_DES_TYPE, SOC_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
				query.setParameter("socialCardId", socialCardId);
				query.setParameter("timestamp", System.currentTimeMillis());
				query.setParameter("username", user.getUsername());
				query.setParameter("type", "C");
				query.setParameter("json", jsonValue);

				query.executeUpdate();

				// Commit the transaction
				session.getTransaction().commit();

				session.close();

				return sociale;
			}	
			
			
		if(Utils.DUMMY) {
			
			return DummySocialCardDB.updateSociale( socialCardId, sociale);
		}
		
		return null;
	}

	public List<SectionHistory> getSocialeHistBySocialCardId(Long socialCardId) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT SOC_COD_ID, SOC_NUM_TIMESTAMP, SOC_DES_USERNAME FROM HAR_SOCIALE WHERE SOC_COD_ID = :socialCardId ";// AND SOC_DES_TYPE = :type";
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
