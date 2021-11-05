package it.altran.harraga.model.amministrativa;

import java.util.ArrayList;

public class ProceduraLegale {

	private Dublino dublino;
	private ArrayList<Appuntamento> appuntamenti;
	
	private ArrayList<RicorsoAmministrativo> ricorsiAmministrativi;

	
	public ProceduraLegale() {}
	
	public ProceduraLegale(Dublino dublino, ArrayList<Appuntamento> appuntamenti, ArrayList<RicorsoAmministrativo> ricorsoAmministrativi) {
		super();
		this.dublino = dublino;
		this.appuntamenti = appuntamenti;
		this.ricorsiAmministrativi = ricorsoAmministrativi;
	}

	public Dublino getDublino() {
		return dublino;
	}

	public void setDublino(Dublino dublino) {
		this.dublino = dublino;
	}

	public ArrayList<Appuntamento> getAppuntamenti() {
		return appuntamenti;
	}

	public void setAppuntamenti(ArrayList<Appuntamento> appuntamenti) {
		this.appuntamenti = appuntamenti;
	}

	public ArrayList<RicorsoAmministrativo> getRicorsiAmministrativi() {
		return ricorsiAmministrativi;
	}

	public void setRicorsiAmministrativi(ArrayList<RicorsoAmministrativo> ricorsoAmministrativi) {
		this.ricorsiAmministrativi = ricorsoAmministrativi;
	}
	
	
	
	
	
	
}
