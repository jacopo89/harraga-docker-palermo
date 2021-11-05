package it.altran.harraga.model.istruzione;

import it.altran.harraga.model.Allegato;

public class IstruzioneItaliaConculso {
	private String tipo;
	private String quale;
	private long inizio;
	private long fine;
	private String istituto;
	private Allegato allegato;
	
	public IstruzioneItaliaConculso() {}
	public IstruzioneItaliaConculso(String tipo, String quale, long inizio, long fine, String istituto, Allegato allegato) {
		super();
		this.tipo = tipo;
		this.quale = quale;
		this.inizio = inizio;
		this.fine = fine;
		this.istituto = istituto;
		this.allegato = allegato;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuale() {
		return quale;
	}
	public void setQuale(String quale) {
		this.quale = quale;
	}
	public long getInizio() {
		return inizio;
	}
	public void setInizio(long inizio) {
		this.inizio = inizio;
	}
	public long getFine() {
		return fine;
	}
	public void setFine(long fine) {
		this.fine = fine;
	}
	public String getIstituto() {
		return istituto;
	}
	public void setIstituto(String istituto) {
		this.istituto = istituto;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
	
	
	

	
	
	
	
	
}
