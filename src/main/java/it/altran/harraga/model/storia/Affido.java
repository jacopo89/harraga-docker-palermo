package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;

public class Affido {
	private long dataAffido;
	private String enteAffido;
	private Allegato allegatoAffido;
	
	public Affido(){}
	
	public Affido(long dataAffido, String enteAffido, Allegato allegatoAffido){
		this.dataAffido = dataAffido;
		this.enteAffido = enteAffido;
		this.allegatoAffido = allegatoAffido;
	}
	

	public long getDataAffido() {
		return dataAffido;
	}


	public void setDataAffido(long dataAffido) {
		this.dataAffido = dataAffido;
	}


	public String getEnteAffido() {
		return enteAffido;
	}


	public void setEnteAffido(String enteAffido) {
		this.enteAffido = enteAffido;
	}


	public Allegato getAllegatoAffido() {
		return allegatoAffido;
	}


	public void setAllegatoAffido(Allegato allegatoAffido) {
		this.allegatoAffido = allegatoAffido;
	}
}