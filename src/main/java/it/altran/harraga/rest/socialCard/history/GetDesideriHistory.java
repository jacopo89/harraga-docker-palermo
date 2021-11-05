package it.altran.harraga.rest.socialCard.history;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.DesideriDAO;
import it.altran.harraga.model.SectionHistory;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetDesideriHistory extends ActionSupport implements ServletRequestAware  {

	
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
		
    	//LoginDAO.getInstance().checkAuth(authToken);
    	

//		if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
//			return "error401";
		
    	
//    	Desideri desideri =  DesideriDAO.getInstance().getDesideriBySocialCardId(socialCardId);
    	List<SectionHistory> sh = DesideriDAO.getInstance().getDesideriHistBySocialCardId(socialCardId);
    	
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