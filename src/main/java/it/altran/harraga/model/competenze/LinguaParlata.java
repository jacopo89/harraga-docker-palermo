package it.altran.harraga.model.competenze;

import it.altran.harraga.model.Allegato;

public class LinguaParlata {
	private String nome;
	private String livelloScritto;
	private String livelloOrale;
	private Allegato allegatoLingua;
	
	
	public LinguaParlata() {}
	
	public LinguaParlata(String nome, String livelloScritto, String livelloOrale, Allegato allegatoLingua) {
		super();
		this.nome = nome;
		this.livelloScritto = livelloScritto;
		this.livelloOrale = livelloOrale;
		this.allegatoLingua = allegatoLingua;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLivelloScritto() {
		return livelloScritto;
	}

	public void setLivelloScritto(String livelloScritto) {
		this.livelloScritto = livelloScritto;
	}

	public String getLivelloOrale() {
		return livelloOrale;
	}

	public void setLivelloOrale(String livelloOrale) {
		this.livelloOrale = livelloOrale;
	}

	public Allegato getAllegatoLingua() {
		return allegatoLingua;
	}

	public void setAllegatoLingua(Allegato allegatoLingua) {
		this.allegatoLingua = allegatoLingua;
	}
	
	
	
	
}
