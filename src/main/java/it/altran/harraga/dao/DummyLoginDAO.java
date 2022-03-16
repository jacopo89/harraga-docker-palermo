package it.altran.harraga.dao;

import it.altran.harraga.model.User;
import it.altran.harraga.utils.Utils;

import java.util.ArrayList;

public class DummyLoginDAO {

	private static boolean byPassLogin = true;

	static ArrayList<User> userList = DummyLoginDAO.init();

	private static ArrayList<User> init() {
		ArrayList<Integer> cartAss = new ArrayList<Integer>();
		cartAss.add(1);
		cartAss.add(2);
		cartAss.add(3);

		User user1 = new User("CPA", "CPA", null, 0, User.Ruolo.CPA, 1, " ", " ", " ", " ", "", null, 0);
		User user2 = new User("SecondaAccoglienza", "SecondaAccoglienza", null, 0, User.Ruolo.RESP_SEC_ACC, 1, " ", " ", " ", " ", "", null, 0);
		User user3 = new User("Tutore", "Tutore", null, 0, User.Ruolo.TUTORE, 1, " ", " ", " ", " ", "", null, 0);
		User user4 = new User("ComuneMarsala", "ComuneMarsala", null, 0, User.Ruolo.COMUNE_MARSALA, 1, " ", " ", " ", " ", "", null, 0);
		User user5 = new User("ReferenteLegale", "ReferenteLegale", null, 0, User.Ruolo.REF_LEGALE, 1, " ", " ", " ", " ", "", null, 0);
		User user6 = new User("ASP", "ASP", null, 0, User.Ruolo.ASP, 0, "nomeA", " ", " ", " ", "", null, 0);
		User user7 = new User("CPIA", "CPIA", null, 0, User.Ruolo.CPIA, 2, " ", " ", " ", " ", "", null, 0);
		User user8 = new User("AgenziaLavoro", "AgenziaLavoro", null, 0, User.Ruolo.AGENZIA_LAVORO, 0, "nomeAgl", "CognomeAgl", "email@ag.lav", "443282288282", "", cartAss, 0);
		User user9 = new User("Associazioni", "Associazioni", null, 0, User.Ruolo.ASSOCIAZIONI, 1, " ", " ", " ", " ", "", null, 0);
		User user10 = new User("USSM", "USSM", null, 0, User.Ruolo.USSM, 1, " ", " ", " ", " ", "", null, 0);
		User user11 = new User("ITASTRA", "ITASTRA", null, 0, User.Ruolo.ITASTRA, 1, " ", " ", " ", " ", "", null, 0);

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		userList.add(user5);
		userList.add(user6);
		userList.add(user7);
		userList.add(user8);
		userList.add(user9);
		userList.add(user10);
		userList.add(user11);
		return userList;
	}

	public static User performLogin(User loginData) {

		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getUsername().equalsIgnoreCase(loginData.getUsername()) && userList.get(i).getPassword().equals(loginData.getPassword())) {
				userList.get(i).setToken(Utils.getRandomToken());
				// userList.get(i).setType("");
				return userList.get(i);
			}

		return null;

	}

	public static boolean addUser(User loginData) {

		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getUsername().equalsIgnoreCase(loginData.getUsername())) {
				return false;
			}

		userList.add(loginData);
		return true;

	}

	

	public static boolean checkAuth(String authToken) {
		if (byPassLogin)
			return true;
		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getToken() != null && userList.get(i).getToken().equalsIgnoreCase(authToken)) {
				return true;
			}

		return false;
	}

	public static ArrayList<User> getUserList() {
		return userList;
	}

	public static boolean updateUser(User newUser) {

		User old;
		for (int i = 0; i < userList.size(); i++) {
			old = userList.get(i);
			if (old.getUsername().equals(newUser.getUsername())) {
				newUser.setPassword(old.getPassword());
				userList.set(i, newUser);
				return true;
			}
		}

		return false;
	}

	public static User getUser(String userName) {

		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getUsername().equals(userName)) {
				return userList.get(i);

			}

		return null;
	}

	public static User getUserByToken(String authToken) {
		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getToken() != null && userList.get(i).getToken().equalsIgnoreCase(authToken)) {
				return userList.get(i);
			}
		return null;
	}

	public static boolean associaUser(String username, int socialCardId) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
//				if (userList.get(i).getElencoCartelleAssociate() == null) {
//					userList.get(i).setElencoCartelleAssociate(new ArrayList());
//				}
//				if (!userList.get(i).getElencoCartelleAssociate().contains(socialCardId)) {
//					userList.get(i).getElencoCartelleAssociate().add(socialCardId);
//					return true;
//				}
			}
		}
		return false;
	}

	public static boolean deassociaUser(String username, int socialCardId) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
//				if (userList.get(i).getElencoCartelleAssociate() != null && userList.get(i).getElencoCartelleAssociate().contains(socialCardId)) {
//					userList.get(i).getElencoCartelleAssociate().remove(new Integer(socialCardId));
//					return true;
//				}
			}
		}
		return false;
	}

}
