package it.altran.harraga.dao;

import com.google.gson.Gson;
import it.altran.harraga.listener.HibernateListener;
import it.altran.harraga.model.SectionHistory;
import it.altran.harraga.model.User;
import it.altran.harraga.model.desideri.Desideri;
import it.altran.harraga.utils.Utils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesideriDAO {

	private static final boolean JSON = true;

	private static DesideriDAO instance;

	private Map<String, Map<String, String>> desMap = new HashMap<>();

	private DesideriDAO() {
	}

	public static DesideriDAO getInstance() {
		if (instance == null) {
			instance = new DesideriDAO();
		}
		return instance;
	}

	public Desideri getDesideriBySocialCardId(Long socialCardId, Long timestamp) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			if (timestamp == null) {
				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT DES_DES_JSON FROM HAR_DESIDERI WHERE DES_COD_ID = :socialCardId AND DES_DES_TYPE = :type";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("type", "C");
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Desideri des = gson.fromJson(res.toString(), Desideri.class);
					return des;
				}

			} else {

				// RECUPERA VALORE AGGIORNATO

				String sql = "SELECT DES_DES_JSON FROM HAR_DESIDERI WHERE DES_COD_ID = :socialCardId AND DES_NUM_TIMESTAMP = :timestamp";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("timestamp", timestamp);
				query.setParameter("socialCardId", socialCardId);
				List results = query.list();
				if (results != null && results.size() > 0) {
					Object res = results.get(0);
					Gson gson = new Gson();
					Desideri des = gson.fromJson(res.toString(), Desideri.class);
					return des;
				}

			}

			// Commit the transaction
			session.getTransaction().commit();

			session.close();
			return null;
		}

		if (Utils.DUMMY) {
			if (timestamp == null)
				return DummySocialCardDB.getDesideriById(socialCardId);
			else
				return DummySocialCardDB.getDesideriById(socialCardId, timestamp);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Desideri where DES_COD_ID = :id");
		query.setParameter("id", socialCardId);
		List<Desideri> customerList = query.list();

		session.close();
		if (customerList != null && customerList.size() > 0) {
			// System.out.println(customerList.get(0).getAltreAspettative());
			return customerList.get(0);
		}

		// System.out.println(customerList.toString());

		return null;
		//
	}

	public List<SectionHistory> getDesideriHistBySocialCardId(Long socialCardId) {

		
		if (JSON) {
//			List<SectionHistory> secHistList= new ArrayList<SectionHistory>();
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			String sql = "SELECT DES_COD_ID, DES_NUM_TIMESTAMP, DES_DES_USERNAME FROM HAR_DESIDERI WHERE DES_COD_ID = :socialCardId";// AND DES_DES_TYPE = :type";
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
//			final List<SectionHistory> list = new LinkedList<>();
//			for(final Object o : query.list()) {
//			    list.add((SectionHistory)o);
//			}
			
//			ArrayList list = (ArrayList)query.list();
//			Iterator lt=list.iterator();
//			for(int i=0; i<list.size();i++){
//			//Userdao is a pojo class having setXXX() and getXXX() functions
//
//				SectionHistory qux = ( SectionHistory) lt.next(); 
//			System.out.println(qux.getTimestamp());
//			}
			
			return historyList;
			
		}
		return null;
	}

	public Desideri updateDesideri(Long socialCardId, Desideri desideri, User user) {

		if (JSON) {
			// get hibernate session from the servlet context
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

			Session session = sessionFactory.openSession();

			// start transaction
			session.beginTransaction();

			// desideri.setTimestamp(System.currentTimeMillis());
			// desideri.setTYPE("C");

			Query queryUp = session.createSQLQuery("update HAR_DESIDERI set DES_DES_TYPE = :type" + " where DES_COD_ID = :socialCardId");
			queryUp.setParameter("socialCardId", socialCardId);
			queryUp.setParameter("type", "H");
			int result = queryUp.executeUpdate();

			Gson gson = new Gson();
			String jsonValue = gson.toJson(desideri);

			Query query = session
					.createSQLQuery("INSERT INTO HAR_DESIDERI " + "(DES_COD_ID, DES_NUM_TIMESTAMP, DES_DES_USERNAME,DES_DES_TYPE,DES_DES_JSON) " + "VALUES (:socialCardId, :timestamp, :username, :type, :json )");
			query.setParameter("socialCardId", socialCardId);
			query.setParameter("timestamp", System.currentTimeMillis());
			query.setParameter("username", user.getUsername());
			query.setParameter("type", "C");
			query.setParameter("json", jsonValue);

			query.executeUpdate();

			// Commit the transaction
			session.getTransaction().commit();

			session.close();

			return null;
		}

		if (Utils.DUMMY) {
			return DummySocialCardDB.updateDesideri(socialCardId, desideri);
		}

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		session.saveOrUpdate(desideri);

		// Commit the transaction
		session.getTransaction().commit();

		return null;

	}

}
