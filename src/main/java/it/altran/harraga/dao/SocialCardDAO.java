package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;
import it.altran.harraga.model.SocialCard;
import it.altran.harraga.model.User;
import it.altran.harraga.model.amministrativa.Amministrativa;
import it.altran.harraga.model.anagrafica.Anagrafica;
import it.altran.harraga.model.competenze.Competenze;
import it.altran.harraga.model.desideri.Desideri;
import it.altran.harraga.model.istruzione.Istruzione;
import it.altran.harraga.model.lavoro.Lavoro;
import it.altran.harraga.model.penale.Penale;
import it.altran.harraga.model.sanitaria.Sanitaria;
import it.altran.harraga.model.sociale.Sociale;
import it.altran.harraga.model.storia.Storia;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class SocialCardDAO {

	private static final String BASE_PATH_SOCIAL_CARD = File.separator + "docs" + File.separator + "social-card" + File.separator;
	private static SocialCardDAO instance;
    private final static Logger LOGGER = Logger.getLogger(SocialCardDAO.class.getName());

	private static final boolean JSON = true;

	private SocialCardDAO() {
	}

	public static SocialCardDAO getInstance() {
		if (instance == null) {
			instance = new SocialCardDAO();
		}
		return instance;
	}

	public ArrayList<SocialCard> getAllSocialCard() {


		if(JSON) {
			ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();
			// get hibernate session from the servlet context
//			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);
//
//			Session session = sessionFactory.openSession();


			SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE ANA_DES_TYPE = :type";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("type", "C");
			List results = query.list();
			//System.out.print(results.toString());
			if (results != null && results.size() > 0) {
				for (int i = 0; i < results.size(); i++) {
					Object res = results.get(i);
					Gson gson = new Gson();
					Anagrafica anag = gson.fromJson(res.toString(), Anagrafica.class);
					socialCardList.add(new SocialCard(anag.getId(),anag));
				}

			}
			return socialCardList;
		}


		if (Utils.DUMMY) {
			return DummySocialCardDB.getAllSocialCard();
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Anagrafica");
		// query.setParameter("id", socialCardId);
		List<Anagrafica> anagList = query.list();

		ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();

		for (Anagrafica anagItem : anagList) {
			socialCardList.add(new SocialCard(anagItem.getId(), anagItem));
		}

		return socialCardList;

		// return DummySocialDardDB.getAllUser();
	}

	public static SocialCard getSocialCardById(int userId) {
		// TODO access to DB to retrieve user by id

		SocialCard user = DummySocialCardDB.getSocialCardById(userId);

		return user;
	}

	public SocialCard createSocialCard(SocialCard socialCard, User user) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();


			//TODO inserire in social card e recuperare il nuovo id in DB
			String sql = "SELECT MAX(ANA_COD_ID) FROM HAR_ANAGRAFICA";
			SQLQuery queryMax = session.createSQLQuery(sql);
			Integer maxId = (Integer) queryMax.uniqueResult();
			long newId = 1;
			if(maxId!=null)
				newId = maxId+1;

			socialCard.getAnagrafica().setId(newId);

			Gson gson = new Gson();
			String jsonAnagrafica = gson.toJson(socialCard.getAnagrafica());

			Query query = session
					.createSQLQuery("INSERT INTO HAR_ANAGRAFICA  (ANA_COD_ID, ANA_NUM_TIMESTAMP, ANA_DES_USERNAME,ANA_DES_TYPE,ANA_DES_JSON,ANA_NUM_TUTELA, ANA_ITALIANO)  VALUES (:socialCardId, :timestamp, :username, :type, :json,:numeroTutela, :italiano)");
			query.setParameter("socialCardId", newId);


			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername() );
			query.setParameter("type", "C");
			query.setParameter("json", jsonAnagrafica);
			query.setParameter("numeroTutela", socialCard.getAnagrafica().getNumeroTutela());
			query.setParameter("italiano", socialCard.getAnagrafica().getItaliano());
			//query.setParameter("dataNascitaCorretta", socialCard.getAnagrafica().getDataNascitaCorretta());

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return socialCard;

		}

		if (Utils.DUMMY)

		{
			return DummySocialCardDB.createSocialCard(socialCard);
		}

		return null;
		/*
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Long id = (Long) session.save(socialCard.getAnagrafica());
		socialCard.getAnagrafica().setId(id);

		// Commit the transaction
		session.getTransaction().commit();

		return socialCard;
		*/

	}


	public boolean importSocialCardList(ArrayList<Anagrafica> anagraficaList, User user) {
		if(anagraficaList!=null && anagraficaList.size()>0) {
			for(Anagrafica anagrafica : anagraficaList) {

				createSocialCard(new SocialCard(null, anagrafica) , user);
			}
			return true;
		}
		return false;
	}

	public Contatto getContatto(Long idContatto) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Contatto where CON_COD_ID = :id");
		query.setParameter("id", idContatto);
		List<Contatto> cList = query.list();
		if (cList != null && cList.size() > 0)
			return cList.get(0);
		return null;
	}

	public Allegato getAllegato(Long idAllegato) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		Query queryAllegato = session.createQuery("from Allegato where ALL_COD_ID = :id");
		queryAllegato.setParameter("id", idAllegato);
		List<Allegato> allList = queryAllegato.list();
		if (allList != null && allList.size() > 0)
			return allList.get(0);

		return null;
	}

	public Long updateAllegato(Allegato allegato, Long socialCardId, String sectionName) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		salvaAllegato(socialCardId, allegato, sectionName);

		session.saveOrUpdate(allegato);

		session.getTransaction().commit();

		return allegato.getId();

	}

	public void salvaAllegato(Long socialCardId, Allegato allegato, String sectionName) {
		ServletContext context = ServletActionContext.getServletContext();
		String basePath = context.getRealPath(BASE_PATH_SOCIAL_CARD);

		// salvo solo effettivamente il file e inviato (non in caso di update di altre sezioni)
		if (allegato != null && allegato.getEncodedFile() != null && !allegato.getEncodedFile().trim().equals("")) {
			//String destinationPath = basePath + File.separator + socialCardId + File.separator + sectionName + File.separator + allegato.getNome();

			String fileString = allegato.getEncodedFile();

			// TODO GESTIRE I CASI IN CUI IL FILA GI ESISTE usando IL NUOVO NOME FILE
			// RESTITUITO DA SAVEFILE
//			String link = sectionName + File.separator + allegato.getNome();
			String link = Utils.saveFile(fileString, socialCardId, allegato.getNome(), sectionName, basePath);
			allegato.setLink(link);
			allegato.setEncodedFile("");
		}
	}

	public void deleteFileAllegato(Long idAll, Long socialCardId) {
		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Allegato allegato = getAllegato(idAll);

		if (allegato != null) {
			SocialCardDAO.getInstance().eliminaAllegato(socialCardId, allegato.getLink());
			session.delete(allegato);
		}

		session.getTransaction().commit();

	}

	private void eliminaAllegato(Long socialCardId, String link) {
		ServletContext context = ServletActionContext.getServletContext();
		String basePath = context.getRealPath(BASE_PATH_SOCIAL_CARD);

		String tempPath = basePath + File.separator + socialCardId + File.separator + link;

		// String path = context.getRealPath(tempPath);
		Utils.deleteFile(Paths.get(tempPath));

	}

	private void eliminaCartella(Long socialCardId) {
		ServletContext context = ServletActionContext.getServletContext();
		String basePath = context.getRealPath(BASE_PATH_SOCIAL_CARD);

		String tempPath = basePath + File.separator + socialCardId + File.separator;

		// String path = context.getRealPath(tempPath);
		Utils.deleteFolder(tempPath);

	}

	public void deleteSocialCardById(Long socialCardId) {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

//		String hql = "delete from Anagrafica where ANA_COD_ID= :idAnag";
//		Query q = session.createQuery(hql);
//		q.setParameter("idAnag", socialCardId);
		String sql = "DELETE FROM HAR_ANAGRAFICA WHERE ANA_COD_ID= :idAnag";
		Query q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_AMMINISTRATIVA WHERE AMM_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();


		sql = "DELETE FROM HAR_SANITARIA WHERE SAN_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_ISTRUZIONE WHERE IST_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_LAVORO WHERE LAV_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_PENALE WHERE PEN_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_COMPETENZE WHERE COM_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_SOCIALE WHERE SOC_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_DESIDERI WHERE DES_COD_ID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		sql = "DELETE FROM HAR_CARTASS WHERE CAR_NUM_CARDID= :idAnag";
		q = session.createSQLQuery(sql);
		q.setParameter("idAnag", socialCardId);
		q.executeUpdate();

		// Commit the transaction
		session.getTransaction().commit();

		// elimino intero folder
		eliminaCartella(socialCardId);

		// return DummySocialDardDB.deleteUserById(userId);

	}

	public ArrayList<SocialCard> getSocialCardListByUser(User user) {

		ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();

		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE ANA_DES_TYPE = :type AND ANA_COD_ID IN (:socialCardIdList)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("type", "C");
		query.setParameterList("socialCardIdList", user.getElencoCartelleAssociate());

		List results = query.list();

		if (results != null && results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				Object res = results.get(i);
				Gson gson = new Gson();
				Anagrafica anag = gson.fromJson(res.toString(), Anagrafica.class);
				socialCardList.add(new SocialCard(anag.getId(),anag));
			}

		}
		return socialCardList;
