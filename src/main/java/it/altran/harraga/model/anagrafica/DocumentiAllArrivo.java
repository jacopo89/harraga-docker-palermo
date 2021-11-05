package it.altran.harraga.model.anagrafica;

import it.altran.harraga.model.Allegato;

public class DocumentiAllArrivo {
	
	private String tipologia;
	private Allegato allegato;

	public DocumentiAllArrivo() {}
	
	public DocumentiAllArrivo(String tipologia, Allegato allegato) {
		super();
		this.tipologia = tipologia;
		this.allegato = allegato;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
}
