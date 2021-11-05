package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateUser extends ActionSupport {

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
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
    	String authToken = request.getHeader("Auth-Token");
		
    	//LoginDAO.getInstance().checkAuth(authToken);
    	

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		
		
		
		
		Gson gson = new Gson();
		
		String x= "{message: authorization_fail}";
		
		jsonString = gson.toJson(x);
		
		if (user != null) {			
			if(UserDAO.getInstance().updateUser(user)) {	
				jsonString = gson.toJson("{message: 'success'}");
			}else {
				return "error";
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