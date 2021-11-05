package it.altran.harraga.rest.socialCard.history;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.SocialeDAO;
import it.altran.harraga.model.SectionHistory;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetSocialeHistory extends ActionSupport implements ServletRequestAware  {

	
	private Long socialCardId;
	private HttpServletRequest request;
	
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
		
    	List<SectionHistory> sh = SocialeDAO.getInstance().getSocialeHistBySocialCardId(socialCardId);
    	
    	Gson gson = new Gson();
        
        
        jsonString = gson.toJson(sh);

        return "success";
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

}