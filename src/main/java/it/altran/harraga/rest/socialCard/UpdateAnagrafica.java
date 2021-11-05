package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.AnagraficaDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.anagrafica.Anagrafica;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateAnagrafica extends ActionSupport {

	private Anagrafica anagrafica;
	private Long socialCardId;

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.ANAGRAFICA)) {

				if (socialCardId > 0) {
					Anagrafica anagraf = AnagraficaDAO.getInstance().updateAnagrafica(socialCardId, anagrafica, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(anagraf);
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