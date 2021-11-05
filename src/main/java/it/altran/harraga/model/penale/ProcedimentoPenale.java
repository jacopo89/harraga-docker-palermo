package it.altran.harraga.model.penale;

import it.altran.harraga.model.Contatto;
import it.altran.harraga.model.PEI;

public class ProcedimentoPenale {
	private String statoProcedimento;
	private Contatto assistenteSociale;  
	private PEI	pei;

	
	public ProcedimentoPenale() {}
	
	public ProcedimentoPenale(String statoProcedimento, Contatto assistenteSociale, PEI pei) {
		super();
		this.statoProcedimento = statoProcedimento;
		this.assistenteSociale = assistenteSociale;
		this.pei = pei;
	}
	
	
	public String getStatoProcedimento() {
		return statoProcedimento;
	}
	public void setStatoProcedimento(String statoProcedimento) {
		this.statoProcedimento = statoProcedimento;
	}
	public Contatto getAssistenteSociale() {
		return assistenteSociale;
	}
	public void setAssistenteSociale(Contatto assistenteSociale) {
		this.assistenteSociale = assistenteSociale;
	}
	public PEI getPei() {
		return pei;
	}
	public void setPei(PEI pei) {
		this.pei = pei;
	}
			

}
