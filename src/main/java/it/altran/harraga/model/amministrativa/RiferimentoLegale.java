package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Contatto;

public class RiferimentoLegale {

	private String tipo ;
	private Contatto contatto;
	
	public RiferimentoLegale() {}
	
	public RiferimentoLegale(String tipo, Contatto contatto) {
		super();
		this.tipo = tipo;
		this.contatto = contatto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}
	
}
