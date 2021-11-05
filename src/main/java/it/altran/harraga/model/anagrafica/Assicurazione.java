package it.altran.harraga.model.anagrafica;

import it.altran.harraga.model.Allegato;

import java.io.Serializable;

public class Assicurazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String tipo;
	private String numero;
	private long dataInizio;
	private long dataFine;

	private Long idAnagrafica;
	
	private Long idAllegato;
	private Allegato allegato;

	public Assicurazione() {
	}

	public Assicurazione(String tipo, String numero, long dataInizio, long dataFine, Allegato allegato) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.allegato = allegato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

	public Long getIdAllegato() {
		return idAllegato;
	}

	public void setIdAllegato(Long idAllegato) {
		this.idAllegato = idAllegato;
	}

	public Long getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Long idAanagrafica) {
		this.idAnagrafica = idAanagrafica;
	}
}
