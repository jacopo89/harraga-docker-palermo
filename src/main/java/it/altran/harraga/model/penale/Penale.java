package it.altran.harraga.model.penale;

import java.util.ArrayList;

public class Penale {

	private ArrayList<ProcedimentoPenale> procedimentiPenali;
	
	public Penale() {}
	
	public Penale(ArrayList<ProcedimentoPenale> procedimentiPenali) {
		super();
		this.procedimentiPenali = procedimentiPenali;
	}

	public ArrayList<ProcedimentoPenale> getProcedimentiPenali() {
		return procedimentiPenali;
	}

	public void setProcedimentiPenali(
			ArrayList<ProcedimentoPenale> procedimentiPenali) {
		this.procedimentiPenali = procedimentiPenali;
	}
	
			
	
}
