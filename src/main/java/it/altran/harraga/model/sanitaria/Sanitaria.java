package it.altran.harraga.model.sanitaria;

import java.util.ArrayList;

public class Sanitaria {

	private ArrayList<SpecificaDisabilita> specificheDisabilita;
	private ArrayList<PatologiaAllergica> patologieAllergiche;
	private MedicoCurante medicoCurante;
	private MedicoCurante presoInCarico;
	// JACOPO : vaccini diventa un ArrayList<String>
	private ArrayList<Vaccino> vaccini;
	private ArrayList<EventoMedico> eventiMedici;
	
	
	public Sanitaria() {
		
	}
	
	public Sanitaria(ArrayList<SpecificaDisabilita> specificheDisabilita, ArrayList<PatologiaAllergica> patologieAllergiche, MedicoCurante medicoCurante, MedicoCurante presoInCarico, ArrayList<Vaccino> vaccini,
			ArrayList<EventoMedico> eventoMedico) {
		super();
		this.specificheDisabilita = specificheDisabilita;
		this.patologieAllergiche = patologieAllergiche;
		this.medicoCurante = medicoCurante;
		this.presoInCarico = presoInCarico;
		this.vaccini = vaccini;
		this.eventiMedici = eventoMedico;
	}


	public ArrayList<SpecificaDisabilita> getSpecificheDisabilita() {
		return specificheDisabilita;
	}

	public void setSpecificheDisabilita(ArrayList<SpecificaDisabilita> specificheDisabilita) {
		this.specificheDisabilita = specificheDisabilita;
	}

	public ArrayList<PatologiaAllergica> getPatologieAllergiche() {
		return patologieAllergiche;
	}

	public void setPatologieAllergiche(ArrayList<PatologiaAllergica> patologieAllergiche) {
		this.patologieAllergiche = patologieAllergiche;
	}

	public MedicoCurante getMedicoCurante() {
		return medicoCurante;
	}

	public void setMedicoCurante(MedicoCurante medicoCurante) {
		this.medicoCurante = medicoCurante;
	}

	public MedicoCurante getPresoInCarico() {
		return presoInCarico;
	}

	public void setPresoInCarico(MedicoCurante presoInCarico) {
		this.presoInCarico = presoInCarico;
	}

	

	public ArrayList<EventoMedico> getEventiMedici() {
		return eventiMedici;
	}

	public void setEventiMedici(ArrayList<EventoMedico> eventiMedici) {
		this.eventiMedici = eventiMedici;
	}

	public ArrayList<Vaccino> getVaccini() {
		return vaccini;
	}

	public void setVaccini(ArrayList<Vaccino> vaccini) {
		this.vaccini = vaccini;
	} 	
	
	
	
	
	
	


	
	
	
}
