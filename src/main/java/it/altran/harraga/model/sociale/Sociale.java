package it.altran.harraga.model.sociale;

import java.util.ArrayList;

public class Sociale {

	private ArrayList<Esperienza> volontariato;
	private ArrayList<Esperienza> laboratori;
	private ArrayList<Esperienza> sport;
	private ArrayList<Esperienza> gruppiAss;

	public Sociale() {
	}

	public Sociale(ArrayList<Esperienza> volontariato, ArrayList<Esperienza> laboratori, ArrayList<Esperienza> sport, ArrayList<Esperienza> gruppiAss) {
		super();
		this.volontariato = volontariato;
		this.laboratori = laboratori;
		this.sport = sport;
		this.gruppiAss = gruppiAss;
	}

	public ArrayList<Esperienza> getVolontariato() {
		return volontariato;
	}

	public void setVolontariato(ArrayList<Esperienza> volontariato) {
		this.volontariato = volontariato;
	}

	public ArrayList<Esperienza> getLaboratori() {
		return laboratori;
	}

	public void setLaboratori(ArrayList<Esperienza> laboratori) {
		this.laboratori = laboratori;
	}

	public ArrayList<Esperienza> getSport() {
		return sport;
	}

	public void setSport(ArrayList<Esperienza> sport) {
		this.sport = sport;
	}

	public ArrayList<Esperienza> getGruppiAss() {
		return gruppiAss;
	}

	public void setGruppiAss(ArrayList<Esperienza> gruppiAss) {
		this.gruppiAss = gruppiAss;
	}

	
	
}
