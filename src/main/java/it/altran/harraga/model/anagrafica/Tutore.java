package it.altran.harraga.model.anagrafica;

import it.altran.harraga.model.Allegato;
import it.altran.harraga.model.Contatto;

public class Tutore {

	Integer id;

	Long idContatto;
	Long idAllegato;
	Long idAnagrafica;

	private Contatto contatto;
	private String numeroDelega;
	private long dataAssegnazione;
	private String luogoAssegnazione;
	private String motivoTutela;
	private String tribunaleMinori;
	private String giudiceTutelare;
	private String rettificaTutelare;
	private Allegato decretoTribunale;

	public Tutore() {
	}

	public Tutore(Contatto contatto, String numeroDelega, long dataAssegnazione, String luogoAssegnazione, String motivoTutela, String tribunaleMinori, String giudiceTutelare, String rettificaTutelare,
			Allegato decretoTribunale) {
		super();
		this.contatto = contatto;
		this.numeroDelega = numeroDelega;
		this.dataAssegnazione = dataAssegnazione;
		this.luogoAssegnazione = luogoAssegnazione;
		this.motivoTutela = motivoTutela;
		this.tribunaleMinori = tribunaleMinori;
		this.giudiceTutelare = giudiceTutelare;
		this.rettificaTutelare = rettificaTutelare;
		this.decretoTribunale = decretoTribunale;
	}

	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}

	public String getNumeroDelega() {
		return numeroDelega;
	}

	public void setNumeroDelega(String numeroDelega) {
		this.numeroDelega = numeroDelega;
	}

	public long getDataAssegnazione() {
		return dataAssegnazione;
	}

	public void setDataAssegnazione(long dataAssegnazione) {
		this.dataAssegnazione = dataAssegnazione;
	}

	public String getLuogoAssegnazione() {
		return luogoAssegnazione;
	}

	public void setLuogoAssegnazione(String luogoAssegnazione) {
		this.luogoAssegnazione = luogoAssegnazione;
	}

	public String getMotivoTutela() {
		return motivoTutela;
	}

	public void setMotivoTutela(String motivoTutela) {
		this.motivoTutela = motivoTutela;
	}

	public String getTribunaleMinori() {
		return tribunaleMinori;
	}

	public void setTribunaleMinori(String tribunaleMinori) {
		this.tribunaleMinori = tribunaleMinori;
	}

	public String getGiudiceTutelare() {
		return giudiceTutelare;
	}

	public void setGiudiceTutelare(String giudiceTutelare) {
		this.giudiceTutelare = giudiceTutelare;
	}

	public String getRettificaTutelare() {
		return rettificaTutelare;
	}

	public void setRettificaTutelare(String rettificaTutelare) {
		this.rettificaTutelare = rettificaTutelare;
	}

	public Allegato getDecretoTribunale() {
		return decretoTribunale;
	}

	public void setDecretoTribunale(Allegato decretoTribunale) {
		this.decretoTribunale = decretoTribunale;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getIdContatto() {
		return idContatto;
	}

	public void setIdContatto(Long idContatto) {
		this.idContatto = idContatto;
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

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	

}
