package it.altran.harraga.rest;

import com.opensymphony.xwork2.ActionSupport;
import it.altran.harraga.dao.UserDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class TestDownload extends ActionSupport implements ServletRequestAware {

	private String fileName;
	private int socialCardId;
	


	HttpServletRequest request;
	private InputStream fileInputStream;
	protected Map session;

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSocialCardId() {
		return socialCardId;
	}
	
	public void setSocialCardId(int socialCardId) {
		this.socialCardId = socialCardId;
	}


	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}
	

    public Map getSession() {
        return session;
    }
 
    public void setSession(Map sess) {
        this.session = sess;
    }
	

	public String execute() throws Exception {
		try {
						
			String authToken = getServletRequest().getHeader("Auth-Token");
			
			if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
				return ERROR;			
					
			ServletContext context = ServletActionContext.getServletContext();
			String tempPath = "/docs/social-card/"+socialCardId+"/"+fileName;
			String path = context.getRealPath(tempPath);
			
			System.out.println(path);
			if(path!=null)
				fileInputStream = new FileInputStream(new File(path));
			
		} catch (FileNotFoundException ex) {
			// logger.error(this.getClass().getSimpleName() + ": File in " + FILE_FOLDER + filename + " cannot be found.");
			ex.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

}