package it.altran.harraga.model.anagrafica;

import it.altran.harraga.model.Allegato;

public class Documento {
	
	private String tipologia;
	
	private Long id;
	private String note;
	private String descrizione;
	
	private Long idAllegato;
	private Allegato allegato;
	
	private Long idAnagrafica;
	
	public Documento() {}
	
	public Documento(String tipologia, String note, String descrizione, Allegato allegato) {
		super();
		this.tipologia = tipologia;
		this.note = note;
		this.descrizione = descrizione;
		this.allegato = allegato;
	}
	
	
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Long getIdAllegato() {
		return idAllegato;
	}

	public void setIdAllegato(Long idAllegato) {
		this.idAllegato = idAllegato;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Allegato getAllegato() {
		return allegato;
	}
	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public Long getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}	
	
	

}
