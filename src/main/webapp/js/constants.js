//Opzioni precompilate
var sessoOpzioni = [ {
	name : 'M',
	code : 'M'
}, {
	name : 'F',
	code : 'F'
}, {
	name : 'Altro',
	code : 'Altro'
} ];

// JACOPO : aggiunte opzioni per italiano
var italOpzioni = [ {
	name : 'Sì',
	code : 'S'
}, {
	name : 'No',
	code : 'N'
} ];

var tipoDomicOp = ['Famiglia affidataria','CPA' , 'Comunità','Sprar'];

var livelloLinguaOp = [ 'Avanzato C1-C2','Intermedio B1-B2', 'Base A1-A2' ];
var livelloOp = ['Base','Intermedio','Avanzato'];
var formInformOp = ['Formale', 'Informale'];
var formNoFormOp = ['Formale', 'Non formale'];
var formTriFormOp = ['Formale','Informale' ,'Non formale'];

var permSoggStatoOp = [ 'In attesa','Scaduto','Altro'];
var perSoggTipoOp = ['Minore età','Richiesta asilo','Umanitario','Sussidiaria','Status rifugiato','Lavoro','Studio', 'Altro'];
var rifLegOp = ['Avvocato','Operatore Legale','Ente'];

var familiareOp = ['Padre', 'Madre', 'Fratello', 'Sorella', 'Altro'];

var tipologiaValutazioneOp = ["Tratta", "Protezione Internazionale"];
var allRitOp = ['Allontanamento','Ritrovamento'];

var readWriteOp = ['si','no','parzialmente'];
var istrOrgAnniOp =['0-3','3-6','6+'];
var corsoRiconosciutoOp = ['si','no','in corso di riconoscimento'];

var statoLavoroOp = ['In Corso','Pregressa'];

// JACOPO : Inserimento var DocumentoOp
var documentoOp = ['Estratto di nascita','Carta di identità', 'Codice Fiscale', 'Altro'];
var luogoOp =["Questura","Commissione","Anagrafe","Tribunale","Comune di Palermo", "Altro"];
var secAccOp = ["Sprar", "Comunità alloggio", "Altro"];
var booleanOp = ["Sì", "No"];
var motivazioneOp = ["Comune","Maggiore età", "Calcolo maggiore età", "altro"];
var penaleOp = ["In corso", "Concluso"];
var istruzioneOp = ["Licenza Elementare", "Licenza Media","Diploma","Qualifica Professionale", "Altro"];
var linguaOp =["Bene", "Parzialmente", "Male"];
var tipologiaAdozioneOp = ["Affido","Adozione"];



var ruoli = [ 'CPA', 'RESP_SEC_ACC', 'TUTORE', 'COMUNE_MARSALA', 'REF_LEGALE',
		'ASP', 'CPIA', 'AGENZIA_LAVORO', 'ASSOCIAZIONI', 'USSM', 'ITASTRA', 'GARANTE', 'MINORE', 'TRIBUNALE'];

// JACOPO : ELIMINAZIONE DEI RUOLI CPA E RESP_SEC_ACC DA CREATOR_ROLE
var CREATOR_ROLE = ['COMUNE_MARSALA' ];
// JACOPO : ELIMINAZIONE DEI RUOLI CPA E RESP_SEC_ACC DA INSTITUTIONAL_ROLE
var INSTITUTIONAL_ROLE = ['COMUNE_MARSALA', 'ASP',  'ITASTRA', 'CPIA', 'USSM', 'GARANTE' ];

