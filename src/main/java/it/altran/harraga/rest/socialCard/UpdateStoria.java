package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.StoriaDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.storia.Storia;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateStoria extends ActionSupport {

	private Storia storia;
	private Long socialCardId;

	public Storia getStoria() {
		return storia;
	}

	public void setStoria(Storia storia) {
		this.storia = storia;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.STORIA)) {
				if (storia != null && socialCardId > 0) {

					// Storia sto = StoriaDAO.getInstance().updateStoria(socialCardId, storia);

					Storia sto = StoriaDAO.getInstance().updateStoria(socialCardId, storia, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(sto);
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