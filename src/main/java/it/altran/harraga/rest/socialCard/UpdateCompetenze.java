package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.CompetenzeDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.competenze.Competenze;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UpdateCompetenze extends ActionSupport {

	private Competenze competenze;
	private Long socialCardId;

	public Competenze getCompetenze() {
		return competenze;
	}

	public void setCompetenze(Competenze competenze) {
		this.competenze = competenze;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.COMPETENZE)) {

				if (competenze != null && socialCardId > 0) {

					// Competenze sto = CompetenzeDAO.getInstance().updateCompetenze(socialCardId, competenze);

					Competenze soc = CompetenzeDAO.getInstance().updateCompetenze(socialCardId, competenze, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(soc);
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