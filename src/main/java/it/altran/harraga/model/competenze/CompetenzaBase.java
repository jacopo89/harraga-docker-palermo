package it.altran.harraga.model.competenze;

import it.altran.harraga.model.Allegato;

public class CompetenzaBase {

	private String nome;
	private String descrizione;
	private Allegato certificazione;
	
	public CompetenzaBase() {}
	public CompetenzaBase(String nome, String descrizione, Allegato certificazione) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.certificazione = certificazione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Allegato getCertificazione() {
		return certificazione;
	}
	public void setCertificazione(Allegato certificazione) {
		this.certificazione = certificazione;
	}
	
	
	
	
	
}
