package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class PermessoSogiorno {
	private String stato;
	private String altroStato;
	private long dataScadenza;
	private long dataRichiesta;
	private long dataRilascio;
	private String tipologia;
	private String rilasciatoDa;
	private Allegato allegato;
	
	public PermessoSogiorno() {}
	
	public PermessoSogiorno(String stato, String altroStato, long dataScadenza, long dataRichiesta, long dataRilascio, String rilasciatoDa, String tipologia, Allegato allegato) {
		super();
		this.stato = stato;
		this.dataRichiesta = dataRichiesta;
		this.dataRilascio = dataRilascio;
		this.rilasciatoDa = rilasciatoDa;
		this.altroStato = altroStato;
		this.dataScadenza = dataScadenza;
		this.tipologia = tipologia;
		this.allegato = allegato;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public long getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(long dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public String getAltroStato() {
		return altroStato;
	}

	public void setAltroStato(String altroStato) {
		this.altroStato = altroStato;
	}

	public long getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(long dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public long getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(long dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	public String getRilasciatoDa() {
		return rilasciatoDa;
	}

	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}
}
