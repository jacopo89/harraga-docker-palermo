package it.altran.harraga.dao;

import it.altran.harraga.model.User;
import it.altran.harraga.utils.Utils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO {

	private static UserDAO instance;

	private HashMap<String, User> loginMap = new HashMap<>(); // TOKEN - USERNAME

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public User performLogin(User loginData) {

		// if(Utils.DUMMY)
		// return DummyLoginDAO.performLogin(loginData);

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createQuery("from User where USR_DES_USERNAME = :username");
		query.setParameter("username", loginData.getUsername());
		List<User> userList = query.list();
		session.close();

		if (userList != null && userList.size() > 0) {
			User usr = userList.get(0);
			String encPassword = Utils.MD5(loginData.getPassword());
			if (usr.getPassword().equals(encPassword)) {

				String authToken = Utils.getRandomToken();
				usr.setElencoCartelleAssociate(getSocialCardAssociate(usr.getUsername()));
				usr.setToken(authToken);
				loginMap.put(authToken, usr);
				return usr;
			}

		}

		return null;

	}

	public boolean checkAuth(String authToken) {

		// if(Utils.DUMMY) {
		// return DummyLoginDAO.checkAuth(authToken);
		// }

		if (loginMap.containsKey(authToken)) {
			return true;
		}
		return false;

	}

	public ArrayList<User> getUserList() {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createQuery("from User");
		ArrayList<User> userList = (ArrayList<User>) query.list();
		session.close();
		return userList;

		// return DummyLoginDAO.getUserList();

	}

	public boolean createUser(User user) {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// TODO inserire in social card e recuperare il nuovo id in DB
		String sql = "SELECT MAX(USR_COD_ID) FROM HAR_USER";
		SQLQuery queryMax = session.createSQLQuery(sql);
		Integer max = (Integer) queryMax.uniqueResult();
		long newId = max+1;

		user.setId(newId);
		user.setMatricola("0");

		String encPass = Utils.MD5(user.getPassword());
		user.setPassword(encPass);

		// start transaction
		session.beginTransaction();

		session.save(user);

		session.getTransaction().commit();
		session.flush();
		session.close();

		return true;
		// return DummyLoginDAO.updateUser(user);

	}

	public boolean updateUser(User user) {

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		User existingUser = (User) session.get(User.class, user.getId());
		existingUser.setNome(user.getNome());
		existingUser.setCognome(user.getCognome());
		existingUser.setEmail(user.getEmail());
		existingUser.setMatricola(user.getMatricola());
		existingUser.setPostiDisponibili(user.getPostiDisponibili());
		existingUser.setStato(user.getStato());
		existingUser.setTelefono(user.getTelefono());
		existingUser.setType(user.getType());

		if (user.getPassword() != null && !user.getPassword().equals("")) {
			String encPassword = Utils.MD5(user.getPassword());
			existingUser.setPassword(encPassword);
		}

		session.update(existingUser);

		session.getTransaction().commit();
		session.flush();
		session.close();

		return true;
		// return DummyLoginDAO.updateUser(user);

	}

	public User getUser(String userName) {

		// if(Utils.DUMMY)
		// return DummyLoginDAO.getUser(userName);

		// get hibernate session from the servlet context
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createQuery("from User where USR_DES_USERNAME = :username");
		query.setParameter("username", userName);
		List<User> userList = query.list();
		session.close();

		if (userList != null && userList.size() > 0) {
			User usr = userList.get(0);
			usr.setElencoCartelleAssociate(getSocialCardAssociate(usr.getUsername()));
			return usr;
		}
		return null;

	}

	private ArrayList<Integer> getSocialCardAssociate(String username){
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createSQLQuery("select CAR_NUM_CARDID from HAR_CARTASS where USR_DES_USERNAME = :username");
		query.setParameter("username", username);
		List<Integer> idList = query.list();
		session.close();

		return (ArrayList<Integer>) idList;

	}

	public User getUserByToken(String authToken) {
		// if(Utils.DUMMY)
		// return DummyLoginDAO.getUserByToken(authToken);

		User usr = loginMap.get(authToken);
		return usr;
	}

	public boolean associaUser(String username, int socialCardId) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createSQLQuery("INSERT INTO HAR_CARTASS (USR_DES_USERNAME, CAR_NUM_CARDID) VALUES (:username, :socialCardId)");
		query.setParameter("username", username);
		query.setParameter("socialCardId", socialCardId);
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();


		return true;
//		if(Utils.DUMMY)
//			return DummyLoginDAO.associaUser(username, socialCardId);
	}

	public boolean deassociaUser(String username, int socialCardId) {
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		// start transaction
		session.beginTransaction();

		Query query = session.createSQLQuery("DELETE FROM HAR_CARTASS WHERE  CAR_NUM_CARDID = :socialCardId ");//AND USR_DES_USERNAME = :username
//		query.setParameter("username", username);
		query.setParameter("socialCardId", socialCardId);
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();


		return true;
//		if(Utils.DUMMY)
//		return DummyLoginDAO.deassociaUser(username, socialCardId);
	}

}
