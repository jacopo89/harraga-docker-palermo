package it.altran.harraga.model.anagrafica;

import java.io.Serializable;

public class Domicilio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	//JACOPO : AGGIUNTO TEMPO PERMANENZA, SET E GET E CAMBIO COSTRUTTORE
	private String tipo;
	private String nome;
	private String tipoInserimento;
	private String responsabile;
	private String email;
	private long tempoPermanenza;
	private String telefono;
	
	private Long idAnagrafica;
	
	
	public Domicilio() {}
	
	
	public Domicilio(String tipo, String nome, String tipoInserimento, String responsabile, String email,
			String telefono, long tempoPermanenza) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.tipoInserimento = tipoInserimento;
		this.responsabile = responsabile;
		this.email = email;
		this.telefono = telefono;
		this.tempoPermanenza = tempoPermanenza;
	}
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
	public String getTipoInserimento() {
		return tipoInserimento;
	}
	public void setTipoInserimento(String tipoInserimento) {
		this.tipoInserimento = tipoInserimento;
	}
	public String getResponsabile() {
		return responsabile;
	}
	public void setResponsabile(String responsabile) {
		this.responsabile = responsabile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Long getIdAnagrafica() {
		return idAnagrafica;
	}


	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}


	public long getTempoPermanenza() {
		return tempoPermanenza;
	}


	public void setTempoPermanenza(long tempoPermanenza) {
		this.tempoPermanenza = tempoPermanenza;
	}
	
	
	

}
