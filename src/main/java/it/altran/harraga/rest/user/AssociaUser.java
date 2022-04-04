package it.altran.harraga.rest.user;

import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AssociaUser extends ActionSupport {

	private String username;
	private int socialCardId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

		HttpServletRequest request = ServletActionContext.getRequest();

		String authToken = request.getHeader("Auth-Token");

		// LoginDAO.getInstance().checkAuth(authToken);

		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		User user = UserDAO.getInstance().getUserByToken(authToken);

		if (user != null && user.getType() == User.Ruolo.COMUNE_AGRIGENTO) {

			if (username != null && socialCardId > 0) {
				if (UserDAO.getInstance().associaUser(username, socialCardId))
					return "success";
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