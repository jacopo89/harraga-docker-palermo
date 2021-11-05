package it.altran.harraga.model.desideri;

import java.io.Serializable;

public class Desideri implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long socialCardId;
	private String aspirazioniProfessionali;
	private String altreAspettative;
	
	private String editName;
	private long timestamp;
	private String TYPE;
	
	
	public Desideri() {		
	}
	
	public Desideri(Long socialCardId, String aspirazioniProfessionali, String altreAspettative) {
		super();
		this.socialCardId = socialCardId;
		this.aspirazioniProfessionali = aspirazioniProfessionali;
		this.altreAspettative = altreAspettative;
	}
	
	
	
	
	public String getEditName() {
		return editName;
	}

	public void setEditName(String editName) {
		this.editName = editName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public Long getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(Long socialCardId) {
		this.socialCardId = socialCardId;
	}

	public String getAspirazioniProfessionali() {
		return aspirazioniProfessionali;
	}
	public void setAspirazioniProfessionali(String aspirazioniProfessionali) {
		this.aspirazioniProfessionali = aspirazioniProfessionali;
	}
	public String getAltreAspettative() {
		return altreAspettative;
	}
	public void setAltreAspettative(String altreAspettative) {
		this.altreAspettative = altreAspettative;
	}
	
	
	
}
