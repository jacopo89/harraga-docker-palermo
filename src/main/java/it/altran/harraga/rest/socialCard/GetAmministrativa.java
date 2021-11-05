package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.AmministrativaDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.amministrativa.Amministrativa;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class GetAmministrativa extends ActionSupport implements ServletRequestAware {

	private Long socialCardId;
	private HttpServletRequest request;
	private Long timestamp;


	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(Long id) {
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

		// LoginDAO.getInstance().checkAuth(authToken);

		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";

		User user = UserDAO.getInstance().getUserByToken(authToken);

		if (user != null) {
			if (PermissionMap.getInstance().canRead(user.getType(), Permission.sezioni.AMMINISTRATIVA)) {

				if (socialCardId > 0) {
					//Amministrativa amministrativa = SocialCardDAO.getAmministrativaBySocialCardId(socialCardId);
					Amministrativa amministrativa = AmministrativaDAO.getInstance().getAmministrativaBySocialCardId(socialCardId, timestamp, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(amministrativa);

					return "success";
				}else {
					return "error404";
				}
					
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