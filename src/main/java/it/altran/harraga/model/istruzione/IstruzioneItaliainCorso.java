package it.altran.harraga.model.istruzione;

import it.altran.harraga.model.Allegato;

import java.util.ArrayList;

public class IstruzioneItaliainCorso {

	private String tipo;
	private String nome;
	private long inizio;
	private long fine;
	private String classeGruppo;
	private String orariGiorni;
	private String istituto;
	private String luogo;
	private Allegato pianoCPIA;
	private Allegato pattoCPIA;
	private Allegato progettoFormativo;
	private ArrayList<Allegato> valutazioni;
	
	
	public IstruzioneItaliainCorso() {}
	public IstruzioneItaliainCorso(String tipo, String nome, long inizio, long fine, String classeGruppo, String orariGiorni, String istituto, String luogo, Allegato pianoCPIA, Allegato pattoCPIA,
			Allegato progettoFormativo, ArrayList<Allegato> valutazioni) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.inizio = inizio;
		this.fine = fine;
		this.classeGruppo = classeGruppo;
		this.orariGiorni = orariGiorni;
		this.istituto = istituto;
		this.luogo = luogo;
		this.pianoCPIA = pianoCPIA;
		this.pattoCPIA = pattoCPIA;
		this.progettoFormativo = progettoFormativo;
		this.valutazioni = valutazioni;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getInizio() {
		return inizio;
	}
	public void setInizio(long inizio) {
		this.inizio = inizio;
	}
	public long getFine() {
		return fine;
	}
	public void setFine(long fine) {
		this.fine = fine;
	}
	public String getClasseGruppo() {
		return classeGruppo;
	}
	public void setClasseGruppo(String classeGruppo) {
		this.classeGruppo = classeGruppo;
	}
	public String getOrariGiorni() {
		return orariGiorni;
	}
	public void setOrariGiorni(String orariGiorni) {
		this.orariGiorni = orariGiorni;
	}
	public String getIstituto() {
		return istituto;
	}
	public void setIstituto(String istituto) {
		this.istituto = istituto;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public Allegato getPianoCPIA() {
		return pianoCPIA;
	}
	public void setPianoCPIA(Allegato pianoCPIA) {
		this.pianoCPIA = pianoCPIA;
	}
	public Allegato getPattoCPIA() {
		return pattoCPIA;
	}
	public void setPattoCPIA(Allegato pattoCPIA) {
		this.pattoCPIA = pattoCPIA;
	}
	public Allegato getProgettoFormativo() {
		return progettoFormativo;
	}
	public void setProgettoFormativo(Allegato progettoFormativo) {
		this.progettoFormativo = progettoFormativo;
	}
	public ArrayList<Allegato> getValutazioni() {
		return valutazioni;
	}
	public void setValutazioni(ArrayList<Allegato> valutazioni) {
		this.valutazioni = valutazioni;
	}
	
	

}
