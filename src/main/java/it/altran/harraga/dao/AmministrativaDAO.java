package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.amministrativa.*;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AmministrativaDAO {

	private static final String PATH_AMMINISTRARIVA = "amministrativa";

	private static final boolean JSON = true;

	private static AmministrativaDAO instance;

	private AmministrativaDAO() {
	}

	public static AmministrativaDAO getInstance() {
		if (instance == null) {
			instance = new AmministrativaDAO();
		}
		return instance;
	}

	public Amministrativa getAmministrativaBySocialCardId(Long socialCardId, Long timestamp, User user) {
		
		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT AMM_DES_JSON FROM HAR_AMMINISTRATIVA WHERE AMM_COD_ID = :socialCardId AND AMM_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Amministrativa anag = gson.fromJson(res.toString(), Amministrativa.class);
					return anag;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT AMM_DES_JSON FROM HAR_AMMINISTRATIVA WHERE AMM_COD_ID = :socialCardId AND AMM_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Amministrativa anag  = gson.fromJson(res.toString(), Amministrativa.class);
					return anag;
				}

			}

			// Commit the transaction
//			session.getTransaction().commit();

			session.close();
			return null;
		}
		
		
		
		if (Utils.DUMMY) {
			return DummySocialCardDB.getAmministrativaById(socialCardId);
		}

		return null;

	}

	public Amministrativa updateAmministrativa(Long socialCardId, Amministrativa amm, User user) {
		
			if (amm.getPermessoSoggiorno() != null) {
				for (PermessoSogiorno permesso : amm.getPermessoSoggiorno()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, permesso.getAllegato(), PATH_AMMINISTRARIVA);
				}
			}

		if (amm.getDocumentiIdentita() != null) {
			for (DocumentoIdentita documento : amm.getDocumentiIdentita()) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, documento.getAllegato(), PATH_AMMINISTRARIVA);
			}
		}

			if (amm.getProcedureLegali() != null) {
				for (ProceduraLegale procedura : amm.getProcedureLegali()) {
					if (procedura.getDublino() != null)
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, procedura.getDublino().getAllegato(), PATH_AMMINISTRARIVA);

					if (procedura.getAppuntamenti() != null) {
						for (Appuntamento appuntamento : procedura.getAppuntamenti()) {
							if (appuntamento.getAllegati() != null) {
								for (AllegatoAppuntamento allegatoApp : appuntamento.getAllegati()) {
									SocialCardDAO.getInstance().salvaAllegato(socialCardId, allegatoApp.getAllegato(), PATH_AMMINISTRARIVA);
								}
							}
						}
					}

					if (procedura.getRicorsiAmministrativi() != null) {
						for (RicorsoAmministrativo ricorso : procedura.getRicorsiAmministrativi()) {
							if (ricorso.getAllegati() != null) {
								for (AllegatoRicorso allegatoApp : ricorso.getAllegati()) {
									SocialCardDAO.getInstance().salvaAllegato(socialCardId, allegatoApp.getAllegato(), PATH_AMMINISTRARIVA);
								}
							}
						}
					}

				}

			}

			if (amm.getDelegaAmministrativa() != null && amm.getDelegaAmministrativa().getAllegati() != null) {
				for (AllegatoDelega allegatoDelega : amm.getDelegaAmministrativa().getAllegati()) {
					SocialCardDAO.getInstance().salvaAllegato(socialCardId, allegatoDelega.getAllegato(), PATH_AMMINISTRARIVA);
				}
			}

			if (amm.getRevocaTutela() != null && amm.getRevocaTutela().getProvvedimento() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getRevocaTutela().getProvvedimento(), PATH_AMMINISTRARIVA);
			}

			if (amm.getCodiceFiscale() != null && amm.getCodiceFiscale().getAllegato() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getCodiceFiscale().getAllegato(), PATH_AMMINISTRARIVA);
			}

			if (amm.getTesseraSanitaria() != null && amm.getTesseraSanitaria().getAllegato() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getTesseraSanitaria().getAllegato(), PATH_AMMINISTRARIVA);
			}
			if (amm.getStp() != null && amm.getStp().getAllegato() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getStp().getAllegato(), PATH_AMMINISTRARIVA);
			}

			if (amm.getFotoSegnalazione() != null && amm.getFotoSegnalazione().getAllegato() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getFotoSegnalazione().getAllegato(), PATH_AMMINISTRARIVA);
			}

			if (amm.getPattoAccoglienza() != null && amm.getPattoAccoglienza().getPatto() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getPattoAccoglienza().getPatto(), PATH_AMMINISTRARIVA);
			}

			if (amm.getProseguo21() != null && amm.getProseguo21().getProvvedimento() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getProseguo21().getProvvedimento(), PATH_AMMINISTRARIVA);

			}

			if (amm.getAffidoAmministrativo() != null && amm.getAffidoAmministrativo().getProvvedimentoAffidamento() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getAffidoAmministrativo().getProvvedimentoAffidamento(), PATH_AMMINISTRARIVA);

			}

			if (amm.getAffidoAmministrativo() != null && amm.getAffidoAmministrativo().getVerbaleAffidamento() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, amm.getAffidoAmministrativo().getVerbaleAffidamento(), PATH_AMMINISTRARIVA);

			}


			if(JSON) {
				// get hibernate session from the servlet context
				SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

				Session session = sessionFactory.openSession();
				// start transaction
				session.beginTransaction();

				Query queryUp = session.createSQLQuery("update HAR_AMMINISTRATIVA set AMM_DES_TYPE = :type  where AMM_COD_ID = :socialCardId");
				queryUp.setParameter("socialCardId", socialCardId);
				queryUp.setParameter("type", "H");
				int result = queryUp.executeUpdate();

				Gson gson = new Gson();
				String jsonValue = gson.toJson(amm);

				Query query = session
						.createSQLQuery("INSERT INTO HAR_AMMINISTRATIVA" + "(AMM_COD_ID, AMM_NUM_TIMESTAMP, AMM_DES_USERNAME, AMM_DES_TYPE, AMM_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
				query.setParameter("socialCardId", socialCardId);
				query.setParameter("timestamp", System.currentTimeMillis());
				query.setParameter("username", user.getUsername());
				query.setParameter("type", "C");
				query.setParameter("json", jsonValue);

				query.executeUpdate();

				// Commit the transaction
				session.getTransaction().commit();

				session.close();

				return amm;
			}
			
		if (Utils.DUMMY) {

			return DummySocialCardDB.updateAmministrativa(socialCardId, amm);
		}

		return null;
	}
	
	public List<SectionHistory> getAmministrativaHistBySocialCardId(Long socialCardId) {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT AMM_COD_ID, AMM_NUM_TIMESTAMP, AMM_DES_USERNAME FROM HAR_AMMINISTRATIVA WHERE AMM_COD_ID = :socialCardId";// AND AMM_DES_TYPE = :type";
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
		return historyList;
}

}
