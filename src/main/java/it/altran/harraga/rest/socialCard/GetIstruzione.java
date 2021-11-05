package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.IstruzioneDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.istruzione.Istruzione;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class GetIstruzione extends ActionSupport implements ServletRequestAware {

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

		HttpServletRequest request = ServletActionContext.getRequest();

		String authToken = request.getHeader("Auth-Token");

		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";
		User user = UserDAO.getInstance().getUserByToken(authToken);

		if (user != null) {
			if (PermissionMap.getInstance().canRead(user.getType(), Permission.sezioni.ISTRUZIONE)) {

				Istruzione istruzione = IstruzioneDAO.getInstance().getIstruzioneBySocialCardId(socialCardId, timestamp);

				Gson gson = new Gson();

				jsonString = gson.toJson(istruzione);

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