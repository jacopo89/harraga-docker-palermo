package it.altran.harraga.rest.user;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;

import javax.transaction.Transactional;

public class LoginAPI extends ActionSupport {

	private User loginData;



	public User getLoginData() {
		return loginData;
	}

	public void setLoginData(User loginData) {
		this.loginData = loginData;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

	@Transactional
	public String execute() {
		Gson gson = new Gson();

		

		if (loginData != null) {
			User loginDataOut = UserDAO.getInstance().performLogin(loginData);

			if (loginDataOut != null) {	
				if(loginDataOut.isEnabled())
					jsonString = gson.toJson(loginDataOut);
				else {					
					return "errorUserNotEnabled";
				}
			}else {
				
				jsonString = gson.toJson(loginDataOut);
				
				return "error";
			}

		}
		return "success";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}