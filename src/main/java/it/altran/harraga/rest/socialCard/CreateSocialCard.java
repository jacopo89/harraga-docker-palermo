package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialCardDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.SocialCard;
import it.altran.harraga.model.User;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class CreateSocialCard extends ActionSupport {

	private SocialCard socialCard;

	public SocialCard getSocialCard() {
		return socialCard;
	}

	public void setSocialCard(SocialCard sc) {
		this.socialCard = sc;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonString;

	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String authToken = request.getHeader("Auth-Token");

		System.out.println(authToken);
		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";

		User user = UserDAO.getInstance().getUserByToken(authToken);

		Gson gson = new Gson();

		if (user != null) {
			if (PermissionMap.getInstance().canCreate(user.getType())) {

				if (socialCard != null) {
					SocialCard user1 = SocialCardDAO.getInstance().createSocialCard(socialCard, user);

					gson = new Gson();

					jsonString = gson.toJson(user1);

				} else {
					jsonString = "SocialCard: null";
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