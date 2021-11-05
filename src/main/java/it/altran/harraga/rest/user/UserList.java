package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UserList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
    	String authToken = request.getHeader("Auth-Token");

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		User user = UserDAO.getInstance().getUserByToken(authToken);
    	
		Gson gson = new Gson();
		ArrayList<User> userList = new ArrayList<User>();
		
		if(user!=null) {
			if(user.getType() == User.Ruolo.COMUNE_PALERMO) {				
				 userList = UserDAO.getInstance().getUserList();
				 for(User userItem : userList) {
					 userItem.setPassword("");
				 }
				 
			}				
		}
		
		
		
		jsonString = gson.toJson(userList);
		
		return "success";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}