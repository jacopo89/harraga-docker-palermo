package it.altran.harraga.model.sanitaria;

import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;

public class EventoMedico {
	
	
	private long data;	
	private String tipo;

	
	private String presidioOspedaliero;
	private String unitaOperativa;
	private Contatto medicoRiferimento;
	
	private Allegato allegato;

	
	public EventoMedico() {
		
	}
	
	public EventoMedico(long data, String presidioOspedaliero, String unitaOperativa, Contatto medicoRiferimento, Allegato allegato, String tipo) {
		super();
		this.data = data;
		this.presidioOspedaliero = presidioOspedaliero;
		this.unitaOperativa = unitaOperativa;
		this.medicoRiferimento = medicoRiferimento;
		this.allegato = allegato;
		this.tipo=tipo;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getPresidioOspedaliero() {
		return presidioOspedaliero;
	}

	public void setPresidioOspedaliero(String presidioOspedaliero) {
		this.presidioOspedaliero = presidioOspedaliero;
	}

	public String getUnitaOperativa() {
		return unitaOperativa;
	}

	public void setUnitaOperativa(String unitaOperativa) {
		this.unitaOperativa = unitaOperativa;
	}

	public Contatto getMedicoRiferimento() {
		return medicoRiferimento;
	}

	public void setMedicoRiferimento(Contatto medicoRiferimento) {
		this.medicoRiferimento = medicoRiferimento;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
	

}
