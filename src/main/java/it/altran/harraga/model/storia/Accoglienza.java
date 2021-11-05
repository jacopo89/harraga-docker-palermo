package it.altran.harraga.model.storia;

import it.altran.harraga.model.PEI;

import java.util.ArrayList;

public class Accoglienza {
	private String hotSpot;
	private long hotSpotDataIngresso;
	private long hotSpotDataUscita;
	private String cpaNome;
	private long cpaDataIngresso;
	private long cpaDataUscita;
	private String secondaAccoglienza;
	private String secondaAccoglienzaTipo;
	private long secondaAccoglienzaDataIngresso;
	private long secondaAccoglienzaDataUscita;
	private String nomeResponsabileRespSecAcc;
	private String cognomeResponsabileRespSecAcc;
	private String mailResponsabileRespSecAcc;
	private String telefonoResponsabileRespSecAcc;
	
	private ArrayList<PEI> pei;
	private ArrayList<AllontanamentiRitrovamenti> allontanamentiRitrovamenti;
	private ArrayList<Affido> affidi;
	private ArrayList<Adozione> adozioni;
	private ArrayList<RelazioneAssistenteSociale> relazioniAssistenteSociale;
	
	
	public Accoglienza() {}
	public Accoglienza(String hotSpot, long hotSpotDataIngresso, long hotSpotDataUscita, String cpaNome, long cpaDataIngresso, long cpaDataUscita, String secondaAccoglienza, String secondaAccoglienzaTipo,
			String nomeResponsabileRespSecAcc, String cognomeResponsabileRespSecAcc, String mailResponsabileRespSecAcc, String telefonoResponsabileRespSecAcc,
			long secondaAccoglienzaDataIngresso, long secondaAccoglienzaDataUscita, ArrayList<PEI> pEI, ArrayList<AllontanamentiRitrovamenti> allontanamentiRitrovamenti, ArrayList<Affido> affidi, ArrayList<Adozione> adozioni, ArrayList<RelazioneAssistenteSociale> relazioniAssistenteSociale) {
		super();
		this.hotSpot = hotSpot;
		this.hotSpotDataIngresso = hotSpotDataIngresso;
		this.hotSpotDataUscita = hotSpotDataUscita;
		this.cpaNome = cpaNome;
		this.cpaDataIngresso = cpaDataIngresso;
		this.cpaDataUscita = cpaDataUscita;
		this.secondaAccoglienza = secondaAccoglienza;
		this.secondaAccoglienzaTipo = secondaAccoglienzaTipo;
		this.nomeResponsabileRespSecAcc = nomeResponsabileRespSecAcc;
		this.cognomeResponsabileRespSecAcc = cognomeResponsabileRespSecAcc;
		this.mailResponsabileRespSecAcc = mailResponsabileRespSecAcc;
		this.telefonoResponsabileRespSecAcc = telefonoResponsabileRespSecAcc;
		this.secondaAccoglienzaDataIngresso = secondaAccoglienzaDataIngresso;
		this.secondaAccoglienzaDataUscita = secondaAccoglienzaDataUscita;
		this.pei = pEI;
		this.allontanamentiRitrovamenti = allontanamentiRitrovamenti;
		this.affidi = affidi;
		this.adozioni = adozioni;
		this.relazioniAssistenteSociale = relazioniAssistenteSociale;
	}
	
	
	public String getHotSpot() {
		return hotSpot;
	}
	public void setHotSpot(String hotSpot) {
		this.hotSpot = hotSpot;
	}
	public long getHotSpotDataIngresso() {
		return hotSpotDataIngresso;
	}
	public void setHotSpotDataIngresso(long hotSpotDataIngresso) {
		this.hotSpotDataIngresso = hotSpotDataIngresso;
	}
	public long getHotSpotDataUscita() {
		return hotSpotDataUscita;
	}
	public void setHotSpotDataUscita(long hotSpotDataUscita) {
		this.hotSpotDataUscita = hotSpotDataUscita;
	}
	public String getCpaNome() {
		return cpaNome;
	}
	public void setCpaNome(String cpaNome) {
		this.cpaNome = cpaNome;
	}
	public long getCpaDataIngresso() {
		return cpaDataIngresso;
	}
	public void setCpaDataIngresso(long cpaDataIngresso) {
		this.cpaDataIngresso = cpaDataIngresso;
	}
	public long getCpaDataUscita() {
		return cpaDataUscita;
	}
	public void setCpaDataUscita(long cpaDataUscita) {
		this.cpaDataUscita = cpaDataUscita;
	}
	public String getSecondaAccoglienza() {
		return secondaAccoglienza;
	}
	public void setSecondaAccoglienza(String secondaAccoglienza) {
		this.secondaAccoglienza = secondaAccoglienza;
	}
	public String getSecondaAccoglienzaTipo() {
		return secondaAccoglienzaTipo;
	}
	public void setSecondaAccoglienzaTipo(String secondaAccoglienzaTipo) {
		this.secondaAccoglienzaTipo = secondaAccoglienzaTipo;
	}
	public long getSecondaAccoglienzaDataIngresso() {
		return secondaAccoglienzaDataIngresso;
	}
	public void setSecondaAccoglienzaDataIngresso(long secondaAccoglienzaDataIngresso) {
		this.secondaAccoglienzaDataIngresso = secondaAccoglienzaDataIngresso;
	}
	public long getSecondaAccoglienzaDataUscita() {
		return secondaAccoglienzaDataUscita;
	}
	public void setSecondaAccoglienzaDataUscita(long secondaAccoglienzaDataUscita) {
		this.secondaAccoglienzaDataUscita = secondaAccoglienzaDataUscita;
	}
	public  ArrayList<PEI> getPei() {
		return pei;
	}
	public void setPei( ArrayList<PEI> pEI) {
		this.pei = pEI;
	}
	public ArrayList<AllontanamentiRitrovamenti> getAllontanamentiRitrovamenti() {
		return allontanamentiRitrovamenti;
	}
	public void setAllontanamentiRitrovamenti(ArrayList<AllontanamentiRitrovamenti> allontanamentiRitrovamenti) {
		this.allontanamentiRitrovamenti = allontanamentiRitrovamenti;
	}
	
	
	public ArrayList<Affido> getAffidi() {
		return affidi;
	}
	public void setAffidi(ArrayList<Affido> affidi) {
		this.affidi = affidi;
	}
	public ArrayList<Adozione> getAdozioni() {
		return adozioni;
	}
	public void setAdozioni(ArrayList<Adozione> adozioni) {
		this.adozioni = adozioni;
	}
	public ArrayList<RelazioneAssistenteSociale> getRelazioniAssistenteSociale() {
		return relazioniAssistenteSociale;
	}
	public void setRelazioniAssistenteSociale(
			ArrayList<RelazioneAssistenteSociale> relazioniAssistenteSociale) {
		this.relazioniAssistenteSociale = relazioniAssistenteSociale;
	}
	public String getNomeResponsabileRespSecAcc() {
		return nomeResponsabileRespSecAcc;
	}
	public void setNomeResponsabileRespSecAcc(String nomeResponsabileRespSecAcc) {
		this.nomeResponsabileRespSecAcc = nomeResponsabileRespSecAcc;
	}
	public String getCognomeResponsabileRespSecAcc() {
		return cognomeResponsabileRespSecAcc;
	}
	public void setCognomeResponsabileRespSecAcc(
			String cognomeResponsabileRespSecAcc) {
		this.cognomeResponsabileRespSecAcc = cognomeResponsabileRespSecAcc;
	}
	public String getMailResponsabileRespSecAcc() {
		return mailResponsabileRespSecAcc;
	}
	public void setMailResponsabileRespSecAcc(String mailResponsabileRespSecAcc) {
		this.mailResponsabileRespSecAcc = mailResponsabileRespSecAcc;
	}
	public String getTelefonoResponsabileRespSecAcc() {
		return telefonoResponsabileRespSecAcc;
	}
	public void setTelefonoResponsabileRespSecAcc(
			String telefonoResponsabileRespSecAcc) {
		this.telefonoResponsabileRespSecAcc = telefonoResponsabileRespSecAcc;
	}
	
	
	
	
	
	

}
