'use strict';

// Declare app level module which depends on filters, and services
angular.module('harragapp',
		[	'ngRoute',
			'ngMaterial',
			'ngMessages',
			'harragapp.filters',
			'harragapp.services',
			'harragapp.userServices',
			'harragapp.socialCardServices',
			'harragapp.socialCardHistoryServices',
			'harragapp.directives',
			'harragapp.controllers',
			'harragapp.socialCardListController',
			'harragapp.socialCardDetailController',
			'harragapp.socialCardEditController',
			'harragapp.socialCardCreateController',
			'harragapp.socialCardImportController',
			'pascalprecht.translate',
			'ngStorage']).


config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'});
        $routeProvider.when('/social-card-list', {templateUrl: 'partials/social-card-list.html', controller: 'SocialCardListCtrl'});
        $routeProvider.when('/', {templateUrl: 'partials/social-card-list.html', controller: 'SocialCardListCtrl'});
        $routeProvider.when('/social-card-detail/:id', {templateUrl: 'partials/social-card-detail.html', controller: 'SocialCardDetailCtrl'});
		$routeProvider.when('/social-card-edit/:id', {templateUrl: 'partials/social-card-edit.html', controller: 'SocialCardEditCtrl'});
        $routeProvider.when('/social-card-creation', {templateUrl: 'partials/social-card-creation.html', controller: 'SocialCardCreationCtrl'});
        $routeProvider.when('/social-card-import', {templateUrl: 'partials/social-card-import.html', controller: 'SocialCardImportCtrl'});
        $routeProvider.when('/user-list', {templateUrl: 'partials/user/user-list.html', controller: 'UserListCtrl'});
        $routeProvider.when('/user-profile/:username', {templateUrl: 'partials/user/user-profile.html', controller: 'UserCtrl'});
        // JACOPO : aggiunta la route
        $routeProvider.when('/filtri-list', {templateUrl: 'partials/user/migranti-list.html', controller: 'ItalianiListCtrl'});
        $routeProvider.when('/maggiorenni-list', {templateUrl: 'partials/user/maggiorenni-list.html', controller: 'MaggiorenniListCtrl'});
        $routeProvider.otherwise({redirectTo: '/login'});
    }])
	.config(function ($translateProvider) {
  $translateProvider.translations('it', {
	  // USER DATA LABEL
	  repeat_password: "ripeti password",


	//SOCIAL CARD LIST
	  SOCIALE:"SOCIALITA'",
	  mostraStranieri:'Mostra Migranti',
	  mostraMaggiorenni:'Mostra Maggiorenni',
	  paeseList:"Paese",
	  cittadinanzaList:"Cittad.",
	  linguaMadreList:"Lingua",

	  // SOCIAL CARD LABEL
    name: 'Nome',
    nome: 'Nome',
    numeroTutela:"Numero Tutela",
    othername: "Altro nome",
	cognome: "Cognome",
	alias: "Alias",
	sesso: "Sesso",
	paese: "Paese d'origine",
	luogoNascita: "Luogo di nascita",
	cittadinanza: "Cittadinanza",
	dataNascita: "Data di nascita (dichiarata/corretta)",
	linguaMadre: "Lingua madre",
	altreLingue: "Altre lingue/dialetti parlati",
	gruppoEtnico: "Gruppo etnico di appartenenza",
	bill: "Polizza assicurativa",
	gestisciUtenti :'Gestisci utenti',
	exportJSON :'Esporta in JSON',
	importSocialCard :'Importa in CSV',
	dataNascitaDichiarata : 'Data di nascita - Prima identificazione',
	dataNascitaCorretta : 'Data di nascita - Corretta',
	dataArrivo : 'Data di arrivo in Italia',
	luogoArrivo : 'Luogo di arrivo in Italia',
	documentiAllArrivo: "Documenti in possesso all'arrivo",
	email : 'E-mail',
	telefono : 'Telefono',
	UONome : 'Unità Operativa - UO',
	UOData : 'Data di assegnazione UO',
	ElencoDomicilio : 'Domicilio/Accoglienza',
	ElencoDocumenti : 'Documenti di identità',
	numeroDelega : 'Numero di tutela',
	permessoSoggiorno : 'Permesso di soggiorno',
	dataScadenza : 'Data di scadenza',
	tipologia : 'Tipologia',
	eliminaPermesso : 'Elimina questo permesso',
	aggiungiPermesso : 'Aggiungi un permesso',
	procedureLegali : 'Procedure legali - amministrative',
	aggiungiProceduraLegale : 'Aggiungi una Procedura legale',
	aggiungiAppuntamento : 'Aggiungi un appuntamento',
	aggiungiAllegatoAppuntamento : 'Aggiungi un allegato per questo appuntamento',
	eliminaAppuntamento : 'Elimina questo appuntamento',
	ricorsiAmministrativi : 'Ricorsi amministrativi',
	aggiungiRicorso : 'Aggiungi un ricorso',
	eliminaRicorso : 'Elimina questo ricorso',
	allegatiRicorso : 'Allegati per il ricorso',
	aggiungiAllegatoRicorso : 'Aggiungi un allegato al ricorso',
	riferimentoLegale : 'Riferimento legale',
	delegaAmministrativa : 'Delega amministrativa',
	allegatiDelega : 'Allegati per la delega',
	aggiungiAllegatoDelega : 'aggiungi un allegato per la delega',
	revocaTutela : 'Revoca della tutela',
	proseguo21 : 'Proseguimento amministrativo',
	religione : 'Particolari osservanze',
	familiari : 'Famiglia di origine',
	exportPDF : 'Esporta in PDF',
	exportFolder : 'Esporta in Zip',
	user_type : 'Profilo',
	username : 'Username',
	password : 'Scegli una password',

	// BUTTONS & PAGE_LABEL
	welcome_message: "Ragazzi Harraga",
	login_message: "Accedi",
	register_message: "Registrati",
	show: "Mostra",
	edit: "Modifica",
	editSocialCard: "Modifica Cartella Sociale",
	delete : "Elimina",
	create: "Crea nuova",
	cancel: "Annulla",
	apply: "Salva",
	cartella_sociale: "Cartella Sociale",
	socialCardList: "Elenco Cartelle Sociali",
	socialCardListStranieri: "Elenco Cartelle Sociali Migranti",
	socialCardListMaggiorenni: "Elenco Cartelle Sociali Maggiorenni",
	createSocialCard: "Nuova Cartella Sociale",
	SAVE_HISTORY: 'Elenco Modifiche',
	true: "SI",
	false: "NO",
	//JACOPO : NUOVE DEFINIZIONI

	// ANAGRAFICA
	italiano: "Italiano",
	dataAssegnazione: "Data Assegnazione",
	Mediatore :"Mediatore linguistico/culturale",
	luogoAssegnazione: "Luogo Assegnazione",
	motivoTutela: "Motivazione della tutela",
	tribunaleMinori:"Tribunale dei minori",
	giudiceTutelare: "Giudice tutelare",
	rettificaTutelare: "Rettifica tutela",
	decretoTribunale: "Decreto Tribunale",
	Assicurazione: "Polizza assicurativa",
	dataInizio: "Data inizio",
	dataFine: "Data fine",
	daPaese: "Da quale paese",
	versoPaese:"Verso quale paese",
	data:"Data",
	luogo:"Luogo",
	tipoInserimento:"Tipo di inserimento",
	tempoPermanenza:"Tempo di permanenza previsto fino al",
	responsabile:"Responsabile",
	AssistenteSociale:"Assistente Sociale",

	// AMMINISTRATIVA
	Dublino:"Regolamento di Dublino",
	provvedimentiGiudiziari:"Provvedimenti Giudiziari",
	istituzioneEmittente:"Istituzione Emittente",
	stato: "Stato",
	statoProcedura : "Stato procedura",
	motivo:"Motivo",
	esito:"Esiti",
	avvocato: "Nome dell'avvocato",
	gratuito: "Gratuito Patrocinio",
	udienze:"Udienza",
	status:"Status",
	dataRicorsi:"Data di inoltro del ricorso",
	appuntamenti:"Appuntamenti",
	richiestaUONI:"Richiesta UONI",
	aggiungiProvvedimentoGiudiziario:"aggiungi un Provvedimento giudiziario",
	aggiungiUdienzaRicorso:"Aggiungi udienza ricorso",
	eliminaProceduraLegale:"Elimina procedura legale",
	dataAttribuzione:"Data attribuzione",
	dataFinale:"Data finale",
	eliminaAllegatoAppuntamento:"Elimina allegato appuntamento",
	eliminaUdienza:"Elimina udienza",
	eliminaAllegatoRicorso:"Elimina allegato ricorso",
	eliminaAllegatoDelega:"Elimina allegato delega",
	affidoAmministrativo: "Affidamento",
	dataVerbaleAffidamento: "Data verbale affidamento",
	enteVerbaleAffidamento: "Autorità che dispone l'affido",
	verbaleAffidamento: "Verbale affidamento provvisorio ex art. 403 CC",
	dataProvvedimentoAffidamento: "Data provvedimento affidamento",
	enteProvvedimentoAffidamento: "Autorità che dispone l'affido",
	provvedimentoAffidamento: "Provvedimento di affidamento definitivo emesso dal Tribunale per i Minorenni",
	pattoAccoglienza: "Patto di accoglienza",
	Stp:"STP",
	codiceFiscale: "Codice fiscale",
	tesseraSanitaria: "Tessera sanitaria",

	// PENALE
	statoProcedimento :"Stato Procedimento",
	Attivita:"Attività",
	nomeAssistenteSociale:"Nome Assistente Sociale",
	cognomeAssistenteSociale:"Cognome Assistente Sociale",
	emailAssistenteSociale:"Email Assistente Sociale",
	telefonoAssistenteSociale:"Telefono Assistente Sociale",

	// ISTRUZIONE E FORMAZIONE

	istruzioneOrigine:"Percorsi d'istruzione e formazione nel paese d'origine",
	istruzioneItaliaConculsi:"Percorsi d'istruzione e formazione in Italia conclusi",
	istruzioneItaliainCorso:"Percorsi d'istruzione e formazione in corso in Italia",
	tipologiaIstruzione: "Tipologia istruzione",
	Istruzione: "Istruzione",
	indirizzoStudi:"Indirizzo di studi",
	classeGruppo:"Classe/Gruppo",
	istituto:"Istituto",
	orariGiorni:"Orari e Giorni",
	saLeggere:"Sa leggere",
	saScrivere:"Sa scrivere",
	anni:"Anni",
	riconosciuto:"Riconosciuto",
	pattoCPIA:"Patto formativo",
	pianoCPIA:"Progetto formativo",
	bilancioCompetenze:"Bilancio competenze",
	aggiungiUnaValutazione:"Aggiungi una valutazione",


	// STORIA
	etnia:"Etnia",
	relazione:"Relazione di parentela",
	inVita:"In vita",
	inUE:"In Unione Europea",
	statusGiuridico:"Status giuridico",
	hotSpot:"Hotspot",
	hotSpotDataIngresso:"Data di ingresso",
	hotSpotDataUscita:"Data di uscita",
	cpa:"CPA",
	cpaNome: "Nome CPA",
	cpaDataIngresso:"Data ingresso",
	cpaDataUscita:"Data uscita",
	secondaAccoglienza:"Seconda accoglienza",
	secondaAccoglienzaTipo:"Tipologia",
	secondaAccoglienzaDataIngresso:"Data ingresso seconda accoglienza",
	secondaAccoglienzaDataUscita:"Data uscita seconda accoglienza",
	dataAdozione:"Data adozione",
	enteAdozione:"Ente adozione",
	allegatoAdozione:"Allegato adozione",
	allontanamentiRitrovamenti:"Allontanamenti/Ritrovamenti",
	paeseOrigine:"Paese d'origine",
	affido:"Affido",
	dataAffido:"Data affido",
	enteAffido:"Ente affido",
	adozione:"Adozione",
	motiviEspatrio: "Ragioni e motivi dell'espatrio",
	timoriManifestati: "Eventuali timori manifestati in caso di rientro nel paese di origine",
	informazioniViaggio: "Informazioni sul viaggio dal paese di origine sino all'arrivo in Italia",
	valutazioneMultidisciplinare:"Valutazione Multidisciplinare",
	valutazione: "Valutazione",

	storiaPersonale: "Storia personale",
	vitaMinore: "Informazioni sulla vita del minore",
	scuola: "Scuola",
	hobbies: "Hobbies",
	ragioniEspatrio: "Ragioni e motivi dell'espatrio",
	eventualiTimori: "Eventuali timori in caso di rientro nel paese di origine",

	osservazioni:"Osservazioni degli educatori",
	pianificazioneInterventi: "Area multidisciplinare per la pianificazione interventi dall'equipe alla luce di una valutazione del superiore interesse del minore",
	diario: "Diario degli interventi degli operatori",

	percorsoMigratorio:"Percorso migratorio",
	annoPartenza:"Anno di partenza",
	luogoPartenza:"Luogo di partenza",
	paesiAttraversati:"Paesi attraversati",
	accoglienza:"Accoglienza in Italia",
	traumatico:"Transito traumatico",
	visto:"Visto",
	nomePaese:"Paese",
	pei:"Progetto educativo Individuale",

	// SANITARIA
	specificheDisabilita: "Specifiche disabilità",
	nomeDisabilita:"Disabilità",
	patologieAllergiche:"Patologie allergiche",
	nomePatologia:"Patologia",
	medicoCurante:"Medico curante assegnato",
	presoInCarico:"Preso in carico da altri servizi",
	elencoVaccini: "Elenco Vaccini",
	vaccini:"Vaccino",
	eventiMedici:"Visite/Incontri/Interventi/Esami",
	presidioOspedaliero:"Presidio Ospedaliero",
	unitaOperativa:"Unità operativa",
	medicoRiferimento:"Medico di riferimento",


	// LAVORO
	ambito: "Ambiti lavorativi",
	inquadramento: "Inquadramento lavorativo",
	nomeAzienda: "Nome azienda",
	luogoAzienda:"Luogo azienda",
	emailAzienda:"Email azienda",
	telefonoAzienda:"Telefono azienda",
	competenzeAcquisite: "Competenze acquisite",
	elencoCertificazioni:"Elenco certificazioni",
	aggiungiCertificazione:"Aggiungi certificazione",
	eliminaEsperienzaLavorativa: "Elimina esperienza lavorativa",
	aggiungiEsperienzaLavorativa: "Aggiungi esperienza lavorativa",
	EsperienzeLavorative:"Esperienze Lavorative",
	nomeResponsabileRespSecAcc:"Nome Responsabile",
	cognomeResponsabileRespSecAcc:"Cognome Responsabile",
	mailResponsabileRespSecAcc:"Mail Responsabile",
	telefonoResponsabileRespSecAcc:"Telefono Responsabile",
	relazioneAssistenteSociale:"Relazione Assistente Sociale",
	relazioneAssistente:"Relazione Assistente",

	// COMPETENZE
	lingueParlate:"Lingue parlate",
	lingueDichiarate:"Lingue dichiarate",
	lingueAttestate:"Lingue attestate",
	lingueCertificate:"Lingue certificate",
	livelloScritto:"Livello scritto",
	livelloOrale:"Livello orale",
	nomeLingua:"Nome lingua",
	CompetenzeDigitali:"Competenze digitali",
	AltreCompetenze:"Altre competenze",
	descrizione:"Descrizione",
	allegatoLingua:"Certificazione",
	competenzeDigitali:"Competenze digitali",
	altreCompetenze:"Altre competenze",

	// DESIDERI
	aspirazioniProfessionali:"Aspirazioni professionali",
	altreAspettative:"Ascolto del minore/progetto individuale",

	// SOCIALE
	EsperienzeVolontariato:"Esperienze di volontariato",
	EsperienzeLaboratori:"Laboratori",
	EsperienzeSport:"Attività sportiva",
	Gruppi:"Appartenenza a gruppi/associazioni",
	giorniOrari:"Giorni e orari",
	pregressa:"Pregressa",
	tipoEsperienza:"Tipo di esperienza",
	referente: "Referente",
	certificazione:"Certificazione",

	//USER
	IN_ATTESA:'In attesa',
	RIFIUTATI:'Rifiutati',
	ABILITATI:'Abiitati',
	gestione_utenti:'Gestione Utenti',
	profilo_utente:'Profilo Utente',
	postiDisponibili:'Posti disponibili'
  });
  $translateProvider.preferredLanguage('it');



}).constant('SERVER_INFO', {

	base_url: 'http://localhost:8080/harraga/',


//	SOCIAL CARD
	get_social_card_list_service_url : 'getSocialCardList',

	//JACOPO : aggiunta URL per chiamata REST italiani e maggiorenni (STRUTS attenzione)
	get_italiani_list_service_url : 'getMigrantiList',
	get_maggiorenni_list_service_url : 'getMaggiorenniList',

	delete_social_Card_service_url : 'deleteSocialCard',
	download_service_url: "download",
	download_folder_service_url: "downloadFolder",


	get_anagrafica_service_url : 'getAnagrafica',
	get_amministrativa_service_url: 'getAmministrativa',
	get_sanitaria_service_url: 'getSanitaria',
	get_storia_service_url: 'getStoria',
	get_istruzione_service_url : 'getIstruzione',
	get_lavoro_service_url: 'getLavoro',
	get_competenze_service_url: 'getCompetenze',
	get_sociale_service_url : 'getSociale',
	get_penale_service_url : 'getPenale',
	get_desideri_service_url: 'getDesideri',

	create_social_card_service_url : 'createSocialCard',
	import_social_card_list_service_url : 'importSocialCardList',


	update_anagrafica_service_url: 'updateAnagrafica',
	update_storia_service_url: 'updateStoria',
	update_sanitaria_service_url: 'updateSanitaria',
	update_lavoro_service_url: 'updateLavoro',
	update_desideri_service_url: 'updateDesideri',
	update_penale_service_url: 'updatePenale',


	update_amministrativa_service_url: 'updateAmministrativa',
	update_istruzione_service_url: 'updateIstruzione',
	update_sociale_service_url: 'updateSociale',
	update_competenze_service_url: 'updateCompetenze',


	get_anagrafica_history_service_url: 'getAnagraficaHist',
	get_amministrativa_history_service_url: 'getAmministrativaHist',

	get_sanitaria_history_service_url: 'getSanitariaHist',
	get_storia_history_service_url: 'getStoriaHist',
	get_istruzione_history_service_url : 'getIstruzioneHist',
	get_lavoro_history_service_url: 'getLavoroHist',
	get_competenze_history_service_url: 'getCompetenzeHist',
	get_sociale_history_service_url : 'getSocialeHist',
	get_penale_history_service_url : 'getPenaleHist',

	get_desideri_history_service_url: 'getDesideriHist',

//	USER
	login_service_url: 'login',
	register_service_url: 'register',

	update_user_service_url: 'updateUser',
	associa_user_service_url: 'associaUser',
	deassocia_user_service_url: 'deassociaUser',

	get_user_list_service_url:'getUserList',
	get_user_service_url:'getUser',


});

