package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.LavoroDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.lavoro.Lavoro;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UpdateLavoro extends ActionSupport {

	private ArrayList<Lavoro> lavoro;
	private Long socialCardId;

	public ArrayList<Lavoro> getLavoro() {
		return lavoro;
	}

	public void setLavoro(ArrayList<Lavoro> lavoro) {
		this.lavoro = lavoro;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.LAVORO)) {
				if (lavoro != null && socialCardId > 0) {

					ArrayList<Lavoro> lav = LavoroDAO.getInstance().updateLavoro(socialCardId, lavoro, user);

					// ArrayList<Lavoro> lav = SocialCardDAO.updateLavoro(socialCardId, lavoro);

					Gson gson = new Gson();

					jsonString = gson.toJson(lav);
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