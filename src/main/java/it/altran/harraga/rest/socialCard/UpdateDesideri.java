package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.DesideriDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.desideri.Desideri;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateDesideri extends ActionSupport {

	private Desideri desideri;
	private Long socialCardId;
	
	public Desideri getDesideri() {
		return desideri;
	}

	public void setDesideri(Desideri desideri) {
		this.desideri = desideri;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.DESIDERI)) {

				if (desideri != null && socialCardId != null) {
					Desideri des = DesideriDAO.getInstance().updateDesideri(socialCardId, desideri, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(des);
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