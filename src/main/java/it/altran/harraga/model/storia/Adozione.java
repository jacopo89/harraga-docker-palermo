package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;

public class Adozione {
	private long dataAdozione;
	

	private String enteAdozione;
	private Allegato allegatoAdozione;
	
	public Adozione(){}
	
	public Adozione(long dataAdozione, String enteAdozione, Allegato allegatoAdozione){
		this.dataAdozione = dataAdozione;
		this.enteAdozione = enteAdozione;
		this.allegatoAdozione = allegatoAdozione;
	}
	
	public long getDataAdozione() {
		return dataAdozione;
	}


	public void setDataAdozione(long dataAdozione) {
		this.dataAdozione = dataAdozione;
	}


	public String getEnteAdozione() {
		return enteAdozione;
	}


	public void setEnteAdozione(String enteAdozione) {
		this.enteAdozione = enteAdozione;
	}


	public Allegato getAllegatoAdozione() {
		return allegatoAdozione;
	}


	public void setAllegatoAdozione(Allegato allegatoAdozione) {
		this.allegatoAdozione = allegatoAdozione;
	}

}
