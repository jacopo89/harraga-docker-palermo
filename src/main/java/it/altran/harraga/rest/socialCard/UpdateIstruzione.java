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

import javax.servlet.http.HttpServletRequest;

public class UpdateIstruzione extends ActionSupport {

	private Istruzione istruzione;
	private Long socialCardId;

	public Istruzione getIstruzione() {
		return istruzione;
	}

	public void setIstruzione(Istruzione istruzione) {
		this.istruzione = istruzione;
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
			if (PermissionMap.getInstance().canWrite(user.getType(), Permission.sezioni.ISTRUZIONE)) {

				if (istruzione != null && socialCardId > 0) {

					Istruzione istr = IstruzioneDAO.getInstance().updateIstruzione(socialCardId, istruzione, user);

					Gson gson = new Gson();

					jsonString = gson.toJson(istr);
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