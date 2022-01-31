package it.altran.harraga.model.anagrafica;

import com.google.gson.annotations.SerializedName;
import it.altran.harraga.model.Contatto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 *
 */
public class Anagrafica implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String nome;
	private String cognome;
	private String othername;
	private String alias;
	private String sesso;
	private String paese;
	private String luogoNascita;
	private String cittadinanza;
	private Long dataNascitaDichiarata;	
	private Long dataNascitaCorretta;	
	// JACOPO : aggiunto numero tutela con setter e getter e italiano con setter e getter
	private String numeroTutela;
	private String italiano;
	private String linguaMadre;
	private String altreLingue;

	private String gruppoEtnico;

	private Long dataArrivo;
	private String luogoArrivo;
	private String email;
	private String telefono;
	private String UONome;
	private Long UOData;
	
	
	private Long idMediatore;
	
	private Contatto mediatore;
	
	private  List<Domicilio> domicilio;

	private List<Documento> documenti;
	
	private Long idAssSoc;
	private Contatto assistenteSociale;
	
	private Tutore tutore;
	
	private Assicurazione assicurazione;

	private DocumentiAllArrivo documentiAllArrivo;
	
	
	public Anagrafica() {
		
	}
	
	
	
	
	
	
	
//	public Anagrafica(int id, String name, String cognome, String alias, String sesso, String paese, String cittadinanza,
//			Date dataNascita, String linguaMadre, ArrayList<Residenza> domicilio, Contatto assistenteSociale) {
//		super();
//		this.id = id;
//		this.nome = name;
//		this.cognome = cognome;
//		this.alias = alias;
//		this.sesso = sesso;
//		this.paese = paese;
//		this.cittadinanza = cittadinanza;
//		this.dataNascita = dataNascita;
//		this.linguaMadre = linguaMadre;
//		this.domicilio = domicilio;
//		this.assistenteSociale = assistenteSociale;
//	}
	
	
	public Long getIdMediatore() {
		return idMediatore;
	}

	public void setIdMediatore(Long idMediatore) {
		this.idMediatore = idMediatore;
	}




	public Long getIdAssSoc() {
		return idAssSoc;
	}

	public void setIdAssSoc(Long idAssSoc) {
		this.idAssSoc = idAssSoc;
	}





	

	public Anagrafica(Long id, String nome, String cognome, String othername, String numeroTutela, String italiano, String alias, String sesso, String paese,
			String luogoNascita,
			String cittadinanza, Long dataNascitaDic, Long dataNascitaCorretta, String linguaMadre, String altreLingue,
					  String gruppoEtnico,
					  Contatto mediatore, Long dataArrivo,
			String luogoArrivo, ArrayList<Domicilio> domicilio, String email, String telefono,
			ArrayList<Documento> documenti, String uONome, Long uOData, Contatto assistenteSociale, Tutore tutore,
			Assicurazione assicurazione, DocumentiAllArrivo documentiAllArrivo ) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.othername = othername;
		this.numeroTutela = numeroTutela;
		this.italiano = italiano;
		this.alias = alias;
		this.sesso = sesso;
		this.paese = paese;
		this.luogoNascita = luogoNascita;
		this.cittadinanza = cittadinanza;
		this.dataNascitaDichiarata = dataNascitaDic;
		this.linguaMadre = linguaMadre;
		this.altreLingue = altreLingue;
		this.gruppoEtnico = gruppoEtnico;
		this.mediatore = mediatore;
		this.dataArrivo = dataArrivo;
		this.luogoArrivo = luogoArrivo;
		this.domicilio = domicilio;
		this.email = email;
		this.telefono = telefono;
		this.documenti = documenti;
		UONome = uONome;
		UOData = uOData;
		this.assistenteSociale = assistenteSociale;
		this.tutore = tutore;
		this.assicurazione = assicurazione;
		this.dataNascitaCorretta = dataNascitaCorretta;
		this.documentiAllArrivo = documentiAllArrivo;
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
	public void setNome(String name) {
		this.nome = name;
	}
	public String getOthername() {
		return othername;
	}
	public void setOthername(String othername) {
		this.othername = othername;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNumeroTutela() {
		return numeroTutela;
	}

	public void setNumeroTutela(String numeroTutela) {
		this.numeroTutela = numeroTutela;
	}

	public String getItaliano() {
		return italiano;
	}

	public void setItaliano(String italiano) {
		this.italiano = italiano;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getCittadinanza() {
		return cittadinanza;
	}
	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
	}
	public Long getDataNascitaDichiarata() {
		return dataNascitaDichiarata;
	}
	public void setDataNascitaDichiarata(Long dataNascita) {
		this.dataNascitaDichiarata = dataNascita;
	}
		
	public Long getDataNascitaCorretta() {
		return dataNascitaCorretta;
	}

	public void setDataNascitaCorretta(Long dataNascitaCorretta) {
		this.dataNascitaCorretta = dataNascitaCorretta;
	}

	public void setAltreLingue(String altreLingue) {
		this.altreLingue = altreLingue;
	}

	public String getAltreLingue() {
		return altreLingue;
	}

	public String getGruppoEtnico() {
		return gruppoEtnico;
	}

	public void setGruppoEtnico(String gruppoEtnico) {
		this.gruppoEtnico = gruppoEtnico;
	}

	public String getLinguaMadre() {
		return linguaMadre;
	}
	public void setLinguaMadre(String linguaMadre) {
		this.linguaMadre = linguaMadre;
	}
	

	public Contatto getMediatore() {
		return mediatore;
	}

	public void setMediatore(Contatto mediatore) {
		this.mediatore = mediatore;
	}

	public Long getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Long dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public String getLuogoArrivo() {
		return luogoArrivo;
	}

	public void setLuogoArrivo(String luogoArrivo) {
		this.luogoArrivo = luogoArrivo;
	}

	public List<Domicilio> getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(List<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}

	
	public void setDomicilio(ArrayList<Domicilio> domicilio) {
		this.domicilio = domicilio;
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

	public List<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(ArrayList<Documento> documenti) {
		this.documenti = documenti;
	}
	
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}

	public String getUONome() {
		return UONome;
	}

	public void setUONome(String uONome) {
		UONome = uONome;
	}

	public Long getUOData() {
		return UOData;
	}

	public void setUOData(Long uOData) {
		UOData = uOData;
	}

	public Contatto getAssistenteSociale() {
		return assistenteSociale;
	}

	public void setAssistenteSociale(Contatto assistenteSociale) {
		this.assistenteSociale = assistenteSociale;
	}

	public Tutore getTutore() {
		return tutore;
	}

	public void setTutore(Tutore tutore) {
		this.tutore = tutore;
	}

	public Assicurazione getAssicurazione() {
		return assicurazione;
	}

	public void setAssicurazione(Assicurazione assicurazione) {
		this.assicurazione = assicurazione;
	}

	public void setDocumentiAllArrivo(DocumentiAllArrivo documentiAllArrivo) {
		this.documentiAllArrivo = documentiAllArrivo;
	}

	public DocumentiAllArrivo getDocumentiAllArrivo() {
		return documentiAllArrivo;
	}

	@Override
	public String toString(){
		return String.format("Name %s, Surname %s", this.nome, this.cognome);
	}
}