var PERMISSION_F = [

		// CPA - 
		{
			"userType" : "CPA",
			"read" : [ "ANAGRAFICA", "AMMINISTRATIVA","PERMESSO_SOGGIORNO", "STORIA", "ISTRUZIONE",
				"SANITARIA","DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI","SOCIALE" ],
			"write" : [ "ANAGRAFICA", "AMMINISTRATIVA", "STORIA", "ISTRUZIONE",
				"SANITARIA","DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI","SOCIALE" ]
		},

		// RESP_SEC_ACC
		{
			"userType" : "RESP_SEC_ACC",
			"read" : [ "ANAGRAFICA", "AMMINISTRATIVA","PERMESSO_SOGGIORNO", "STORIA", "ISTRUZIONE",
				"SANITARIA","DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI", "SOCIALE" ],
			"write" : [ "ANAGRAFICA", "AMMINISTRATIVA", "STORIA", "ISTRUZIONE",
				"SANITARIA","DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI","SOCIALE" ]
		},
		// TUTORE
		{
			"userType" : "TUTORE",
			"read" : [ "ANAGRAFICA", "AMMINISTRATIVA","PERMESSO_SOGGIORNO", "STORIA", "ISTRUZIONE",
				"SANITARIA","DISABILITA", "LAVORO", "SOCIALE", "COMPETENZE", "PENALE", "DESIDERI" ],
			"write" : [ "SOCIALE","COMPETENZE", "DESIDERI" ]
		},
	
		// COMUNE_MARSALA
		,
		{
			"userType" : "COMUNE_MARSALA",
			"read" : [ "ANAGRAFICA", "AMMINISTRATIVA","PERMESSO_SOGGIORNO", "STORIA", "ISTRUZIONE",
					"SANITARIA", "DISABILITA", "LAVORO", "SOCIALE", "COMPETENZE", "PENALE", "DESIDERI" ],
			"write" : [ "ANAGRAFICA", "AMMINISTRATIVA", "STORIA", "SANITARIA", "DISABILITA" ]
		},
		
		// REF_LEGALE
		{
			"userType" : "REF_LEGALE",
			"read" : [  "ANAGRAFICA", "AMMINISTRATIVA","PERMESSO_SOGGIORNO","DISABILITA", "STORIA","PERMESSO_SOGGIORNO"],
			"write" : [ "AMMINISTRATIVA" ]
		},
		
		// TODO
		
		// ASP
		{
			"userType" : "ASP",
			"read" : ["ANAGRAFICA","PERMESSO_SOGGIORNO", "STORIA", "SANITARIA", "DISABILITA","PERMESSO_SOGGIORNO" ],
			"write" : [ "SANITARIA", "DISABILITA" ]
		},
		// CPIA/Scuola
		{
			"userType" : "CPIA",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "AMMINISTRATIVA", "ISTRUZIONE",
				"DISABILITA", "SOCIALE", "COMPETENZE", "DESIDERI","PERMESSO_SOGGIORNO" ],
			"write" : [ "ISTRUZIONE", "LAVORO", "SOCIALE", "COMPETENZE", "DESIDERI" ]

		},
		// AGENZIA_LAVORO
		{
			"userType" : "AGENZIA_LAVORO",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "ISTRUZIONE",
				"DISABILITA", "LAVORO", "SOCIALE", "COMPETENZE", "DESIDERI" ],
			"write" : [ "ISTRUZIONE", "LAVORO", "COMPETENZE", "DESIDERI" ]
		},
		
		// ASSOCIAZIONI
		{
			"userType" : "ASSOCIAZIONI",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "ISTRUZIONE",
				"DISABILITA", "SOCIALE", "COMPETENZE", "DESIDERI" ],
			"write" : [ "ISTRUZIONE", "SOCIALE","COMPETENZE" ]
		},
		// USSM
		{
			"userType" : "USSM",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "AMMINISTRATIVA", "STORIA", "ISTRUZIONE",
				"DISABILITA", "SOCIALE", "COMPETENZE", "PENALE", "DESIDERI" ],
			"write" : [ "PENALE" ]
		},


		
		// ITASTRA
		{
			"userType" : "ITASTRA",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "ISTRUZIONE", "COMPETENZE","DISABILITA" ],
			"write" : [ "ISTRUZIONE", "COMPETENZE"]
		},
		
		// GARANTE
		{
			"userType" : "GARANTE",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "AMMINISTRATIVA", "STORIA", "ISTRUZIONE",
				"SANITARIA", "DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI" ],
			"write" : [ ]
		},
		
		
		//TRIBUNALE
		{
			"userType" : "TRIBUNALE",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "AMMINISTRATIVA", "STORIA", "ISTRUZIONE",
				"SANITARIA", "DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI" ],
			"write" : [ ]
		},
		// MINORE
		{
			"userType" : "MINORE",
			"read" : [ "ANAGRAFICA","PERMESSO_SOGGIORNO", "AMMINISTRATIVA", "ISTRUZIONE",
				"DISABILITA", "LAVORO", "COMPETENZE", "DESIDERI" ],
			"write" : [ ]
		},
		

];
/*
var PERMISSION = [ {
	"userType" : "AGENZIA_LAVORO",
	"section" : "ANAGRAFICA",
	"read" : [ "ANAGRAFICA" ],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "ADMINISTRATIVE",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "HISTORY",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "HEALT",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "EDUCATION",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "WORK",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "SOCIALITY",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "TRANSVERSAL_SKILLS",
	"read" : [],
	"write" : []
}, {
	"userType" : "AGENZIA_LAVORO",
	"section" : "WISHES",
	"read" : [],
	"write" : []
},
// ASP
{
	"userType" : "ASP",
	"section" : "ANAGRAFICA",
	"read" : [ "generici", "tutore" ],
	"write" : [ "" ]
},
// CPIA
{
	"userType" : "CPIA",
	"section" : "ANAGRAFICA",
	"read" : [ "generici" ],
	"write" : []
},
// GaranteInfanzia
{
	"userType" : "GaranteInfanzia",
	"section" : "ANAGRAFICA",
	"read" : [ "generici", "tutore" ],
	"write" : []
},
// CPA
{
	"userType" : "CPA",
	"section" : "ANAGRAFICA",
	"read" : [ "generici", "tutore" ],
	"write" : [ "" ]
},
// ResponsabileComunita
{
	"userType" : "ResponsabileComunita",
	"section" : "ANAGRAFICA",
	"read" : [ "generici", "tutore" ],
	"write" : [ "" ]
},
// ServizioSanitario
{
	"userType" : "ServizioSanitario",
	"section" : "ANAGRAFICA",
	"read" : [ "generici" ],
	"write" : []
},
// Tutore
{
	"userType" : "Tutore",
	"section" : "ANAGRAFICA",
	"read" : [ "generici", "tutore" ],
	"write" : [ "" ]
},
// ComunePalermo
{
	"userType" : "COMUNE_MARSALA",
	"section" : "ANAGRAFICA",
	"read" : [ "ANAGRAFICA" ],
	"write" : [ "" ]
}, {
	"userType" : "COMUNE_MARSALA",
	"section" : "AMMINISTRATIVA",
	"read" : [ "AMMINISTRATIVA" ],
	"write" : [ "" ]
},

];*/