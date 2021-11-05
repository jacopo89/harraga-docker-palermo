package it.altran.harraga.model;

import java.io.Serializable;

public class Contatto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome, String email, String telefono) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
	}
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	
	
}
