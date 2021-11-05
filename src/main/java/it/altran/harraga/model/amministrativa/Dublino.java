package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class Dublino {

	
	private String paesefrom;
	private String paeseto;
	//JACOPO : AGGIUNTO CAMPO DATA
	private long data;
	private String statoProcedura;
	private Allegato allegato;
	
	
	public Dublino() {}
	public Dublino(String paesefrom, String paeseto, long data, String statoProcedura, Allegato allegato) {
		super();
		this.paesefrom = paesefrom;
		this.paeseto = paeseto;
		this.data = data;
		this.statoProcedura = statoProcedura;
		this.allegato = allegato;
	}
	
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getStatoProcedura() {
		return statoProcedura;
	}
	public void setStatoProcedura(String statoProcedura) {
		this.statoProcedura = statoProcedura;
	}
	public String getPaesefrom() {
		return paesefrom;
	}
	public void setPaesefrom(String paesefrom) {
		this.paesefrom = paesefrom;
	}
	public String getPaeseto() {
		return paeseto;
	}
	public void setPaeseto(String paeseto) {
		this.paeseto = paeseto;
	}
	
	
	
}
