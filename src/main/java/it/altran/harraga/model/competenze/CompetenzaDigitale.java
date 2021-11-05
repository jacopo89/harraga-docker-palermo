package it.altran.harraga.model.competenze;

import it.altran.harraga.model.Allegato;

public class CompetenzaDigitale extends CompetenzaBase {

	private String tipo;
	private String attivitaSvolta;
	private String livello;
	
	
	public CompetenzaDigitale() {
		super();
	}
	
	public CompetenzaDigitale(String tipo, String attivitaSvolta, String livello, String nome, String descrizione, Allegato certificazione) {
		super(nome, descrizione, certificazione);
		this.attivitaSvolta = attivitaSvolta;
		this.livello = livello;
		this.tipo = tipo;
	}

	public String getAttivitaSvolta() {
		return attivitaSvolta;
	}

	public void setAttivitaSvolta(String attivitaSvolta) {
		this.attivitaSvolta = attivitaSvolta;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	

}
