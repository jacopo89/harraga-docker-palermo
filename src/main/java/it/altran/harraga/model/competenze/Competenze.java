package it.altran.harraga.model.competenze;

import java.util.ArrayList;

public class Competenze {

	private ArrayList<LinguaParlata> lingueDichiarate;
	private ArrayList<LinguaParlata> lingueAttestate;
	private ArrayList<LinguaParlata> lingueCertificate;

	private ArrayList<CompetenzaDigitale> competenzeDigitali;
	private ArrayList<CompetenzaDigitale> altreCompetenze;
	private ArrayList<Patente> patenti;

	public Competenze() {
	}

	public Competenze(ArrayList<LinguaParlata> lingueDichiarate, ArrayList<LinguaParlata> lingueAttestate, ArrayList<LinguaParlata> lingueCertificate, ArrayList<CompetenzaDigitale> competenzeDigitali,
			ArrayList<CompetenzaDigitale> altreCompetenze, ArrayList<Patente> patenti) {
		super();
		this.lingueDichiarate = lingueDichiarate;
		this.lingueAttestate = lingueAttestate;
		this.lingueCertificate = lingueCertificate;
		this.competenzeDigitali = competenzeDigitali;
		this.altreCompetenze = altreCompetenze;
		this.patenti = patenti;
	}

	public ArrayList<LinguaParlata> getLingueDichiarate() {
		return lingueDichiarate;
	}

	public void setLingueDichiarate(ArrayList<LinguaParlata> lingueDichiarate) {
		this.lingueDichiarate = lingueDichiarate;
	}

	public ArrayList<LinguaParlata> getLingueAttestate() {
		return lingueAttestate;
	}

	public void setLingueAttestate(ArrayList<LinguaParlata> lingueAttestate) {
		this.lingueAttestate = lingueAttestate;
	}

	public ArrayList<LinguaParlata> getLingueCertificate() {
		return lingueCertificate;
	}

	public void setLingueCertificate(ArrayList<LinguaParlata> lingueCertificate) {
		this.lingueCertificate = lingueCertificate;
	}

	public ArrayList<CompetenzaDigitale> getCompetenzeDigitali() {
		return competenzeDigitali;
	}

	public void setCompetenzeDigitali(ArrayList<CompetenzaDigitale> competenzeDigitali) {
		this.competenzeDigitali = competenzeDigitali;
	}

	public ArrayList<CompetenzaDigitale> getAltreCompetenze() {
		return altreCompetenze;
	}

	public void setAltreCompetenze(ArrayList<CompetenzaDigitale> altreCompetenze) {
		this.altreCompetenze = altreCompetenze;
	}

	public ArrayList<Patente> getPatenti() {
		return patenti;
	}

	public void setPatenti(ArrayList<Patente> patenti) {
		this.patenti = patenti;
	}

}
