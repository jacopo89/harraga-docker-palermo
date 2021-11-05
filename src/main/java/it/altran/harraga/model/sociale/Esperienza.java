package it.altran.harraga.model.sociale;

import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;

public class Esperienza {

	private String pregressa;
	private String tipo;
	private long dataInizio;
	private long dataFine;
	private String giorniOrari;
	private String competenzeAcquisite;
	private Contatto referente;
	private Allegato certificazione;
	
	public Esperienza() {}
	public Esperienza(String pregressa, String tipo, long dataInizio, long dataFine, String giorniOrari, String competenzeAcquisite, Contatto referente, Allegato certificazione) {
		super();
		this.pregressa = pregressa;
		this.tipo = tipo;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.giorniOrari = giorniOrari;
		this.competenzeAcquisite = competenzeAcquisite;
		this.referente = referente;
		this.certificazione = certificazione;
	}
	
	
	
	
	public String isPregressa() {
		return pregressa;
	}
	public void setPregressa(String pregressa) {
		this.pregressa = pregressa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(long dataInizio) {
		this.dataInizio = dataInizio;
	}
	public long getDataFine() {
		return dataFine;
	}
	public void setDataFine(long dataFine) {
		this.dataFine = dataFine;
	}
	public String getGiorniOrari() {
		return giorniOrari;
	}
	public void setGiorniOrari(String giorniOrari) {
		this.giorniOrari = giorniOrari;
	}
	public String getCompetenzeAcquisite() {
		return competenzeAcquisite;
	}
	public void setCompetenzeAcquisite(String competenzeAcquisite) {
		this.competenzeAcquisite = competenzeAcquisite;
	}
	public Contatto getReferente() {
		return referente;
	}
	public void setReferente(Contatto referente) {
		this.referente = referente;
	}
	public Allegato getCertificazione() {
		return certificazione;
	}
	public void setCertificazione(Allegato certificazione) {
		this.certificazione = certificazione;
	}
	
	
	
	
	
	

}