//		if(Utils.DUMMY)
//			return DummySocialCardDB.getSocialCardListByUser(user);
	}
// JACOPO : INSERITO METODO PER TROVARE LISTA DI ITALIANI
public ArrayList<SocialCard> GetMigrantiSocialCards() {


		if(JSON) {
			ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();
			// get hibernate session from the servlet context
//			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);
//
//			Session session = sessionFactory.openSession();


			SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE ((ANA_DES_TYPE = 'C' OR ANA_DES_TYPE IS NULL) && ANA_ITALIANO = 'N' ) " ;

			SQLQuery query = session.createSQLQuery(sql);
            List<Object[]> results = (List<Object[]>) query.list();
            System.out.println("La dimensione della lista e' "+ results.size());

			if (results != null && results.size() > 0) {
				for (int i = 0; i < results.size(); i++) {
					Object res = results.get(i);
					Gson gson = new Gson();
					Anagrafica anag = gson.fromJson(res.toString(), Anagrafica.class);
					socialCardList.add(new SocialCard(anag.getId(),anag));
				}
			}

			return socialCardList;
		}


		if (Utils.DUMMY) {
			return DummySocialCardDB.getAllSocialCard();
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Anagrafica");
		// query.setParameter("id", socialCardId);
		List<Anagrafica> anagList = query.list();

		ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();

		for (Anagrafica anagItem : anagList) {
			socialCardList.add(new SocialCard(anagItem.getId(), anagItem));
		}

		return socialCardList;

		// return DummySocialDardDB.getAllUser();
	}
//JACOPO : INSERITO METODO PER TROVARE LISTA DI MAGGIORENNI >18
public ArrayList<SocialCard> getMaggiorenniSocialCards() {


	if(JSON) {
		ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();
		// get hibernate session from the servlet context
//		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);
//
//		Session session = sessionFactory.openSession();


		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		String sql = "SELECT ANA_DES_JSON FROM HAR_ANAGRAFICA WHERE (ANA_DES_TYPE = :type AND ANA_DATA_NASCITA_LONG < :dataNascitaCorretta)" ;
		SQLQuery query = session.createSQLQuery(sql);

		Date today = new Date();
		Calendar calToday = Calendar.getInstance();
		calToday.setTime(today);
		calToday.set(Calendar.HOUR, 0);
		calToday.set(Calendar.AM_PM, Calendar.AM);
		calToday.set(Calendar.MINUTE, 0);
		calToday.set(Calendar.SECOND, 0);
		calToday.set(Calendar.MILLISECOND, 0);
		Date dateRepresentation = calToday.getTime();
		System.out.println(dateRepresentation.toString());

		Calendar cal18 = Calendar.getInstance();
		cal18 = (Calendar) calToday.clone();
		cal18.set(Calendar.YEAR, calToday.get(Calendar.YEAR)-18);
		Date dateRepresentation2 = cal18.getTime();
		System.out.println(dateRepresentation2.toString());
		long limit = cal18.getTimeInMillis();
		System.out.println("La data in millisecondi e' "+limit);


		query.setParameter("type", "C");
		query.setParameter("dataNascitaCorretta", limit);
		//query.setParameter("italiano", "S");
		List results = query.list();
		if (results != null && results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				Object res = results.get(i);
				Gson gson = new Gson();
				System.out.print(res.toString());
				Anagrafica anag = gson.fromJson(res.toString(), Anagrafica.class);
				socialCardList.add(new SocialCard(anag.getId(),anag));
			}

		}
		return socialCardList;
	}


	if (Utils.DUMMY) {
		return DummySocialCardDB.getAllSocialCard();
	}

	// get hibernate session from the servlet context
	SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

	Session session = sessionFactory.openSession();
	Query query = session.createQuery("from Anagrafica");
	// query.setParameter("id", socialCardId);
	List<Anagrafica> anagList = query.list();

	ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();

	for (Anagrafica anagItem : anagList) {
		socialCardList.add(new SocialCard(anagItem.getId(), anagItem));
	}

	return socialCardList;

	// return DummySocialDardDB.getAllUser();
}

	// public static Storia getStoriaById(int socialCardId) {
	// // TODO Auto-generated method stub
	// return DummySocialDardDB.getStoriaById(socialCardId);
	// }
	//
	//
	// public static Competenze getCompetenzeBySocialCardId(int socialCardId) {
	// // TODO Auto-generated method stub
	// return DummySocialDardDB.getCompetenzeById(socialCardId);
	// }
	//
	// public static Istruzione getIstruzioneById(int socialCardId) {
	// // TODO Auto-generated method stub
	// return DummySocialDardDB.getIstruzioneById(socialCardId);
	// }
	//
	// public static Sociale getSocialeBySocialCardId(int socialCardId) {
	// // TODO Auto-generated method stub
	// return DummySocialDardDB.getSocialeById(socialCardId);
	// }
	//
	// public static Penale getPenaleBySocialCardId(int socialCardId) {
	// // TODO Auto-generated method stub
	// return DummySocialDardDB.getPenaleById(socialCardId);
	// }

	// public static ArrayList<Lavoro> updateLavoro(Long socialCardId ,ArrayList<Lavoro> lavoro) {
	// return DummySocialDardDB.updateLavoro( socialCardId, lavoro);
	// }

	// public static Penale updatePenale(int socialCardId, Penale penale) {
	// return DummySocialDardDB.updatePenale( socialCardId, penale);
	// }
	//
	// public static Storia updateStoria(Long socialCardId, Storia storia) {
	// return DummySocialDardDB.updateStoria( socialCardId, storia);
	// }

}
