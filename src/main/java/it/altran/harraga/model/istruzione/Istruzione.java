package it.altran.harraga.model.istruzione;

import java.util.ArrayList;

public class Istruzione {

	private	ArrayList<IstruzioneOrigine>	istruzioneOrigine;
	private	ArrayList<IstruzioneItaliaConculso>	istruzioneItaliaConculsi;
	private	ArrayList<IstruzioneItaliainCorso>	istruzioneItaliainCorso;

	
	public Istruzione() {}
	public Istruzione(ArrayList<IstruzioneOrigine> istruzioneOrigine, ArrayList<IstruzioneItaliaConculso> istruzioneItaliaConculsi, ArrayList<IstruzioneItaliainCorso> istruzioneItaliainCorso) {
		super();
		this.istruzioneOrigine = istruzioneOrigine;
		this.istruzioneItaliaConculsi = istruzioneItaliaConculsi;
		this.istruzioneItaliainCorso = istruzioneItaliainCorso;
	}
	
	
	public ArrayList<IstruzioneOrigine> getIstruzioneOrigine() {
		return istruzioneOrigine;
	}
	public void setIstruzioneOrigine(ArrayList<IstruzioneOrigine> istruzioneOrigine) {
		this.istruzioneOrigine = istruzioneOrigine;
	}
	public ArrayList<IstruzioneItaliaConculso> getIstruzioneItaliaConculsi() {
		return istruzioneItaliaConculsi;
	}
	public void setIstruzioneItaliaConculsi(ArrayList<IstruzioneItaliaConculso> istruzioneItaliaConculsi) {
		this.istruzioneItaliaConculsi = istruzioneItaliaConculsi;
	}
	public ArrayList<IstruzioneItaliainCorso> getIstruzioneItaliainCorso() {
		return istruzioneItaliainCorso;
	}
	public void setIstruzioneItaliainCorso(ArrayList<IstruzioneItaliainCorso> istruzioneItaliainCorso) {
		this.istruzioneItaliainCorso = istruzioneItaliainCorso;
	}

	
	
}
