package it.altran.harraga.model.sanitaria;

import it.altran.harraga.model.Allegato;

public class SpecificaDisabilita {

	private String nome;
	private Allegato allegato;
	
	
	
	public SpecificaDisabilita() {		
	}
	
	
	public SpecificaDisabilita(String nome, Allegato allegato) {
		super();
		this.nome = nome;
		this.allegato = allegato;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
		
}
