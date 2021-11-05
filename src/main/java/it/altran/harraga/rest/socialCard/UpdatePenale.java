package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.PenaleDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.penale.Penale;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdatePenale extends ActionSupport {

	private Penale penale;
	private Long socialCardId;

	public Penale getPenale() {
		return penale;
	}

	public void setPenale(Penale penale) {
		this.penale = penale;
	}

	public Long getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(Long socialCardId) {
		this.socialCardId = socialCardId;
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

		if (user != null) {
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.PENALE)) {
				if (socialCardId > 0) {
					// Penale anagraf = PenaleDAO.getInstance().updatePenale(socialCardId , penale );
					Penale sanit = PenaleDAO.getInstance().updatePenale(socialCardId, penale, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(sanit);
				} else {
					jsonString = "SocialCard: null";
				}
				return "success";
			}
		}
		return "error401";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}