package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import it.altran.harraga.dao.SocialCardDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.SocialCard;
import it.altran.harraga.model.User;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GetMaggiorenniList {
	private static final long serialVersionUID = 1L;
	private String jsonString;

    public String execute() {
    	
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
		
    	String authToken = request.getHeader("Auth-Token");
		
    	//LoginDAO.getInstance().checkAuth(authToken);
    	

		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		User user = UserDAO.getInstance().getUserByToken(authToken);
    	
		Gson gson = new Gson();
		ArrayList<SocialCard> socialCardList = new ArrayList<SocialCard>();
		
		if(user!=null) {
			if(PermissionMap.getInstance().isIstitutional(user.getType())) {
				socialCardList = SocialCardDAO.getInstance().getMaggiorenniSocialCards();
			}else {
				socialCardList = SocialCardDAO.getInstance().getSocialCardListByUser(user);
			}				
		}
        
        jsonString = gson.toJson(socialCardList);

        return "success";
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
