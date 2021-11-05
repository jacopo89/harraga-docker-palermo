package it.altran.harraga.rest;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadFolder extends ActionSupport implements ServletRequestAware {

	private String section;
	private int socialCardId;
	


	HttpServletRequest request;
	private InputStream fileInputStream;
	protected Map session;

	
	

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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
						
//			String authToken = getServletRequest().getHeader("Auth-Token");
//			
//			if(authToken==null || !UserDAO.getInstance().checkAuth(authToken))
//				return ERROR;			
			
			
			
			ServletContext context = ServletActionContext.getServletContext();
			
			String folderPath = context.getRealPath("/docs/social-card/"+socialCardId+"/"+section);
			
			String zipPath = folderPath +".zip";
			
			
//			String tempPath = "/docs/social-card/"+socialCardId+"/"+section+".zip";
			
			pack(Paths.get(folderPath), Paths.get(zipPath));
			
			
			String path = zipPath;//context.getRealPath(zipPath);
			
			System.out.println(path);
			if(path!=null)
				fileInputStream = new FileInputStream(new File(path));
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	private static void pack(final Path folder, final Path zipFilePath) throws IOException {
	    try (
	            FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
	            ZipOutputStream zos = new ZipOutputStream(fos)
	    ) {
	        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
	                Files.copy(file, zos);
	                zos.closeEntry();
	                return FileVisitResult.CONTINUE;
	            }

	            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	                zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
	                zos.closeEntry();
	                return FileVisitResult.CONTINUE;
	            }
	        });
	    }
	}

}