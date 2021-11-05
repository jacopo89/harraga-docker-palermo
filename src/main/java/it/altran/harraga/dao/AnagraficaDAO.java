package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.Contatto;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.anagrafica.*;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class AnagraficaDAO {

	private static final String PATH_ANAGRAFICA = "anagrafica";

	private static final boolean JSON = true;

	private static AnagraficaDAO instance;

	private AnagraficaDAO() {
	}

	public static AnagraficaDAO getInstance() {
		if (instance == null) {
			instance = new AnagraficaDAO();
		}
		return instance;
	}

	public Anagrafica getAnagraficaBySocialCardId(Long socialCardId, Long timestamp) {

		
		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE ANA_COD_ID = :socialCardId AND ANA_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Anagrafica anag = gson.fromJson(res.toString(), Anagrafica.class);
					return anag;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE ANA_COD_ID = :socialCardId AND ANA_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Anagrafica anag  = gson.fromJson(res.toString(), Anagrafica.class);
					return anag;
				}

			}

			// Commit the transaction
//			session.getTransaction().commit();

			session.close();
			return null;
		}
		if (Utils.DUMMY) {
			return DummySocialCardDB.getAnagraficaById(socialCardId);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Anagrafica where ANA_COD_ID = :id");
		query.setParameter("id", socialCardId);
		List<Anagrafica> anagList = query.list();

		if (anagList != null && anagList.size() > 0) {

			Anagrafica anagraf = anagList.get(0);

			anagraf.setMediatore(SocialCardDAO.getInstance().getContatto(anagraf.getIdMediatore()));

			Query queryDom = session.createQuery("from Domicilio where ANA_COD_ID = :id");
			queryDom.setParameter("id", anagraf.getId());
			anagraf.setDomicilio(queryDom.list());

			// Query queryDocumenti = session.createQuery("from Documento where ANA_COD_ID = :id");
			// queryDocumenti.setParameter("id", anagraf.getId());
			// anagraf.setDocumenti(queryDocumenti.list());
			anagraf.setDocumenti(getDocumenti(session, anagraf.getId()));

			anagraf.setTutore(getTutore(session, anagraf.getId()));

			anagraf.setAssistenteSociale(SocialCardDAO.getInstance().getContatto(anagraf.getIdAssSoc()));

			anagraf.setAssicurazione(getAssicurazione(session, anagraf.getId()));

			/*
			 * Query queryAssicurazione = session.createQuery("from Assicurazione where ANA_COD_ID = :id"); queryAssicurazione.setParameter("id", anagraf.getId()); List<Assicurazione> assicList =
			 * queryAssicurazione.list(); if(assicList!=null && assicList.size()>0) {
			 * 
			 * Query queryAllegato = session.createQuery("from Allegato where ALL_COD_ID = :id"); queryAllegato.setParameter("id", assicList.get(0).getIdAllegato()); List<Allegato> allList = queryAllegato.list();
			 * if(allList!=null && allList.size()>0) assicList.get(0).setAllegato(allList.get(0));
			 * 
			 * anagraf.setAssicurazione(assicList.get(0)); }
			 */
			session.close();
			return anagraf;
		}

		// System.out.println(anagList.toString());
		session.close();
		return null;
		// return DummySocialDardDB.getAnagraficaById(socialCardId);
	}

	private List<Documento> getDocumenti(SharedSessionContract session, Long idAnagrafica) {
		Query queryDocumenti = session.createQuery("from Documento where ANA_COD_ID = :id");
		queryDocumenti.setParameter("id", idAnagrafica);
		List<Documento> docList = queryDocumenti.list();
		for (Documento item : docList) {
			item.setAllegato(SocialCardDAO.getInstance().getAllegato(item.getIdAllegato()));
		}
		return docList;
	}

	private Assicurazione getAssicurazione(SharedSessionContract session, Long idAnagrafica) {
		Query queryAssicurazione = session.createQuery("from Assicurazione where ANA_COD_ID = :id");
		queryAssicurazione.setParameter("id", idAnagrafica);
		List<Assicurazione> assicList = queryAssicurazione.list();
		if (assicList != null && assicList.size() > 0) {

			assicList.get(0).setAllegato(SocialCardDAO.getInstance().getAllegato(assicList.get(0).getIdAllegato()));
			// Query queryAllegato = session.createQuery("from Allegato where ALL_COD_ID = :id");
			// queryAllegato.setParameter("id", assicList.get(0).getIdAllegato());
			// List<Allegato> allList = queryAllegato.list();
			// if(allList!=null && allList.size()>0)
			// assicList.get(0).setAllegato(allList.get(0));
			//
			return assicList.get(0);
			// anagraf.setAssicurazione(assicList.get(0));
		}
		return null;
	}

	private Tutore getTutore(SharedSessionContract session, Long idAnagrafica) {
		Query query = session.createQuery("from Tutore where ANA_COD_ID = :id");
		query.setParameter("id", idAnagrafica);
		List<Tutore> tutList = query.list();
		if (tutList != null && tutList.size() > 0) {
			tutList.get(0).setContatto(SocialCardDAO.getInstance().getContatto(tutList.get(0).getIdContatto()));
			tutList.get(0).setDecretoTribunale(SocialCardDAO.getInstance().getAllegato(tutList.get(0).getIdAllegato()));
			return tutList.get(0);
		}
		return null;
	}

	public Anagrafica updateAnagrafica(Long socialCardId, Anagrafica anagrafica, User user) {

		if (JSON) {
			
			if (anagrafica.getAssicurazione() != null) {
				// Allegato allAss = anagrafica.getAssicurazione().SocialCardDAO.getInstance().getAllegato();
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, anagrafica.getAssicurazione().getAllegato(), PATH_ANAGRAFICA);
			}

			if (anagrafica.getDocumentiAllArrivo() != null) {
				// Allegato allAss = anagrafica.getAssicurazione().SocialCardDAO.getInstance().getAllegato();
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, anagrafica.getDocumentiAllArrivo().getAllegato(), PATH_ANAGRAFICA);
			}

			if (anagrafica.getTutore() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, anagrafica.getTutore().getDecretoTribunale(), PATH_ANAGRAFICA);
			}

			if (anagrafica.getDocumenti() != null) {
				for (Documento documento : anagrafica.getDocumenti()) {
					if (documento != null && documento.getAllegato() != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, documento.getAllegato(), PATH_ANAGRAFICA);
					}
				}

			}
			
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			// desideri.setTimestamp(System.currentTimeMillis());
			// desideri.setTYPE("C");

			Query queryUp = session.createSQLQuery("update HAR_ANAGRAFICA set ANA_DES_TYPE = :type" + " where ANA_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(anagrafica);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_ANAGRAFICA " + "(ANA_COD_ID, ANA_NUM_TIMESTAMP, ANA_DES_USERNAME, ANA_DES_TYPE, ANA_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return anagrafica;
		}
		
		
		
		
		if (Utils.DUMMY) {
			// SALVATAGGIO ALLEGATI

			if (anagrafica.getAssicurazione() != null) {
				// Allegato allAss = anagrafica.getAssicurazione().SocialCardDAO.getInstance().getAllegato();
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, anagrafica.getAssicurazione().getAllegato(), PATH_ANAGRAFICA);
			}
			if (anagrafica.getTutore() != null) {
				SocialCardDAO.getInstance().salvaAllegato(socialCardId, anagrafica.getTutore().getDecretoTribunale(), PATH_ANAGRAFICA);
			}

			if (anagrafica.getDocumenti() != null) {
				for (Documento documento : anagrafica.getDocumenti()) {
					if (documento != null && documento.getAllegato() != null) {
						SocialCardDAO.getInstance().salvaAllegato(socialCardId, documento.getAllegato(), PATH_ANAGRAFICA);
					}
				}

			}
			return DummySocialCardDB.updateAnagrafica(socialCardId, anagrafica);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		if (anagrafica.getMediatore() != null) {
			session.saveOrUpdate(anagrafica.getMediatore());
			anagrafica.setIdMediatore(anagrafica.getMediatore().getId());
		}

		if (anagrafica.getAssistenteSociale() != null) {
			session.saveOrUpdate(anagrafica.getAssistenteSociale());
			anagrafica.setIdAssSoc(anagrafica.getAssistenteSociale().getId());
		}

		if (anagrafica.getTutore() != null) {
			anagrafica.getTutore().setIdAnagrafica(anagrafica.getId());
			updateTutore(anagrafica.getTutore());
		}

		if (anagrafica.getAssicurazione() != null) {
			anagrafica.getAssicurazione().setIdAnagrafica(anagrafica.getId());

			if (anagrafica.getAssicurazione().getAllegato() != null) {
				Long idAssic = SocialCardDAO.getInstance().updateAllegato(anagrafica.getAssicurazione().getAllegato(), anagrafica.getId(), PATH_ANAGRAFICA);
				anagrafica.getAssicurazione().setIdAllegato(idAssic);
				// session.saveOrUpdate(anagrafica.getAssicurazione().getAllegato());
				// anagrafica.getAssicurazione().setIdAllegato(anagrafica.getAssicurazione().getAllegato().getId());
			}

			session.saveOrUpdate(anagrafica.getAssicurazione());
		}

		updateDomicili(anagrafica);

		updateDocumenti(anagrafica);

		session.saveOrUpdate(anagrafica);

		// Commit the transaction
		session.getTransaction().commit();

		return null;

		// return DummySocialDardDB.updateAnagrafica(socialCardId, anagrafica);

	}

	private void updateDocumenti(Anagrafica anagrafica) {

		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		ArrayList<Long> docIdList = new ArrayList<Long>();

		for (Documento docItem : anagrafica.getDocumenti()) {

			docItem.setIdAnagrafica(anagrafica.getId());

			if (docItem.getAllegato() != null) {
				// session.save(docItem.getAllegato());
				Long idAll = SocialCardDAO.getInstance().updateAllegato(docItem.getAllegato(), anagrafica.getId(), PATH_ANAGRAFICA);
				docItem.setIdAllegato(idAll);
			}

			session.saveOrUpdate(docItem);

			docIdList.add(docItem.getId());

		}

		session.getTransaction().commit();

		// RECUPERO LISTA DI DOCUMENTI DA ELIMINARE
		String hql = "from Documento where ANA_COD_ID = :id";
		if (docIdList.size() > 0) {
			hql = "from Documento where ANA_COD_ID = :id AND DOC_COD_ID not in (:docIdList)";
		}
		Query queryDocumenti = session.createQuery(hql);
		queryDocumenti.setParameter("id", anagrafica.getId());
		if (docIdList.size() > 0) {
			queryDocumenti.setParameterList("docIdList", docIdList);
		}

		List<Documento> deleteList = queryDocumenti.list();
		for (Documento deleteItem : deleteList) {

			deleteDocumento(deleteItem);
		}

	}

	private void deleteDocumento(Documento documento) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		// ELIMINO IL FILE...
		SocialCardDAO.getInstance().deleteFileAllegato(documento.getIdAllegato(), documento.getIdAnagrafica());

		// session.delete(deleteItem);

		session.delete(documento);

		session.getTransaction().commit();

	}

	private void updateDomicili(Anagrafica anagrafica) {

		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		ArrayList<Long> domIdList = new ArrayList<Long>();

		for (Domicilio domItem : anagrafica.getDomicilio()) {
			domItem.setIdAnagrafica(anagrafica.getId());
			session.saveOrUpdate(domItem);
			domIdList.add(domItem.getId());
		}

		// session.getTransaction().commit();

		String hql = "delete from Domicilio where ANA_COD_ID= :idAnag";
		if (domIdList.size() > 0) {
			hql = "delete from Domicilio where ANA_COD_ID= :idAnag AND DOM_COD_ID not in (:domIdList)";
		}
		Query q = session.createQuery(hql);
		q.setParameter("idAnag", anagrafica.getId());
		if (domIdList.size() > 0) {
			q.setParameterList("domIdList", domIdList);
		}
		q.executeUpdate();

		session.getTransaction().commit();
		// TODO DELETE OLD DOMICILI

	}

	private Long updateContatto(Contatto contatto) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		session.saveOrUpdate(contatto);

		session.getTransaction().commit();

		return contatto.getId();

	}

	private void updateTutore(Tutore tutore) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		if (tutore.getDecretoTribunale() != null) {
			// session.saveOrUpdate(tutore.getDecretoTribunale());
			Long idDecreto = SocialCardDAO.getInstance().updateAllegato(tutore.getDecretoTribunale(), tutore.getIdAnagrafica(), PATH_ANAGRAFICA);
			tutore.setIdAllegato(idDecreto);
		}

		if (tutore.getContatto() != null) {
			// if (tutore.getIdContatto() != null && tutore.getIdContatto() > 0)
			// session.merge(tutore.getContatto());
			// tutore.setIdContatto(tutore.getContatto().getId());
			Long idContatto = updateContatto(tutore.getContatto());
			tutore.setIdContatto(idContatto);
		}

		session.saveOrUpdate(tutore);

		session.getTransaction().commit();

	}

	
	
	public List<SectionHistory> getAnagraficaHistBySocialCardId(Long socialCardId) {

			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			String sql = "SELECT ANA_COD_ID, ANA_NUM_TIMESTAMP, ANA_DES_USERNAME FROM HAR_ANAGRAFICA WHERE ANA_COD_ID = :socialCardId";// AND ANA_DES_TYPE = :type";
			SQLQuery query = session.createSQLQuery(sql);
//			query.setParameter("type", "H");
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
