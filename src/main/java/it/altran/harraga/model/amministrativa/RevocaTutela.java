package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class RevocaTutela {

	private String motivazione;
	private Allegato provvedimento;
	
	public RevocaTutela() {}
	public RevocaTutela(String motivazione, Allegato provvedimento) {
		super();
		this.motivazione = motivazione;
		this.provvedimento = provvedimento;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	public Allegato getProvvedimento() {
		return provvedimento;
	}
	public void setProvvedimento(Allegato provvedimento) {
		this.provvedimento = provvedimento;
	}
	
	
	
	
	
	
}
