package it.altran.harraga.model.istruzione;

import it.altran.harraga.model.Allegato;

public class IstruzioneOrigine {

	private String saLeggere;
	private String saScrivere;
	private String tipo;
	private String anni;
	private String descrizione; //  il campo da riempire sia in caso formale che informale
	private String riconosciuto;
	private Allegato allegato;




	public IstruzioneOrigine() {
	}

	public IstruzioneOrigine(String saLeggere, String saScrivere, String tipo, String anni, String descrizione, String riconosciuto, Allegato allegato) {
		super();
		this.saLeggere = saLeggere;
		this.saScrivere = saScrivere;
		this.tipo = tipo;
		this.anni = anni;
		this.descrizione = descrizione;
		this.riconosciuto = riconosciuto;
		this.allegato = allegato;
	}




	public String getSaLeggere() {
		return saLeggere;
	}

	public void setSaLeggere(String saLeggere) {
		this.saLeggere = saLeggere;
	}

	public String getSaScrivere() {
		return saScrivere;
	}

	public void setSaScrivere(String saScrivere) {
		this.saScrivere = saScrivere;
	}

	public String getAnni() {
		return anni;
	}

	public void setAnni(String anni) {
		this.anni = anni;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRiconosciuto() {
		return riconosciuto;
	}

	public void setRiconosciuto(String riconosciuto) {
		this.riconosciuto = riconosciuto;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

}
