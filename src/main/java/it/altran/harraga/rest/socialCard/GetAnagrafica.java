package it.altran.harraga.rest.socialCard;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.AnagraficaDAO;
import it.altran.harraga.dao.UserDAO;
import it.altran.harraga.model.User;
import it.altran.harraga.model.anagrafica.Anagrafica;
import it.altran.harraga.utils.Permission;
import it.altran.harraga.utils.PermissionMap;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class GetAnagrafica extends ActionSupport implements ServletRequestAware {

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

		//recupero token dalla richiesta HTTP
		String authToken = getServletRequest().getHeader("Auth-Token");
		jsonString = "anagrafica: null";

		//Verifica validit token
		if (authToken == null || !UserDAO.getInstance().checkAuth(authToken))
			return "error401";

		//recupero utente dal token
		User user = UserDAO.getInstance().getUserByToken(authToken);

		if (user != null) {
			//verifica diritti di esecuzione
			if (PermissionMap.getInstance().canRead(user.getType(), Permission.sezioni.ANAGRAFICA)) {

				//esecuzione operazioni
				if (socialCardId > 0) {
					Anagrafica anagrafica = AnagraficaDAO.getInstance().getAnagraficaBySocialCardId(socialCardId,timestamp);
					Gson gson = new Gson();
					jsonString = gson.toJson(anagrafica);

					return "success";
				} else {
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
