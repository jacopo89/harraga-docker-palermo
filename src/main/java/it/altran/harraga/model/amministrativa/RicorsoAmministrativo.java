package it.altran.harraga.model.amministrativa;

import java.util.ArrayList;

public class RicorsoAmministrativo {
	private long data;
	private String avvocato;
	private String gratuito;
	private String note;
	private ArrayList<Udienza> udienze;
	private ArrayList<AllegatoRicorso> allegati;
	
	
	
	public RicorsoAmministrativo() {}
	public RicorsoAmministrativo(long data, String avvocato, String gratuito, String note, ArrayList<Udienza> udienze, ArrayList<AllegatoRicorso> allegati) {
		super();
		this.data = data;
		this.avvocato = avvocato;
		this.gratuito = gratuito;
		this.note = note;
		this.udienze = udienze;
		this.allegati = allegati;
	}
	
	
	
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getAvvocato() {
		return avvocato;
	}
	public void setAvvocato(String avvocato) {
		this.avvocato = avvocato;
	}
	public String getGratuito() {
		return gratuito;
	}
	public void setGratuito(String gratuito) {
		this.gratuito = gratuito;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ArrayList<Udienza> getUdienze() {
		return udienze;
	}
	public void setUdienze(ArrayList<Udienza> udienze) {
		this.udienze = udienze;
	}
	public ArrayList<AllegatoRicorso> getAllegati() {
		return allegati;
	}
	public void setAllegati(ArrayList<AllegatoRicorso> allegati) {
		this.allegati = allegati;
	}

	
	
	
	
	
}
