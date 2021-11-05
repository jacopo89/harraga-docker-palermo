package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialCardDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.SocialCard;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class GetSocialCard extends ActionSupport implements ServletRequestAware  {

	
	private int socialCardId;
	private HttpServletRequest request;
	
    public int getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(int id) {
		this.socialCardId = id;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

    public String execute() {  	
    	
    	
    	String authToken = getServletRequest().getHeader("Auth-Token");
		
    	//LoginDAO.getInstance().checkAuth(authToken);
    	

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		
    	
    	SocialCard user1 =  SocialCardDAO.getSocialCardById(socialCardId);
    	
    	Gson gson = new Gson();
        
        
        jsonString = gson.toJson(user1);

        return "success";
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}