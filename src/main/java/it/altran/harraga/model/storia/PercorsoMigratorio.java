package it.altran.harraga.model.storia;

import java.util.ArrayList;

public class PercorsoMigratorio {

	
	private Long annoPartenza;
	private String luogoPartenza;
	private String motiviEspatrio;
	private String timoriManifestati;
	
	private ArrayList<PaeseAttraversato> paesiAttraversati;

	
	public PercorsoMigratorio() {}
	public PercorsoMigratorio(Long dataPartenza, String luogoPartenza, String motiviEspatrio, String timoriManifestati, ArrayList<PaeseAttraversato> paesiAttraversati) {
		super();
		this.annoPartenza = dataPartenza;
		this.luogoPartenza = luogoPartenza;
		this.paesiAttraversati = paesiAttraversati;
		this.motiviEspatrio = motiviEspatrio;
		this.timoriManifestati = timoriManifestati;
	}
	
	
	public Long getAnnoPartenza() {
		return annoPartenza;
	}
	public void setAnnoPartenza(Long dataPartenza) {
		this.annoPartenza = dataPartenza;
	}
	public String getLuogoPartenza() {
		return luogoPartenza;
	}
	public void setLuogoPartenza(String luogoPartenza) {
		this.luogoPartenza = luogoPartenza;
	}
	public ArrayList<PaeseAttraversato> getPaesiAttraversati() {
		return paesiAttraversati;
	}
	public void setPaesiAttraversati(ArrayList<PaeseAttraversato> paesiAttraversati) {
		this.paesiAttraversati = paesiAttraversati;
	}

	public void setMotiviEspatrio(String motiviEspatrio) {
		this.motiviEspatrio = motiviEspatrio;
	}

	public String getMotiviEspatrio() {
		return motiviEspatrio;
	}

	public void setTimoriManifestati(String timoriManifestati) {
		this.timoriManifestati = timoriManifestati;
	}

	public String getTimoriManifestati() {
		return timoriManifestati;
	}
}
