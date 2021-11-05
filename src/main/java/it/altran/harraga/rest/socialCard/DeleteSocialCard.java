package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialCardDAO;

public class DeleteSocialCard extends ActionSupport {

	
	private int socialCardId;
    public int getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(int socialCardId) {
		this.socialCardId = socialCardId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

    public String execute() {
    	   	
    	
    	SocialCardDAO.getInstance().deleteSocialCardById((long) socialCardId);
    	
    	Gson gson = new Gson();
        
        
        jsonString = gson.toJson(true);

        return "success";
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}