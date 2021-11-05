package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialeDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.sociale.Sociale;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class GetSociale extends ActionSupport implements ServletRequestAware {

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

		Sociale sociale = SocialeDAO.getInstance().getSocialeBySocialCardId(socialCardId, timestamp);

		Gson gson = new Gson();

		jsonString = gson.toJson(sociale);

		return "success";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}