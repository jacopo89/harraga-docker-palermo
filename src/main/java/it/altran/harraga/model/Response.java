package it.altran.harraga.model;

public class Response {

	private int code;
	private String message;
	
	public Response(int cod, String mess) {
		code = cod;
		message = mess;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
