package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;

public class RelazioneAssistenteSociale {
	private String descrizione;
	private Allegato allegato;
	
	
	public RelazioneAssistenteSociale() {}
	
	public RelazioneAssistenteSociale(String descrizione, Allegato allegato) {
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
