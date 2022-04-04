package it.altran.harraga.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HAR_USER")
//@AttributeOverrides({
//    @AttributeOverride(name = "nome", column=@Column(name = "USR_DES_NOME")),    
//})
public class User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USR_COD_ID")
	private Long id;
	
	@Column(name = "USR_DES_USERNAME")
	private String username;
	
	@Column(name = "USR_DES_PASSWORD")
	private String password;

	@Transient
	private String token;
	@Transient
	private int tokenLife = 2 * 60 * 60 * 1000; // 2 HOURS default

	@Column(name="USR_DES_TYPE") 
	@Enumerated(EnumType.STRING) 
	private Ruolo type;

	// 0: IN ATTESA, 1: ABILITATO, 2: RIFIUTATO
	@Column(name = "USR_NUM_STATO")
	private int stato = 0;
	
	@Column(name = "USR_NUM_MATRICOLA")
	private String matricola;
	
	@Column(name = "USR_NUM_POSTI")
	private int postiDisponibili = 0;

	
	@Column(name = "USR_DES_NOME")
	private String nome;
	
	@Column(name = "USR_DES_COGNOME")
	private String cognome;
	
	@Column(name = "USR_DES_EMAIL")
	private String email;
	
	@Column(name = "USR_DES_TELEFONO")
	private String telefono;
	
//	 @ElementCollection
//	 @CollectionTable(name="HAR_CARTASS", joinColumns=@JoinColumn(name="USR_DES_USERNAME"))
//	 @Column(name="CAR_NUM_CARDID")
	@Transient 
	private List<Integer> elencoCartelleAssociate;

	public User() {

	}

	public User(String username, String password, String token, int tokenLife, Ruolo type, int status, String nome, String cognome, String email, String telefono, String matricola, ArrayList<Integer> elencoCartelle,
			int postiDisp) {
//		super(nome, cognome, email, telefono);
		
		this.username = username;
		this.password = password;
		this.token = token;
		this.tokenLife = tokenLife;
		this.type = type;
		this.stato = status;
		this.matricola = matricola;
		this.elencoCartelleAssociate = elencoCartelle;
		this.postiDisponibili = postiDisp;
		
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

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	
	public void setElencoCartelleAssociate(List<Integer> elencoCartelleAssociate) {
		this.elencoCartelleAssociate = elencoCartelleAssociate;
	}
	public  List<Integer> getElencoCartelleAssociate() {
		return this.elencoCartelleAssociate;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Ruolo getType() {
		return type;
	}

	public void setType(Ruolo type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getTokenLife() {
		return tokenLife;
	}

	public void setTokenLife(int tokenLife) {
		this.tokenLife = tokenLife;
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

	public boolean isEnabled() {
		if (stato == 1)
			return true;
		return false;
	}

	public enum Ruolo {
		/*COMUNE_AGRIGENTO, AGENZIA_LAVORO, ASP, CPIA, GARANTE_INFANZIA, CPA, RespCom, SERVIZIO_SANITARIO, TUTORE*/
		CPA, RESP_SEC_ACC, TUTORE, COMUNE_AGRIGENTO, REF_LEGALE,
		ASP, CPIA, AGENZIA_LAVORO, ASSOCIAZIONI, USSM, SCUOLA_ITALIANO, GARANTE, MINORE,TRIBUNALE
	}
	
}
