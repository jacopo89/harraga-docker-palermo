package it.altran.harraga.model;

import java.io.Serializable;

public class Allegato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private String link;
	private Long data;
	private String encodedFile;

	public Allegato() {
	}

	public Allegato(String nome, String link, Long data) {
		super();
		this.nome = nome;
		this.link = link;
		this.data = data;
	}

	public Allegato(String nome, String link, Long data, String encodedFile) {
		super();
		this.nome = nome;
		this.link = link;
		this.data = data;
		this.encodedFile = encodedFile;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public String getEncodedFile() {
		return encodedFile;
	}

	public void setEncodedFile(String encoredFile) {
		this.encodedFile = encoredFile;
	}

}
