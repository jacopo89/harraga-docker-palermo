package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class Proseguo21 {
	private long dataAttribuzione;
	private long dataFinale;
	public long getDataAttribuzione() {
		return dataAttribuzione;
	}
	public void setDataAttribuzione(long dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	public long getDataFinale() {
		return dataFinale;
	}
	public void setDataFinale(long dataFinale) {
		this.dataFinale = dataFinale;
	}
	private String note;
	private Allegato provvedimento;
	
	
	
	public Proseguo21() {}
	public Proseguo21(long dataAttribuzione, long dataFinale, String note, Allegato provvedimento) {
		super();
		this.dataAttribuzione = dataAttribuzione;
		this.dataFinale = dataFinale;
		this.note = note;
		this.provvedimento = provvedimento;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Allegato getProvvedimento() {
		return provvedimento;
	}
	public void setProvvedimento(Allegato provvedimento) {
		this.provvedimento = provvedimento;
	}
	
	
	
	
	
}
