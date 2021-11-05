package it.altran.harraga.dao;

import it.altran.harraga.model.*;
import it.altran.harraga.model.amministrativa.*;
import it.altran.harraga.model.anagrafica.*;
import it.altran.harraga.model.competenze.CompetenzaDigitale;
import it.altran.harraga.model.competenze.Competenze;
import it.altran.harraga.model.competenze.LinguaParlata;
import it.altran.harraga.model.competenze.Patente;
import it.altran.harraga.model.desideri.Desideri;
import it.altran.harraga.model.istruzione.Istruzione;
import it.altran.harraga.model.istruzione.IstruzioneItaliaConculso;
import it.altran.harraga.model.istruzione.IstruzioneItaliainCorso;
import it.altran.harraga.model.istruzione.IstruzioneOrigine;
import it.altran.harraga.model.lavoro.Certificazione;
import it.altran.harraga.model.lavoro.Lavoro;
import it.altran.harraga.model.penale.Penale;
import it.altran.harraga.model.penale.ProcedimentoPenale;
import it.altran.harraga.model.sanitaria.*;
import it.altran.harraga.model.sociale.Esperienza;
import it.altran.harraga.model.sociale.Sociale;
import it.altran.harraga.model.storia.*;

import java.util.ArrayList;

public class DummySocialCardDB {

	static ArrayList<SocialCard> socialCardList = DummySocialCardDB.init();

	private static ArrayList<SocialCard> init() {

		// new Anagrafica(1, "Usain", "Doumbia", "flash", "M", "Giamaica", "Africana", date1,
		// "Africano, Francese",domicilio,assistenteSociale);

		Long socialCardId = 1l;
				
		SocialCard user1 = new SocialCard(socialCardId);

		
		user1.setAnagrafica(getDummyAnagrafica());


		// Anagrafica anag2 = new Anagrafica(2, "Seydou", "Bolt", "aslamih", "M", "Costa D'Avorio", "Svizzera", date2 , "Africano",domicilio,assistenteSociale);
		// SocialCard user2 = new SocialCard(anag2.getId(), anag2);

		ArrayList<SocialCard> userList = new ArrayList<SocialCard>();

		user1.setAmministrativa(getDummyAmministrativa());
		user1.setStoria(getDummyStoria());

		user1.setSanitaria(getDummySanitaria());

		user1.setIstruzione(getDummyIstruzione());

		user1.setLavoro(getDummyLavoro());

		user1.setCompetenze(getDummyCompetenze());

		user1.setSociale(getDummySociale());

		user1.setPenale(getDummyPenale());

		Desideri desideri = new Desideri(1l, "aspirazioni Professionali calciatore ecc", "ho anhce altre Aspettative");
		Desideri desideriHist = new Desideri(1l, "HISTaspirazioni Professionali calciatore ecc", "ho anhce altre Aspettative");
		user1.setDesideri(desideri);

		userList.add(user1);
		// userList.add(user2);

		return userList;
	}

	public static ArrayList<SocialCard> getAllSocialCard() {

		return socialCardList;

	}

	public static SocialCard getSocialCardById(int userId) {

		for (SocialCard u : socialCardList)
			if (u.getId() == userId)
				return u;
		return null;
	}

	public static boolean deleteUserById(int userId) {

		for (SocialCard u : socialCardList)
			if (u.getId() == userId) {
				socialCardList.remove(u);
				return true;
			}
		return false;
	}

	public static SocialCard updateUser(SocialCard user) {
		for (int i = 0; i < socialCardList.size(); i++)
			if (socialCardList.get(i).getId() == user.getId()) {
				// aggiorna socialCard esistente
				socialCardList.set(i, user);
				return user;
			}

		// inserisci nuova socialCard
		Long newId = Long.valueOf((int) (Math.random() * 100) + socialCardList.size());
		user.setId(newId);
		user.getAnagrafica().setId(newId);
		socialCardList.add(user);

		return user;
	}

	public static SocialCard createSocialCard(SocialCard socialCard) {
		// inserisci nuova socialCard
		Long newId = Long.valueOf((int) (Math.random() * 100) + socialCardList.size());
		socialCard.setId(newId);
		socialCard.getAnagrafica().setId(newId);
		socialCardList.add(socialCard);

		return socialCard;
	}

