package it.altran.harraga.model;

public class PEI {
	
	private String descrizione;
	private Allegato allegato;
	
	
	public PEI() {}
	
	public PEI(String descrizione, Allegato allegato) {
		super();
		this.descrizione = descrizione;
		this.allegato = allegato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
	
	
	

}
