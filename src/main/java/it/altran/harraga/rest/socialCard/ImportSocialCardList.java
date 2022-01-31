package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialCardDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.anagrafica.Anagrafica;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ImportSocialCardList extends ActionSupport {

	private  ArrayList<Object> socialCardList;
	

	public ArrayList<Object> getSocialCardList() {
		return socialCardList;
	}

	public void setSocialCardList(ArrayList<Object> socialCardList) {
		this.socialCardList = socialCardList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String authToken = request.getHeader("Auth-Token");
		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";

		User user = UserDAO.getInstance().getUserByToken(authToken);

		Gson gson = new Gson();

		if (user != null) {
			if (PermissionMap.getInstance().canCreate(user.getType())) {

				if (socialCardList != null) {
					boolean status = SocialCardDAO.getInstance().importSocialCardList(socialCardList, user);

					jsonString = gson.toJson(status);//"{input: "+status+"}";//gson.toJson(user1);

				} else {
					jsonString = "SocialCardList: null";
				}
				return "success";
			}else {
				return "error401";
			}
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