	public static Anagrafica updateAnagrafica(Long socialCardId, Anagrafica anagrafica) {
		for (int i = 0; i < socialCardList.size(); i++)
			if (socialCardList.get(i).getId() == socialCardId) {
				SocialCard sc = socialCardList.get(i);
				sc.setAnagrafica(anagrafica);
				// aggiorna Anagrafica
				socialCardList.set(i, sc);
				return sc.getAnagrafica();
			}

		return null;
	}

	public static ArrayList<SocialCard> getSocialCardListByUser(User user) {
		ArrayList<SocialCard> elencoSocialCard = new ArrayList<SocialCard>();
//		for (int i = 0; i < socialCardList.size(); i++)
//			if (user.getElencoCartelleAssociate().contains(socialCardList.get(i).getId())) {
//				elencoSocialCard.add(socialCardList.get(i));
//			}

		return elencoSocialCard;
	}

	public static Desideri getDesideriById(Long socialCardId) {

		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getDesideri();
		return null;
	}
	
	public static Desideri getDesideriById(Long socialCardId, long timestamp) {
		if(timestamp>0) {
			return new Desideri(1l, "HISTaspirazioni Professionali calciatore ecc", "HISTho anhce altre Aspettative");
		}
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getDesideri();
		return null;
	}

	public static Desideri updateDesideri(Long socialCardId, Desideri desideri) {
		for (int i = 0; i < socialCardList.size(); i++)
			if (socialCardList.get(i).getId() == socialCardId) {
				SocialCard sc = socialCardList.get(i);
				sc.setDesideri(desideri);
				// aggiorna Anagrafica
				socialCardList.set(i, sc);
				return sc.getDesideri();
			}
		return null;
	}
	
	
	public static Sanitaria updateSanitaria(Long socialCardId, Sanitaria sanitaria) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setSanitaria(sanitaria);
				// aggiorna Anagrafica
				socialCardList.set(i, sc);
				return sc.getSanitaria();
			}
		}
		return null;
	}
	
	
	public static Penale updatePenale(Long socialCardId, Penale penale) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setPenale(penale);
				socialCardList.set(i, sc);
				return sc.getPenale();
			}
		}
		return null;
	}
	
	
	public static Storia updateStoria(Long socialCardId, Storia storia) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setStoria(storia);
				socialCardList.set(i, sc);
				return sc.getStoria();
			}
		}
		return null;
	}
	
	public static Amministrativa updateAmministrativa(Long socialCardId, Amministrativa amministrativa) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setAmministrativa(amministrativa);
				socialCardList.set(i, sc);
				return sc.getAmministrativa();
			}
		}
		return null;
	}
	
	public static Istruzione updateIstruzione(Long socialCardId, Istruzione istruzione) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setIstruzione(istruzione);
				socialCardList.set(i, sc);
				return sc.getIstruzione();
			}
		}
		return null;
	}
	
	public static Sociale updateSociale(Long socialCardId, Sociale sociale) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setSociale(sociale);
				socialCardList.set(i, sc);
				return sc.getSociale();
			}
		}
		return null;
	}
	
	public static Competenze updateCompetenze(Long socialCardId, Competenze competenze) {
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setCompetenze(competenze);
				socialCardList.set(i, sc);
				return sc.getCompetenze();
			}
		}
		return null;
	}
	
	public static ArrayList<Lavoro> updateLavoro(Long socialCardId, ArrayList<Lavoro> lavoro) {	
		
		for(Lavoro lavItem : lavoro) {
			lavItem.setIdAnagrafica(socialCardId);
		}
			
			
		
		for (int i = 0; i < socialCardList.size(); i++) {
			if (socialCardList.get(i).getId() == socialCardId ) {
				SocialCard sc = socialCardList.get(i);
				sc.setLavoro(lavoro);
				// aggiorna Anagrafica
				socialCardList.set(i, sc);
				return sc.getLavoro();
			}
		}
		return null;
	}

	public static Anagrafica getAnagraficaById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getAnagrafica();
		return null;
	}

	public static Amministrativa getAmministrativaById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getAmministrativa();
		return null;
	}

	public static Storia getStoriaById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getStoria();
		return null;
	}

	public static Sanitaria getSanitariaById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getSanitaria();
		return null;
	}

	public static Istruzione getIstruzioneById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getIstruzione();
		return null;
	}

	public static ArrayList<Lavoro> getLavoroById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getLavoro();
		return null;
	}

	public static Competenze getCompetenzeById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getCompetenze();
		return null;
	}

	public static Sociale getSocialeById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getSociale();
		return null;
	}

	public static Penale getPenaleById(Long socialCardId) {
		for (SocialCard u : socialCardList)
			if (u.getId() == socialCardId)
				return u.getPenale();
		return null;
	}
	// JACOPO : CAMBIATI I METODI PERCHE' SOO VARIATI I COSTRUTTORI
	private static Anagrafica getDummyAnagrafica() {

		long dateMillis = 910000000000l;
		long dateMillis2 = 1510000000000l;

		ArrayList<Domicilio> domicilio = new ArrayList<Domicilio>();

		domicilio.add(new Domicilio("tipo", "nome", "tipoInserimento", "responsabile", "email", "telefono", dateMillis2));
		domicilio.add(new Domicilio("tipo2", "nome2", "tipoInserimento2", "responsabile2", "email2", "telefono2", dateMillis2));

		Contatto assistenteSociale = new Contatto("michele", "esposito", "mic@esp.sito", "333123123");

		Contatto mediatore = new Contatto("Media", "Tore", "med@ia.tore", "333221122");

		Tutore tutore = new Tutore(new Contatto("Tutnome", "Tutcognome", "Tutemail", "Tuttelefono"), "numeroDelega", dateMillis2, "luogoAssegnazione", "motivoTutela", "tribunaleMinori", "giudiceTutelare",
				"rettificaTutelare", new Allegato("RettificaTutelare", "anagrafica/RettificaTutelare.pdf", dateMillis2));

		Assicurazione assic = new Assicurazione("tipo", "numero", dateMillis, dateMillis, new Allegato("Polizza2017", "anagrafica/PolizzaAssicurativa.pdf", dateMillis2));

		ArrayList<Documento> documentList = new ArrayList<Documento>();
		Documento d1 = new Documento("tipologia documento","tipoDoc", "descrizione", new Allegato("Documento1", "anagrafica/Documento1.pdf", dateMillis2));
		Documento d2 = new Documento("tipologia documento","tipoDoc222", "descrizione222", new Allegato("Documento2", "anagrafica/Documento2.pdf", dateMillis2));
		Documento d3 = new Documento("tipologia documento","tipoDocPng", "descrizioneblablaPng", new Allegato("DocumentoPng", "anagrafica/doc.png", dateMillis2));
		documentList.add(d1);
		documentList.add(d2);
		documentList.add(d3);

		DocumentiAllArrivo documentiAllArrivo = new DocumentiAllArrivo("tipologia", new Allegato("DocumentoPng", "anagrafica/doc.png", dateMillis2) );

		Anagrafica anag1 = new Anagrafica(1l, "nome", "cognome", "altronome", "numeroTutela","Y", "alias", "M", "paese", "luogonascmita", "cittadinanza", dateMillis, dateMillis2, "linguaMadre", "altrelingue", "etnia", mediatore, dateMillis, "luogoArrivo", domicilio, "email", "telefono", documentList,
				"uONome", dateMillis, assistenteSociale, tutore, assic, documentiAllArrivo);

		return anag1;
	}

	private static Amministrativa getDummyAmministrativa() {
		long dateMillis = 913000030000l;


		ArrayList<PermessoSogiorno> permessoSoggiorno = new ArrayList<PermessoSogiorno>();
		permessoSoggiorno.add(new PermessoSogiorno("stato", "", dateMillis,  dateMillis, dateMillis, "rilasciatoDa", "tipologia", new Allegato("permessoSoggiorno", "amministrativa/permesso.pdf", dateMillis)));

		
		ArrayList<ProceduraLegale> procedureLegali = new ArrayList<ProceduraLegale>();
		
		Dublino dublino = new Dublino("belgio","belgio", 1, "valido", new Allegato("dublino", "amministrativa/dublino.pdf", dateMillis));
		
		ArrayList<Appuntamento> appuntamenti = new ArrayList<Appuntamento>();
		
		ArrayList<AllegatoAppuntamento> allegati = new ArrayList<AllegatoAppuntamento>();
		allegati.add(new AllegatoAppuntamento("", new Allegato("Appuntamento1", "amministrativa/Appuntamento1.pdf", dateMillis)));
		allegati.add(new AllegatoAppuntamento("", new Allegato("Appuntamento2", "amministrativa/Appuntamento2.pdf", dateMillis)));
		appuntamenti.add(new Appuntamento("luogo","altroLuogo", dateMillis, "motivo", "esito", allegati));
		
		ArrayList<Udienza> udienze = new ArrayList<Udienza>();
		Udienza udienza = new Udienza(dateMillis);
		udienze.add(udienza);
		
		
		ArrayList<RicorsoAmministrativo> ricorsiAmministrativi = new  ArrayList<RicorsoAmministrativo>();
		ArrayList<AllegatoRicorso> allegatiRicorso = new ArrayList<AllegatoRicorso>();
		allegatiRicorso.add(new AllegatoRicorso("",new Allegato("Ricorso1", "amministrativa/ricorso1.pdf", dateMillis)));
		allegatiRicorso.add(new AllegatoRicorso("",new Allegato("Ricorso2", "amministrativa/ricorso2.pdf", dateMillis)));
		
		ricorsiAmministrativi.add(new RicorsoAmministrativo(dateMillis, "avvocato dd", "gratuito", "patrocinio", udienze, allegatiRicorso));
		
		procedureLegali.add(new ProceduraLegale(dublino, appuntamenti, ricorsiAmministrativi));
		
		ArrayList<ProvvedimentoGiudiziario> provvedimentiGiudiziari = new ArrayList<ProvvedimentoGiudiziario>();
		ProvvedimentoGiudiziario pg1 = new ProvvedimentoGiudiziario(dateMillis, "tipo1", "istituzione1", "avvovato1", new Allegato("provvedimentoG1", "amministrativa/provvedimento1.pdf", dateMillis));
		ProvvedimentoGiudiziario pg2 = new ProvvedimentoGiudiziario(dateMillis, "tipo2", "istituzione2", "avvovato2", new Allegato("provvedimentoG2", "amministrativa/provvedimento2.pdf", dateMillis));
		provvedimentiGiudiziari.add(pg1);
		provvedimentiGiudiziari.add(pg2);
		
		
				
				
		RiferimentoLegale riferimentoLegale = new RiferimentoLegale("tipo", new Contatto("nomeRif","cognomeRif","sadas@sasd.it","123234243"));
		
		ArrayList<AllegatoDelega> allegatiDelega = new ArrayList<AllegatoDelega>();
		allegatiDelega.add(new AllegatoDelega ("", new Allegato("delega1", "amministrativa/delega1.pdf", dateMillis)));
		allegatiDelega.add(new AllegatoDelega ("",new Allegato("delega2", "amministrativa/delega2.pdf", dateMillis)));
		
		DelegaAmministrativa delegaAmministrativa = new DelegaAmministrativa(dateMillis, "protocollo", "richiestaUONI","altro", allegatiDelega);
		
		RevocaTutela revocaTutela = new RevocaTutela("motivazione", new Allegato("provvedimento", "amministrativa/provvedimento.pdf", dateMillis ));
		
		
		Proseguo21 proseguo21 = new Proseguo21(dateMillis, dateMillis,"note", new Allegato("Proseguo21", "amministrativa/proseguo21.pdf", dateMillis));
		PattoAccoglienza patto = new PattoAccoglienza(new Allegato("Proseguo21", "amministrativa/proseguo21.pdf", dateMillis));
		TesseraSanitaria tesseraSanitaria = new TesseraSanitaria(new Allegato("Proseguo21", "amministrativa/proseguo21.pdf", dateMillis));
		CodiceFiscale codiceFiscale = new CodiceFiscale(new Allegato("Proseguo21", "amministrativa/proseguo21.pdf", dateMillis));
		Stp stp = new Stp(new Allegato("Proseguo21", "amministrativa/proseguo21.pdf", dateMillis));

		ArrayList<DocumentoIdentita> documentoIdentitaArrayList = new ArrayList<DocumentoIdentita>();
		documentoIdentitaArrayList.add(new DocumentoIdentita(new Allegato("permessoSoggiorno", "amministrativa/permesso.pdf", dateMillis)));

		FotoSegnalazione fotoSegnalazione = new FotoSegnalazione(dateMillis, "ufficio", new Allegato("permessoSoggiorno", "amministrativa/permesso.pdf", dateMillis));

		AffidoAmministrativo affidoAmministrativo = new AffidoAmministrativo(dateMillis, "verbale", new Allegato("permessoSoggiorno", "amministrativa/permesso.pdf", dateMillis), dateMillis, "provvedimento", new Allegato("permessoSoggiorno", "amministrativa/permesso.pdf", dateMillis));

		Amministrativa amministrativa = new Amministrativa(permessoSoggiorno, procedureLegali, provvedimentiGiudiziari, riferimentoLegale, delegaAmministrativa, revocaTutela,patto, stp, tesseraSanitaria, codiceFiscale, documentoIdentitaArrayList, fotoSegnalazione, proseguo21, affidoAmministrativo);
		return amministrativa;
	}

	private static Storia getDummyStoria() {
		long dateMillis = 913000000000l;
		ArrayList<Familiare> familiari = new ArrayList<Familiare>();
		PercorsoMigratorio percorsoMigratorio = null;
		Accoglienza accoglienza = null;

		Contatto contattoPadre = new Contatto("nomePapa", "cognome", "email@sdfk.it", "2341342");
		Familiare padre = new Familiare("Padre", contattoPadre, true, "Italia", true, "statusGiuridico", "paeseOrigine", "note asdfafasd", new Allegato("padre", "storia/padre.pdf", dateMillis));
		familiari.add(padre);
		Contatto contattoMadre = new Contatto("nomeMamma", "cognomeMamma", "email@asfdasdfk.it", "111341342");
		Familiare madre = new Familiare("Madre", contattoMadre, true, "Germania", true, "statusGiuridico", "paeseOrigine", "note asdfafasd", new Allegato("madre", "storia/madre.pdf", dateMillis));
		familiari.add(madre);

		ArrayList<PaeseAttraversato> paesiAttraversati = new ArrayList<PaeseAttraversato>();
		paesiAttraversati.add(new PaeseAttraversato("libia", dateMillis, dateMillis, true, new Allegato("Visto1", "storia/VistoLibia.pdf", dateMillis)));
		paesiAttraversati.add(new PaeseAttraversato("Egitto", dateMillis, dateMillis, true, new Allegato("visto2", "storia/VistoEgitto.pdf", dateMillis)));
		percorsoMigratorio = new PercorsoMigratorio(1995l, "Marocco", "Marocco","Marocco", paesiAttraversati);

		ArrayList<AllontanamentiRitrovamenti> allontanamentiRitrovamenti = new ArrayList<AllontanamentiRitrovamenti>();
		allontanamentiRitrovamenti.add(new AllontanamentiRitrovamenti("allontanamento", dateMillis, "luogoAllont", new Allegato("allont-procura", "storia/allont-procura.pdf", dateMillis),"note"));
		allontanamentiRitrovamenti.add(new AllontanamentiRitrovamenti("ritrovamento", dateMillis, "luogoRitrov", new Allegato("ritrov", "storia/ritrov.pdf", dateMillis),"note"));

		
		
		ArrayList<PEI> pEI = new ArrayList<PEI>();
		pEI.add(new PEI("descPEI " , new Allegato("pei", "storia/pei.pdf", dateMillis)));
		
		ArrayList<Affido> affidi = new ArrayList<Affido>();
		Allegato allegatoAffido = new Allegato("allegatoAffido", "storia/allegatoAffido.pdf", dateMillis);
		affidi.add(new Affido(dateMillis, "ente", allegatoAffido));
		
		ArrayList<Adozione> adozioni = new ArrayList<Adozione>();
		Allegato allegatoAdozione = new Allegato("allegatoAdozione", "storia/allegatoAdozione.pdf", dateMillis);
		adozioni.add(new Adozione(dateMillis, "ente", allegatoAdozione));
		
		ArrayList<RelazioneAssistenteSociale> relazioniAssistenteSociale = new ArrayList<RelazioneAssistenteSociale>();
		Allegato allegatoAssistenteSociale = new Allegato("nome", "storia/relazioneAssistenteSociale.pdf", dateMillis);
		relazioniAssistenteSociale.add(new RelazioneAssistenteSociale("ente", allegatoAssistenteSociale));
		
		accoglienza = new Accoglienza("hotSpot", dateMillis, dateMillis, "cpaNome", dateMillis, dateMillis, "secondaAccoglienza", "secondaAccoglienzaTipo", "nome", "cognome","mail","telefono",dateMillis, dateMillis, pEI, allontanamentiRitrovamenti,affidi,
				adozioni , relazioniAssistenteSociale);

		StoriaPersonale storiaPersonale = new StoriaPersonale("testo", "testo" , "testo", "testo","testo", "testo");
		ValutazioneMultiDisciplinare valutazioneMultiDisciplinare = new ValutazioneMultiDisciplinare("test", "testp");

		Storia storia = new Storia("etniaRagazzo", "religione cristiana", familiari, percorsoMigratorio, accoglienza, storiaPersonale, "osservazioni", "piani", "diario", valutazioneMultiDisciplinare);
		return storia;
	}

	private static Sanitaria getDummySanitaria() {
		long dateMillis = 913000000000l;

		ArrayList<SpecificaDisabilita> specificheDisabilita = new ArrayList<SpecificaDisabilita>();

		specificheDisabilita.add(new SpecificaDisabilita("motoria", new Allegato("DisabilitaMotoria", "sanitaria/DisabilitaMotoria.pdf", dateMillis)));
		specificheDisabilita.add(new SpecificaDisabilita("visiva", new Allegato("DisabilitaVisiva", "sanitaria/DisabilitaVisiva.pdf", dateMillis)));

		ArrayList<PatologiaAllergica> patologieAllergiche = new ArrayList<PatologiaAllergica>();
		patologieAllergiche.add(new PatologiaAllergica("Allergia al Paracetamolo", new Allegato("AllergiaParacetamolo", "AllergiaParacetamolo.pdf", dateMillis)));

		Contatto contatto1 = new Contatto("NomeMedico", "CognomeMedico", "email@medico.it", "3432444233");

		MedicoCurante medicoCurante = new MedicoCurante(contatto1, new Allegato("Assegnazione Medico Curante", "sanitaria/AssegnazioneMedicoCurante.pdf", dateMillis));

		Contatto contatto = new Contatto("NomeServizio", "CognomeServizio", "email@medico.it", "3432444233");
		MedicoCurante presoInCarico = new MedicoCurante(contatto, new Allegato("Assegnazione Servizio", "sanitaria/PresaCaricoAltroServizio.pdf", dateMillis));
		//JACOPO: cambiato il contenuto di vaccini
		ArrayList<Vaccino> vaccini = new ArrayList<Vaccino>();
		ArrayList<EventoMedico> eventiMedici = new ArrayList<EventoMedico>();
		eventiMedici.add(new EventoMedico(dateMillis, "presidioOspedaliero1", "unitaOperativa3", contatto1, new Allegato("Visita Oculistica", "sanitaria/VisitaOculistica.pdf", dateMillis), "visita"));

		eventiMedici.add(new EventoMedico(dateMillis, "presidioOspedaliero2", "unitaOperativa4", contatto1, new Allegato("Esame Sangue", "sanitaria/EsameSangue.pdf", dateMillis), "esame"));
		
		Vaccino vaccino1 = new Vaccino("vaccino");
		Vaccino vaccino2 = new Vaccino("vaccinopolio");
		vaccini.add(vaccino1);
		vaccini.add(vaccino2);
		Sanitaria sanit = new Sanitaria(specificheDisabilita, patologieAllergiche, medicoCurante, presoInCarico, vaccini, eventiMedici);

		return sanit;

	}

	private static ArrayList<Lavoro> getDummyLavoro() {

		ArrayList<Lavoro> lavori = new ArrayList<Lavoro>();
		
		long dateMillis = 913000000000l;

		ArrayList<Certificazione> certificazioni = new ArrayList<Certificazione>();
		certificazioni.add(new Certificazione("sadf",new Allegato("cert1", "lavoro/cert1.pdf", dateMillis)));
		certificazioni.add(new Certificazione("safdfsda",new Allegato("cert2", "lavoro/cert2.pdf", dateMillis)));

		Lavoro lavoro = new Lavoro("tipo", "stato", "tipologia", "ambito", "inquadramento","orariGiorni", dateMillis, dateMillis, "nomeAzienda", "luogoAzienda", "emailAzienda", "telefonoAzienda", "competenzeAcquisite",
				certificazioni);

		ArrayList<Certificazione> certificazioni2 = new ArrayList<Certificazione>();
		certificazioni2.add(new Certificazione("safdfsda",new Allegato("cert3", "lavoro/cert3.pdf", dateMillis)));
		certificazioni2.add(new Certificazione("safdfsda",new Allegato("cert4", "lavoro/cert4.pdf", dateMillis)));

		Lavoro lavoro2 = new Lavoro("tipo2", "stato2", "tipologia2", "ambito2", "inquadramento2","orariGiorni", dateMillis, dateMillis, "nomeAzienda22", "luogoAzienda22", "emailAzienda22", "telefonoAzienda22", "competenzeAcquisite22",
				certificazioni2);
				
		lavori.add(lavoro);
		lavori.add(lavoro2);

		return lavori;

	}

	private static Competenze getDummyCompetenze() {

		long dateMillis = 913000000000l;

		ArrayList<LinguaParlata> lingueDichiarate = new ArrayList<LinguaParlata>();
		ArrayList<LinguaParlata> lingueAttestate = new ArrayList<LinguaParlata>();
		ArrayList<LinguaParlata> lingueCertificate = new ArrayList<LinguaParlata>();
		Allegato allegatoLingua = new Allegato("allegatoLingua", "competenze/allegatoLingua.pdf", dateMillis);
		ArrayList<CompetenzaDigitale> competenzeDigitali = new ArrayList<CompetenzaDigitale>();
		ArrayList<CompetenzaDigitale> altreCompetenze = new ArrayList<CompetenzaDigitale>();
		ArrayList<Patente> patenti = new ArrayList<Patente>();

		lingueDichiarate.add(new LinguaParlata("italiano", "base", "base",allegatoLingua));
		lingueDichiarate.add(new LinguaParlata("francese", "intermedio", "avanzato",allegatoLingua));
		lingueAttestate.add(new LinguaParlata("congolese", "base", "intermedio",allegatoLingua));
		lingueAttestate.add(new LinguaParlata("cinese", "intermedio", "base",allegatoLingua));
		lingueCertificate.add(new LinguaParlata("talebano", "intermedio", "base",allegatoLingua));
		lingueCertificate.add(new LinguaParlata("spagnolo", "base", "intermedio",allegatoLingua));
		
		Allegato certificazioneD = new Allegato("certificazione competenza digitale", "competenze/CertificazioneCompetenzaDigitale.pdf", dateMillis);

		competenzeDigitali.add(new CompetenzaDigitale("formale","attivitaSvolta", "intermedio", "nome", "descrizione", certificazioneD));
		
		Allegato certificazioneA = new Allegato("certificazione corso", "competenze/CertificazioneAltraCompetenza.pdf", dateMillis);
		altreCompetenze.add(new CompetenzaDigitale("formale","attivitaSvolta", "intermedio", "nome", "descrizione", certificazioneD));
		
		patenti.add(new Patente("Patente B",new Allegato("PatenteB", "competenze/patenteB.pdf", dateMillis)));
		
		Competenze competenze = new Competenze(lingueDichiarate, lingueAttestate, lingueCertificate , competenzeDigitali, altreCompetenze, patenti);

		return competenze;

	}

	private static Istruzione getDummyIstruzione() {

		long dateMillis = 913000000000l;

		ArrayList<IstruzioneOrigine> istruzioneOrigine = new ArrayList<IstruzioneOrigine>();
		ArrayList<IstruzioneItaliaConculso> istruzioneItaliaConculsi = new ArrayList<IstruzioneItaliaConculso>();
		ArrayList<IstruzioneItaliainCorso> istruzioneItaliainCorso = new ArrayList<IstruzioneItaliainCorso>();

		String saLeggere = "SI";
		String saScrivere = "Parzialmente";
		String anni = "3-6";
		String descrizione = "descrizione formale";
		
		istruzioneOrigine.add(new IstruzioneOrigine(saLeggere,saScrivere,"Formale",anni,descrizione ,"SI", new Allegato("certOrig", "certOrig.pdf", dateMillis)));
		istruzioneOrigine.add(new IstruzioneOrigine(saLeggere,saScrivere,"Informaleeeeeeeee", "" , "descrizione informale" ,"SI", new Allegato("certOrig", "certOrig.pdf", dateMillis)));
		
		
		istruzioneItaliaConculsi.add(new IstruzioneItaliaConculso("Formale", "corso di meccanica", dateMillis, dateMillis, "istituto di meccanica avanzata", new Allegato("corso Meccanica", "istruzione/corsoMecccanica.pdf", dateMillis)));
				
		Allegato pianoCPIA = new Allegato("pianoCPIA", "istruzione/pianoCPIA.pdf", dateMillis);
		Allegato pattoCPIA = new Allegato("pattoCPIA", "istruzione/pattoCPIA.pdf", dateMillis);
		Allegato progettoFormativo = new Allegato("progettoFormativo", "istruzione/progettoFormativo.pdf", dateMillis);
		ArrayList valutazioni = new ArrayList<Allegato>();
		Allegato valutazione = new Allegato("valutazione", "istruzione/valutazione.pdf", dateMillis);
		istruzioneItaliainCorso.add(new IstruzioneItaliainCorso("tipo", "nome", dateMillis, dateMillis, "classeGruppo", "orariGiorni", "istituto", "luogo", pianoCPIA, pattoCPIA, progettoFormativo, valutazioni));
		
		Istruzione istruzione = new Istruzione(istruzioneOrigine, istruzioneItaliaConculsi, istruzioneItaliainCorso);

		return istruzione;

	}

	private static Penale getDummyPenale() {

		long dateMillis = 913000000000l;

		Contatto assistenteSociale = new Contatto("nomeAssSoc", "cognomeAss", "email@ass.soc", "234123423142");

		
		Allegato allegato= new Allegato("pei", "penale/procedimentiPenali/pei.pdf", dateMillis);
		PEI pei = new PEI("descrizione", allegato);
		ArrayList<ProcedimentoPenale> procedimentiPenali = new ArrayList<ProcedimentoPenale>();
		
		ProcedimentoPenale procedimentoPenale = new ProcedimentoPenale("statoProcedimento",assistenteSociale, pei);
		procedimentiPenali.add(procedimentoPenale);
		Penale penale = new Penale(procedimentiPenali);

		return penale;

	}

	private static Sociale getDummySociale() {

		long dateMillis = 913000000000l;

		ArrayList<Esperienza> volontariato = new ArrayList<Esperienza>();
		ArrayList<Esperienza> laboratori = new ArrayList<Esperienza>();
		ArrayList<Esperienza> sport = new ArrayList<Esperienza>();
		
		Contatto referente = new Contatto("nomeRef", "cognomeRef", "email@referen.te", "234123423142");;
		Allegato certificazione1 = new Allegato("nome", "sociale/certVolont1.pdf", dateMillis);		
		volontariato.add(new Esperienza("incorso", "tipo1", dateMillis, dateMillis, "giorniOrari1", "competenzeAcquisite1", referente, certificazione1));
		
		Allegato certificazione2 = new Allegato("nome", "sociale/certVolont2.pdf", dateMillis);
		volontariato.add(new Esperienza("incorso", "tipo2", dateMillis, dateMillis, "giorniOrari2", "competenzeAcquisite2", referente, certificazione2));
		
		Allegato certificazione3 = new Allegato("nome", "sociale/certLab.pdf", dateMillis);
		laboratori.add(new Esperienza("incorso", "tipo2Lab", dateMillis, dateMillis, "giorniOrari2Lab", "competenzeAcquisiteLab", referente, certificazione3));
		
		Allegato certificazione4 = new Allegato("nome", "sociale/certSport.pdf", dateMillis);
		sport.add(new Esperienza("incorso", "sport", dateMillis, dateMillis, "giorniOrariSport", "competenzeAcquisiteSport", referente, certificazione4));
		
		
		ArrayList<Esperienza> gruppiAss = new ArrayList<Esperienza>();
		gruppiAss.add(new Esperienza("incorso", "tipo", dateMillis, dateMillis , "giorniOrari", "competenzeAcquisite", referente, null));
		
		Sociale Sociale = new Sociale(volontariato, laboratori, sport, gruppiAss);

		return Sociale;

	}

	

	

}
