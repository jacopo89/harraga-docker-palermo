package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;

public class Familiare {
	
	private String relazione;	
	private Contatto contatto;
	private boolean inVita;
	private String paese;
	private boolean inUE;
	private String statusGiuridico;
	private String paeseOrigine;
	
	private String note;
	private Allegato allegato;
	
	public Familiare() {}
	
	public Familiare(String relazione, Contatto contatto, boolean inVita, String paese, boolean inUE, String statusGiuridico, String paeseOrigine, String note, Allegato allegato) {
		super();
		this.relazione = relazione;
		this.contatto = contatto;
		this.inVita = inVita;
		this.paese = paese;
		this.inUE = inUE;
		this.statusGiuridico = statusGiuridico;
		this.paeseOrigine = paeseOrigine;
		this.note = note;
		this.allegato = allegato;
	}

	public String getRelazione() {
		return relazione;
	}

	public void setRelazione(String relazione) {
		this.relazione = relazione;
	}

	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}

	public boolean isInVita() {
		return inVita;
	}

	public void setInVita(boolean inVita) {
		this.inVita = inVita;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public boolean isInUE() {
		return inUE;
	}

	public void setInUE(boolean inUE) {
		this.inUE = inUE;
	}

	public String getStatusGiuridico() {
		return statusGiuridico;
	}

	public void setStatusGiuridico(String statusGiuridico) {
		this.statusGiuridico = statusGiuridico;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public String getPaeseOrigine() {
		return paeseOrigine;
	}

	public void setPaeseOrigine(String paeseOrigine) {
		this.paeseOrigine = paeseOrigine;
	}
}
	
	
