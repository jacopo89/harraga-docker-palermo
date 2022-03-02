package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetUser extends ActionSupport {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	private String jsonString;
	
	private String username;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() {
		Gson gson = new Gson();
		
		User user = null;
		
		HttpServletRequest request = ServletActionContext.getRequest();
    	String authToken = request.getHeader("Auth-Token");

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		User userRequest = UserDAO.getInstance().getUserByToken(authToken);
    	
		if(username!=null && !username.equals("")) {
		
			if(userRequest!=null) {
				if(userRequest.getType() == User.Ruolo.COMUNE_AGRIGENTO || userRequest.getUsername().equals(username)) {				
					user = UserDAO.getInstance().getUser(username);
					user.setPassword("");
				}				
			}
		}
		
		jsonString = gson.toJson(user);
		
		if(user!=null)
			return "success";
		
		return "error"; 	
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}