package it.altran.harraga.model.amministrativa;

import java.util.ArrayList;

public class Amministrativa {

	private ArrayList<PermessoSogiorno> permessoSoggiorno;
	private ArrayList<ProceduraLegale> procedureLegali;
	private ArrayList<ProvvedimentoGiudiziario> provvedimentiGiudiziari;
	private RiferimentoLegale riferimentoLegale;
	private DelegaAmministrativa delegaAmministrativa;
	private RevocaTutela revocaTutela;
	private PattoAccoglienza pattoAccoglienza;
	private AffidoAmministrativo affidoAmministrativo;
	private Stp stp;
	private TesseraSanitaria tesseraSanitaria;
	private CodiceFiscale codiceFiscale;
	private FotoSegnalazione fotoSegnalazione;
	private ArrayList<DocumentoIdentita> documentiIdentita;
	private Proseguo21 proseguo21;
	
	
	
	
	public Amministrativa() {}
	public Amministrativa(ArrayList<PermessoSogiorno> permessoSoggiorno, ArrayList<ProceduraLegale> procedureLegali, ArrayList<ProvvedimentoGiudiziario> provvedimentiGiudiziari, RiferimentoLegale riferimentoLegale,
			DelegaAmministrativa delegaAmministrativa, RevocaTutela revocaTutela, PattoAccoglienza pattoAccoglienza, Stp stp, TesseraSanitaria tesseraSanitaria, CodiceFiscale codiceFiscale, ArrayList<DocumentoIdentita> documentiIdentita, FotoSegnalazione fotoSegnalazione, Proseguo21 proseguo21, AffidoAmministrativo affidoAmministrativo) {
		super();
		this.permessoSoggiorno = permessoSoggiorno;
		this.procedureLegali = procedureLegali;
		this.provvedimentiGiudiziari = provvedimentiGiudiziari;
		this.riferimentoLegale = riferimentoLegale;
		this.delegaAmministrativa = delegaAmministrativa;
		this.revocaTutela = revocaTutela;
		this.proseguo21 = proseguo21;
		this.pattoAccoglienza = pattoAccoglienza;
		this.stp = stp;
		this.tesseraSanitaria = tesseraSanitaria;
		this.codiceFiscale = codiceFiscale;
		this.documentiIdentita = documentiIdentita;
		this.fotoSegnalazione  = fotoSegnalazione;
		this.affidoAmministrativo = affidoAmministrativo;
	}
	public ArrayList<PermessoSogiorno> getPermessoSoggiorno() {
		return permessoSoggiorno;
	}
	public void setPermessoSoggiorno(ArrayList<PermessoSogiorno> permessoSoggiorno) {
		this.permessoSoggiorno = permessoSoggiorno;
	}
	public ArrayList<ProceduraLegale> getProcedureLegali() {
		return procedureLegali;
	}
	public void setProcedureLegali(ArrayList<ProceduraLegale> procedureLegali) {
		this.procedureLegali = procedureLegali;
	}
	public ArrayList<ProvvedimentoGiudiziario> getProvvedimentiGiudiziari() {
		return provvedimentiGiudiziari;
	}
	public void setProvvedimentiGiudiziari(ArrayList<ProvvedimentoGiudiziario> provvedimentiGiudiziari) {
		this.provvedimentiGiudiziari = provvedimentiGiudiziari;
	}
	public RiferimentoLegale getRiferimentoLegale() {
		return riferimentoLegale;
	}
	public void setRiferimentoLegale(RiferimentoLegale riferimentoLegale) {
		this.riferimentoLegale = riferimentoLegale;
	}
	public DelegaAmministrativa getDelegaAmministrativa() {
		return delegaAmministrativa;
	}
	public void setDelegaAmministrativa(DelegaAmministrativa delegaAmministrativa) {
		this.delegaAmministrativa = delegaAmministrativa;
	}
	public RevocaTutela getRevocaTutela() {
		return revocaTutela;
	}
	public void setRevocaTutela(RevocaTutela revocaTutela) {
		this.revocaTutela = revocaTutela;
	}
	public Proseguo21 getProseguo21() {
		return proseguo21;
	}
	public void setProseguo21(Proseguo21 proseguo21) {
		this.proseguo21 = proseguo21;
	}

	public PattoAccoglienza getPattoAccoglienza() {
		return pattoAccoglienza;
	}

	public void setPattoAccoglienza(PattoAccoglienza pattoAccoglienza) {
		this.pattoAccoglienza = pattoAccoglienza;
	}

	public CodiceFiscale getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(CodiceFiscale codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public ArrayList<DocumentoIdentita> getDocumentiIdentita() {
		return documentiIdentita;
	}

	public void setDocumentiIdentita(ArrayList<DocumentoIdentita> documentiIdentita) {
		this.documentiIdentita = documentiIdentita;
	}

	public void setStp(Stp stp) {
		this.stp = stp;
	}

	public Stp getStp() {
		return stp;
	}

	public TesseraSanitaria getTesseraSanitaria() {
		return tesseraSanitaria;
	}

	public void setTesseraSanitaria(TesseraSanitaria tesseraSanitaria) {
		this.tesseraSanitaria = tesseraSanitaria;
	}

	public FotoSegnalazione getFotoSegnalazione() {
		return fotoSegnalazione;
	}

	public void setFotoSegnalazione(FotoSegnalazione fotoSegnalazione) {
		this.fotoSegnalazione = fotoSegnalazione;
	}

	public AffidoAmministrativo getAffidoAmministrativo() {
		return affidoAmministrativo;
	}

	public void setAffidoAmministrativo(AffidoAmministrativo affidoAmministrativo) {
		this.affidoAmministrativo = affidoAmministrativo;
	}
}
