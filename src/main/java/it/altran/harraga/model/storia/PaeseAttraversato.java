package it.altran.harraga.model.storia;

import it.altran.harraga.model.Allegato;

public class PaeseAttraversato {

	private String nome;
	private long dataArrivo;
	private long dataPartenza;
	private boolean traumatico;
	private Allegato visto;
	
	public PaeseAttraversato() {}
	
	public PaeseAttraversato(String nome, long dataArrivo, long dataPartenza, boolean traumatico, Allegato visto) {
		super();
		this.nome = nome;
		this.dataArrivo = dataArrivo;
		this.dataPartenza = dataPartenza;
		this.traumatico = traumatico;
		this.visto = visto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(long dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public long getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(long dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public boolean isTraumatico() {
		return traumatico;
	}

	public void setTraumatico(boolean traumatico) {
		this.traumatico = traumatico;
	}

	public Allegato getVisto() {
		return visto;
	}

	public void setVisto(Allegato visto) {
		this.visto = visto;
	}	
	
}
