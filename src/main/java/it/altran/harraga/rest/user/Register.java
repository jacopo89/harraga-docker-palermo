package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;

public class Register extends ActionSupport {

	private User user;



	public User getUser() {
		return user;
	}

	public void setUser(User usr) {
		this.user = usr;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

	public String execute() {		
		
		Gson gson = new Gson();
		
		String x= "{message: register_fail}";
		
		jsonString = gson.toJson(x);
		
		if (user != null) {			
//			if(DummyLoginDAO.addUser(user)) {	
//				jsonString = gson.toJson("{message: 'success'}");
//			}else {
//				return "errorAlreadyExist";
//			}
			if(UserDAO.getInstance().createUser(user)) {	
				jsonString = gson.toJson("{message: 'success'}");
			}else {
				return "errorAlreadyExist";
			}
			
			return "success";
		}
		return "error";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}