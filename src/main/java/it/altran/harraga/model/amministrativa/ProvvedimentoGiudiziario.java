package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class ProvvedimentoGiudiziario {
	private long data;
	private String tipo;
	private String istituzioneEmittente;
	private String avvocato;
	private Allegato allegato;
	
	public ProvvedimentoGiudiziario() {}
	public ProvvedimentoGiudiziario(long data, String tipo, String istituzioneEmittente, String avvocato, Allegato allegato) {
		super();
		this.data = data;
		this.tipo = tipo;
		this.istituzioneEmittente = istituzioneEmittente;
		this.avvocato = avvocato;
		this.allegato = allegato;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIstituzioneEmittente() {
		return istituzioneEmittente;
	}
	public void setIstituzioneEmittente(String istituzioneEmittente) {
		this.istituzioneEmittente = istituzioneEmittente;
	}
	public String getAvvocato() {
		return avvocato;
	}
	public void setAvvocato(String avvocato) {
		this.avvocato = avvocato;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
	
	

}
