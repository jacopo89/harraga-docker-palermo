package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.PEI;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.storia.*;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class StoriaDAO {

	private static final String PATH_STORIA = "storia";

	private static final boolean JSON = true;

	private static StoriaDAO instance;

	private StoriaDAO() {
	}

	public static StoriaDAO getInstance() {
		if (instance == null) {
			instance = new StoriaDAO();
		}
		return instance;
	}

	public Storia getStoriaBySocialCardId(Long socialCardId, Long timestamp) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();
			SQLQuery query = null;

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO
				String sql = "SELECT STO_DES_JSON FROM HAR_STORIA WHERE STO_COD_ID = :socialCardId AND STO_DES_TYPE = :type";
				query = session.createSQLQuery(sql);
				query.setParameter("type", "C");

			} else {
				// RECUPERA VALORE in base al timestamp
				String sql = "SELECT STO_DES_JSON FROM HAR_STORIA WHERE STO_COD_ID = :socialCardId AND STO_NUM_TIMESTAMP = :timestamp";
				query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
			}
			query.setParameter("socialCardId", socialCardId);
			List results = query.list();
			if (results != null && results.size() > 0) {
				Object res = results.get(0);
				Gson gson = new Gson();
				Storia san = gson.fromJson(res.toString(), Storia.class);
				return san;
			}

			session.close();
			return null;
		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.getStoriaById(socialCardId);
		}

		return null;

	}

	public Storia updateStoria(Long socialCardId, Storia sto, User user) {

		// SALVATAGGIO ALLEGATI

		if (sto.getFamiliari() != null) {
			for (Familiare familiare : sto.getFamiliari()) {
				if (familiare != null && familiare.getAllegato() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, familiare.getAllegato(), PATH_STORIA);
				}
			}
		}
		if (sto.getPercorsoMigratorio() != null &&  sto.getPercorsoMigratorio().getPaesiAttraversati()!=null) {
			for (PaeseAttraversato paese : sto.getPercorsoMigratorio().getPaesiAttraversati()) {
				if (paese != null && paese.getVisto() != null) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, paese.getVisto(), PATH_STORIA);
				}
			}
		}

		Accoglienza acco = sto.getAccoglienza();
		if (acco != null) {

	

			if (acco.getPei() != null) {
				for (PEI peiItem : acco.getPei()) {
					if (peiItem != null && peiItem.getAllegato() != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, peiItem.getAllegato(), PATH_STORIA);
					}
				}
			}

			if (acco.getAllontanamentiRitrovamenti() != null) {
				for (AllontanamentiRitrovamenti allRit : acco.getAllontanamentiRitrovamenti()) {
					if (allRit != null && allRit.getComunicazione() != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, allRit.getComunicazione(), PATH_STORIA);
					}
				}
			}
			if (acco.getAdozioni() != null) {
				for (Adozione adozione : acco.getAdozioni()) {
					if (adozione != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, adozione.getAllegatoAdozione(), PATH_STORIA);
					}
				}
			}
			if (acco.getAffidi() != null) {
				for (Affido affido : acco.getAffidi()) {
					if (affido != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, affido.getAllegatoAffido(), PATH_STORIA);
					}
				}
			}
			if (acco.getRelazioniAssistenteSociale()!= null) {
				for (RelazioneAssistenteSociale relAssSoc : acco.getRelazioniAssistenteSociale() ) {
					if (relAssSoc != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, relAssSoc.getAllegato(), PATH_STORIA);
					}
				}
			}
			
		}

		if (JSON) {

			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();
			// start transaction
			session.beginTransaction();

			Query queryUp = session.createSQLQuery("update HAR_STORIA set STO_DES_TYPE = :type  where STO_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(sto);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_STORIA" + "(STO_COD_ID, STO_NUM_TIMESTAMP, STO_DES_USERNAME, STO_DES_TYPE, STO_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return sto;

		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.updateStoria(socialCardId, sto);
		}

		return null;
	}

	public List<SectionHistory> getStoriaHistBySocialCardId(Long socialCardId) {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT STO_COD_ID, STO_NUM_TIMESTAMP, STO_DES_USERNAME FROM HAR_STORIA WHERE STO_COD_ID = :socialCardId";// AND STO_DES_TYPE = :type";
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
