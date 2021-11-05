package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.model.User.Ruolo;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class GetPermessi extends ActionSupport {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	private String jsonString;
	


	public String execute() {
		Gson gson = new Gson();
		
		//String perm = "{ruoli: ['a','b','c']}";
		HashMap<Ruolo, Permission> pMap = PermissionMap.getInstance().getPermissonMap();
		
		HttpServletRequest request = ServletActionContext.getRequest();
    	/*String authToken = request.getHeader("Auth-Token");

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		
		*/
		
		jsonString = gson.toJson(pMap);
		
		return "success";
		
		//return "error"; 	
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}