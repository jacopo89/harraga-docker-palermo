package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;

public class AllontanamentiRitrovamenti {

	private String tipo;
	private long data;
	private String luogo;
	private Allegato comunicazione;
	private String note;
	
	public AllontanamentiRitrovamenti(){}
	
	public AllontanamentiRitrovamenti(String tipo, long data, String luogo, Allegato comunicazione, String note) {
		super();
		this.tipo = tipo;
		this.data = data;
		this.luogo = luogo;
		this.comunicazione = comunicazione;
		this.note = note;
	}
	
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Allegato getComunicazione() {
		return comunicazione;
	}

	public void setComunicazione(Allegato comunicazione) {
		this.comunicazione = comunicazione;
	}
	
	
	
	

}
