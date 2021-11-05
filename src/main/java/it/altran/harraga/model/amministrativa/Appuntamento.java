package it.altran.harraga.model.amministrativa;

import java.util.ArrayList;

public class Appuntamento {

	private String luogo;
	private String altroLuogo;
	private long data;
	private String motivo;
	private String esito;
	private ArrayList<AllegatoAppuntamento> allegati;
	
	public Appuntamento() {}
	public Appuntamento(String luogo, String altroLuogo, long data, String motivo, String esito, ArrayList<AllegatoAppuntamento> allegati) {
		super();
		this.luogo = luogo;
		this.altroLuogo = altroLuogo;
		this.data = data;
		this.motivo = motivo;
		this.esito = esito;
		this.allegati = allegati;
	}
	
	
	
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public ArrayList<AllegatoAppuntamento> getAllegati() {
		return allegati;
	}
	public void setAllegati(ArrayList<AllegatoAppuntamento> allegati) {
		this.allegati = allegati;
	}
	public String getAltroLuogo() {
		return altroLuogo;
	}
	public void setAltroLuogo(String altroLuogo) {
		this.altroLuogo = altroLuogo;
	}
	
	
	

}
