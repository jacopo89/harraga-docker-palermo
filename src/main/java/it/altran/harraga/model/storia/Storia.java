package it.altran.harraga.model.storia;

import java.util.ArrayList;

public class Storia {

	private String etnia;
	private String religione;
	
	private ArrayList<Familiare> familiari;
	private PercorsoMigratorio percorsoMigratorio;
	private Accoglienza accoglienza;

	private StoriaPersonale storiaPersonale;

	private String osservazioni;
	private String pianificazioneInterventi;
	private String diario;
	private ValutazioneMultiDisciplinare valutazioneMultidisciplinare;


	public Storia() {}
	
	public Storia(String etnia, String religione, ArrayList<Familiare> familiari, PercorsoMigratorio percorsoMigratorio, Accoglienza accoglienza, StoriaPersonale storiaPersonale, String osservazioni, String pianificazioneInterventi, String diario, ValutazioneMultiDisciplinare valutazioneMultidisciplinare) {
		super();
		this.etnia = etnia;
		this.religione = religione;
		this.familiari = familiari;
		this.percorsoMigratorio = percorsoMigratorio;
		this.accoglienza = accoglienza;
		this.storiaPersonale = storiaPersonale;
		this.osservazioni = osservazioni;
		this.pianificazioneInterventi = pianificazioneInterventi;
		this.diario = diario;
		this.valutazioneMultidisciplinare = valutazioneMultidisciplinare;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getReligione() {
		return religione;
	}

	public void setReligione(String religione) {
		this.religione = religione;
	}

	public ArrayList<Familiare> getFamiliari() {
		return familiari;
	}

	public void setFamiliari(ArrayList<Familiare> familiari) {
		this.familiari = familiari;
	}

	public PercorsoMigratorio getPercorsoMigratorio() {
		return percorsoMigratorio;
	}

	public void setPercorsoMigratorio(PercorsoMigratorio percorsoMigratorio) {
		this.percorsoMigratorio = percorsoMigratorio;
	}

	public Accoglienza getAccoglienza() {
		return accoglienza;
	}

	public void setAccoglienza(Accoglienza accoglienza) {
		this.accoglienza = accoglienza;
	}

	public StoriaPersonale getStoriaPersonale() {
		return storiaPersonale;
	}

	public void setStoriaPersonale(StoriaPersonale storiaPersonale) {
		this.storiaPersonale = storiaPersonale;
	}

	public String getOsservazioni() {
		return osservazioni;
	}

	public void setOsservazioni(String osservazioni) {
		this.osservazioni = osservazioni;
	}

	public String getDiario() {
		return diario;
	}

	public void setDiario(String diario) {
		this.diario = diario;
	}

	public String getPianificazioneInterventi() {
		return pianificazioneInterventi;
	}

	public void setPianificazioneInterventi(String pianificazioneInterventi) {
		this.pianificazioneInterventi = pianificazioneInterventi;
	}

	public void setValutazioneMultidisciplinare(ValutazioneMultiDisciplinare valutazioneMultidisciplinare) {
		this.valutazioneMultidisciplinare = valutazioneMultidisciplinare;
	}

	public ValutazioneMultiDisciplinare getValutazioneMultidisciplinare() {
		return valutazioneMultidisciplinare;
	}
}
