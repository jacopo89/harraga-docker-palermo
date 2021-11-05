package it.altran.harraga.model.sanitaria;

import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;

public class MedicoCurante {

	private Contatto contatto;
	private Allegato allegato;
	
	
	
	public MedicoCurante() {		
	}
	
	
	public MedicoCurante(Contatto contatto, Allegato allegato) {
		super();
		this.contatto = contatto;
		this.allegato = allegato;
	}
	
	
	public Contatto getContatto() {
		return contatto;
	}
	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}
		
}
