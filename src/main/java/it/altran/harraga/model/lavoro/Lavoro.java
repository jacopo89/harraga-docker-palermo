package it.altran.harraga.model.lavoro;

import java.util.ArrayList;

public class Lavoro {
	
	private Long id;
	private Long idAnagrafica;
	

	private String tipo;
	private String stato;
	private String tipologia;
	private String ambito;
	private String inquadramento;
	private String orariGiorni;
	private long dataInizio;
	private long dataFine;
	private String nomeAzienda;
	private String luogoAzienda;
	private String emailAzienda;
	private String telefonoAzienda;
	private String competenzeAcquisite;
	private ArrayList<Certificazione> certificazioni;
	
	public Lavoro() {}
	public Lavoro(String tipo, String stato, String tipologia, String ambito, String inquadramento, String orariGiorni, long dataInizio, long dataFine, String nomeAzienda, String luogoAzienda, String emailAzienda, String telefonoAzienda,
			String competenzeAcquisite, ArrayList<Certificazione> certificazioni) {
		super();
		this.tipo = tipo;
		this.stato = stato;
		this.tipologia = tipologia;
		this.ambito = ambito;
		this.inquadramento = inquadramento;
		this.orariGiorni = orariGiorni;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.nomeAzienda = nomeAzienda;
		this.luogoAzienda = luogoAzienda;
		this.emailAzienda = emailAzienda;
		this.telefonoAzienda = telefonoAzienda;
		this.competenzeAcquisite = competenzeAcquisite;
		this.certificazioni = certificazioni;
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdAnagrafica() {
		return idAnagrafica;
	}
	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getInquadramento() {
		return inquadramento;
	}
	public void setInquadramento(String inquadramento) {
		this.inquadramento = inquadramento;
	}
	public long getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(long dataInizio) {
		this.dataInizio = dataInizio;
	}
	public long getDataFine() {
		return dataFine;
	}
	public void setDataFine(long dataFine) {
		this.dataFine = dataFine;
	}
	public String getNomeAzienda() {
		return nomeAzienda;
	}
	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	public String getLuogoAzienda() {
		return luogoAzienda;
	}
	public void setLuogoAzienda(String luogoAzienda) {
		this.luogoAzienda = luogoAzienda;
	}
	public String getEmailAzienda() {
		return emailAzienda;
	}
	public void setEmailAzienda(String emailAzienda) {
		this.emailAzienda = emailAzienda;
	}
	public String getTelefonoAzienda() {
		return telefonoAzienda;
	}
	public void setTelefonoAzienda(String telefonoAzienda) {
		this.telefonoAzienda = telefonoAzienda;
	}
	public String getCompetenzeAcquisite() {
		return competenzeAcquisite;
	}
	public void setCompetenzeAcquisite(String competenzeAcquisite) {
		this.competenzeAcquisite = competenzeAcquisite;
	}
	public ArrayList<Certificazione> getCertificazioni() {
		return certificazioni;
	}
	public void setCertificazioni(ArrayList<Certificazione> certificazioni) {
		this.certificazioni = certificazioni;
	}
	public String getOrariGiorni() {
		return orariGiorni;
	}
	public void setOrariGiorni(String orariGiorni) {
		this.orariGiorni = orariGiorni;
	}
	
	
	

}
