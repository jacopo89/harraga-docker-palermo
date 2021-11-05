package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.competenze.CompetenzaBase;
import it.altran.harraga.model.competenze.CompetenzaDigitale;
import it.altran.harraga.model.competenze.Competenze;
import it.altran.harraga.model.competenze.Patente;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CompetenzeDAO {

	private static final String PATH_COMPETENZE = "competenze";

	private static final boolean JSON = true;
	
	private static CompetenzeDAO instance;

	private CompetenzeDAO() {
	}

	public static CompetenzeDAO getInstance() {
		if (instance == null) {
			instance = new CompetenzeDAO();
		}
		return instance;
	}

	public Competenze getCompetenzeBySocialCardId(Long socialCardId,Long timestamp) {
		
		
		
		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT COM_DES_JSON FROM HAR_COMPETENZE WHERE COM_COD_ID = :socialCardId AND COM_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Competenze comp = gson.fromJson(res.toString(), Competenze.class);
					return comp;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT COM_DES_JSON FROM HAR_COMPETENZE WHERE COM_COD_ID = :socialCardId AND COM_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Competenze comp  = gson.fromJson(res.toString(), Competenze.class);
					return comp;
				}

			}

			// Commit the transaction
//			session.getTransaction().commit();

			session.close();
			return null;
		}
		
		
		
		
		if(Utils.DUMMY) {
			return DummySocialCardDB.getCompetenzeById(socialCardId);
		}
		
		return null;
		
	}

	
	public Competenze updateCompetenze(Long socialCardId , Competenze comp, User user) {
			
			if(comp.getCompetenzeDigitali()!=null) {
				for (CompetenzaDigitale digComp : comp.getCompetenzeDigitali()) {
					if(digComp!=null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, digComp.getCertificazione(), PATH_COMPETENZE);
					}
					
				}
			}
			
			if(comp.getAltreCompetenze()!=null) {
				for (  CompetenzaBase compItem : comp.getAltreCompetenze()) {
					if(compItem!=null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, compItem.getCertificazione(), PATH_COMPETENZE);
					}
					
				}
			}
			
			if(comp.getPatenti()!=null) {
				for (   Patente patente : comp.getPatenti()) {
					if(patente!=null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, patente.getAllegato(), PATH_COMPETENZE);
					}
					
				}
			}

			
			if(JSON) {
				// get hibernate session from the servlet context
				SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

				Session session = sessionFactory.openSession();
				// start transaction
				session.beginTransaction();

				Query queryUp = session.createSQLQuery("update HAR_COMPETENZE set COM_DES_TYPE = :type  where COM_COD_ID = :socialCardId");
				queryUp.setParameter("socialCardId", socialCardId);
				queryUp.setParameter("type", "H");
				int result = queryUp.executeUpdate();

				Gson gson = new Gson();
				String jsonValue = gson.toJson(comp);

				Query query = session
						.createSQLQuery("INSERT INTO HAR_COMPETENZE" + "(COM_COD_ID, COM_NUM_TIMESTAMP, COM_DES_USERNAME, COM_DES_TYPE, COM_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
				query.setParameter("socialCardId", socialCardId);
				query.setParameter("timestamp", System.currentTimeMillis());
				query.setParameter("username", user.getUsername());
				query.setParameter("type", "C");
				query.setParameter("json", jsonValue);

				query.executeUpdate();

				// Commit the transaction
				session.getTransaction().commit();

				session.close();

				return comp;
			}	
			
			
		if(Utils.DUMMY) {
			return DummySocialCardDB.updateCompetenze( socialCardId, comp);
		}
		
		return null;
	}

	public List<SectionHistory> getCompetenzeHistBySocialCardId(Long socialCardId) {
		// get hibernate session from the servlet context
					SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

					Session session = sessionFactory.openSession();

					// start transaction
					session.beginTransaction();

					String sql = "SELECT COM_COD_ID, COM_NUM_TIMESTAMP, COM_DES_USERNAME FROM HAR_COMPETENZE WHERE COM_COD_ID = :socialCardId";// AND COM_DES_TYPE = :type";
					SQLQuery query = session.createSQLQuery(sql);
//					query.setParameter("type", "H");
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
