package it.altran.harraga.model.amministrativa;

import java.util.ArrayList;

public class DelegaAmministrativa {
	private long data;
	private String protocollo;
	private String richiestaUONI;
	private String altro;
	private ArrayList<AllegatoDelega> allegati;
	
	public DelegaAmministrativa() {}
	public DelegaAmministrativa(long data, String protocollo, String richiestaUONI, String altro, ArrayList<AllegatoDelega> allegati) {
		super();
		this.data = data;
		this.protocollo = protocollo;
		this.richiestaUONI = richiestaUONI;
		this.altro = altro;
		this.allegati = allegati;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}
	public String getRichiestaUONI() {
		return richiestaUONI;
	}
	public void setRichiestaUONI(String richiestaUONI) {
		this.richiestaUONI = richiestaUONI;
	}
	public String getAltro() {
		return altro;
	}
	public void setAltro(String altro) {
		this.altro = altro;
	}
	public ArrayList<AllegatoDelega> getAllegati() {
		return allegati;
	}
	public void setAllegati(ArrayList<AllegatoDelega> allegati) {
		this.allegati = allegati;
	}
	
	
	

}
