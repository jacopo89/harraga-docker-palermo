package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.sanitaria.EventoMedico;
import it.altran.harraga.model.sanitaria.PatologiaAllergica;
import it.altran.harraga.model.sanitaria.Sanitaria;
import it.altran.harraga.model.sanitaria.SpecificaDisabilita;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SanitariaDAO {

	private static final String PATH_SANITARIA = "sanitaria";

	private static final boolean JSON = true;

	private static SanitariaDAO instance;

	private SanitariaDAO() {
	}

	public static SanitariaDAO getInstance() {
		if (instance == null) {
			instance = new SanitariaDAO();
		}
		return instance;
	}

	public Sanitaria getSanitariaBySocialCardId(Long socialCardId, Long timestamp) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();
			SQLQuery query = null;

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO
				String sql = "SELECT SAN_DES_JSON FROM HAR_SANITARIA WHERE SAN_COD_ID = :socialCardId AND SAN_DES_TYPE = :type";
				query = session.createSQLQuery(sql);
				query.setParameter("type", "C");

			} else {
				// RECUPERA VALORE in base al timestamp
				String sql = "SELECT SAN_DES_JSON FROM HAR_SANITARIA WHERE SAN_COD_ID = :socialCardId AND SAN_NUM_TIMESTAMP = :timestamp";
				query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
			}
			query.setParameter("socialCardId", socialCardId);
			List results = query.list();
			if (results != null && results.size() > 0) {
				Object res = results.get(0);
				Gson gson = new Gson();
				Sanitaria san = gson.fromJson(res.toString(), Sanitaria.class);
				return san;
			}

			session.close();
			return null;
		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.getSanitariaById(socialCardId);
		}

		return null;

	}

	public Sanitaria updateSanitaria(Long socialCardId, Sanitaria sanitaria, User user) {
		if (sanitaria.getPatologieAllergiche() != null) {
			for (PatologiaAllergica pat : sanitaria.getPatologieAllergiche()) {
				if (pat != null && pat.getAllegato() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, pat.getAllegato(), PATH_SANITARIA);
				}
			}
		}
		if (sanitaria.getSpecificheDisabilita() != null) {
			for (SpecificaDisabilita dis : sanitaria.getSpecificheDisabilita()) {
				if (dis != null && dis.getAllegato() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, dis.getAllegato(), PATH_SANITARIA);
				}
			}
		}

		if (sanitaria.getMedicoCurante() != null && sanitaria.getMedicoCurante().getAllegato() != null) {
			SocialCardDAO.getInstance().salvaAllegato(socialCardId, sanitaria.getMedicoCurante().getAllegato(), PATH_SANITARIA);
		}

		if (sanitaria.getPresoInCarico() != null && sanitaria.getPresoInCarico().getAllegato() != null) {
			SocialCardDAO.getInstance().salvaAllegato(socialCardId, sanitaria.getPresoInCarico().getAllegato(), PATH_SANITARIA);
		}

		if (sanitaria.getEventiMedici() != null) {
			for (EventoMedico evento : sanitaria.getEventiMedici()) {
				if (evento != null && evento.getAllegato() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, evento.getAllegato(), PATH_SANITARIA);
				}
			}
		}

		if (JSON) {

			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();
			// start transaction
			session.beginTransaction();

			Query queryUp = session.createSQLQuery("update HAR_SANITARIA set SAN_DES_TYPE = :type  where SAN_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(sanitaria);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_SANITARIA" + "(SAN_COD_ID, SAN_NUM_TIMESTAMP, SAN_DES_USERNAME, SAN_DES_TYPE, SAN_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return sanitaria;

		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.updateSanitaria(socialCardId, sanitaria);
		}

		return null;

	}

	public List<SectionHistory> getSanitariaHistBySocialCardId(Long socialCardId) {
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT SAN_COD_ID, SAN_NUM_TIMESTAMP, SAN_DES_USERNAME FROM HAR_SANITARIA WHERE SAN_COD_ID = :socialCardId";// AND SAN_DES_TYPE = :type";
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
