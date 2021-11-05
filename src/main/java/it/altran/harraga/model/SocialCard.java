package it.altran.harraga.model;

import it.altran.harraga.model.amministrativa.Amministrativa;
import it.altran.harraga.model.anagrafica.Anagrafica;
import it.altran.harraga.model.competenze.Competenze;
import it.altran.harraga.model.desideri.Desideri;
import it.altran.harraga.model.istruzione.Istruzione;
import it.altran.harraga.model.lavoro.Lavoro;
import it.altran.harraga.model.penale.Penale;
import it.altran.harraga.model.sanitaria.Sanitaria;
import it.altran.harraga.model.sociale.Sociale;
import it.altran.harraga.model.storia.Storia;

import java.util.ArrayList;

public class SocialCard {

	private Long id;

	private String uploadFolder;

	private Anagrafica anagrafica;

	private Amministrativa amministrativa;

	private Storia storia;

	private Sanitaria sanitaria;

	private Istruzione istruzione;

	private ArrayList<Lavoro> lavoro;

	private Sociale sociale;

	private Competenze competenze;

	private Penale penale;

	private Desideri desideri;


	public SocialCard() {

	}


	public SocialCard(Long id) {
		super();
		this.id = id;
	}


	public SocialCard(Long id, Anagrafica anagrafica) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
	}

    public SocialCard(Long id, Anagrafica anagrafica, Amministrativa amministrativa, Competenze competenze, Desideri desideri, Istruzione istruzione, ArrayList<Lavoro> lavori, Penale penale,Sanitaria sanitaria, Sociale sociale, Storia storia) {
        super();
        this.id = id;
        this.anagrafica = anagrafica;
        this.amministrativa = amministrativa;
        this.competenze = competenze;
        this.desideri = desideri;
        this.istruzione = istruzione;
        this.lavoro = lavori;
        this.penale = penale;
        this.sanitaria = sanitaria;
        this.sociale = sociale;
        this.storia = storia;
    }




	public Long getId() {
		return id;
	}


	public void setId(Long long1) {
		this.id = long1;
	}


	public Anagrafica getAnagrafica() {
		return anagrafica;
	}


	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}


	public Amministrativa getAmministrativa() {
		return amministrativa;
	}

	public void setAmministrativa(Amministrativa amministrativa) {
		this.amministrativa = amministrativa;
	}

	public Storia getStoria() {
		return storia;
	}

	public void setStoria(Storia storia) {
		this.storia = storia;
	}

	public Sanitaria getSanitaria() {
		return sanitaria;
	}

	public void setSanitaria(Sanitaria sanitaria) {
		this.sanitaria = sanitaria;
	}



	public Istruzione getIstruzione() {
		return istruzione;
	}

	public void setIstruzione(Istruzione istruzione) {
		this.istruzione = istruzione;
	}

	public ArrayList<Lavoro> getLavoro() {
		return lavoro;
	}

	public void setLavoro(ArrayList<Lavoro> lavoro) {
		this.lavoro = lavoro;
	}


	public Competenze getCompetenze() {
		return competenze;
	}

	public void setCompetenze(Competenze competenze) {
		this.competenze = competenze;
	}


	public Sociale getSociale() {
		return sociale;
	}

	public void setSociale(Sociale sociale) {
		this.sociale = sociale;
	}

	public Penale getPenale() {
		return penale;
	}

	public void setPenale(Penale penale) {
		this.penale = penale;
	}

	public Desideri getDesideri() {
		return desideri;
	}

	public void setDesideri(Desideri desideri) {
		this.desideri = desideri;
	}

	public String getUploadFolder() {
		return uploadFolder;
	}


	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}



}
