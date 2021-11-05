package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SanitariaDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.sanitaria.Sanitaria;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateSanitaria extends ActionSupport {

	private Sanitaria sanitaria;
	private Long socialCardId;

	public Sanitaria getSanitaria() {
		return sanitaria;
	}

	public void setSanitaria(Sanitaria sanitaria) {
		this.sanitaria = sanitaria;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.SANITARIA)) {
				if (socialCardId > 0) {
					Sanitaria sanit = SanitariaDAO.getInstance().updateSanitaria(socialCardId, sanitaria, user);
					// Sanitaria sanit = SocialCardDAO.updateSanitaria(socialCardId, sanitaria);

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