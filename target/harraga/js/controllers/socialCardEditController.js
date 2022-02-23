'use strict';

/* Controllers */

var app = angular.module('harragapp.socialCardEditController', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function($rootScope, $templateCache) {
//	$rootScope.$on('$viewContentLoaded', function() {
//		$templateCache.removeAll();
//	});
//});
app.config(function($mdDateLocaleProvider) {
    $mdDateLocaleProvider.formatDate = function(date) {
        return moment(date).format('DD-MM-YYYY');
     };
 });


app.controller('SocialCardEditCtrl', [ '$scope', '$routeParams', '$location', 'socialCardService', 'commonService', '$localStorage','$mdDialog', '$filter',
	function($scope, $routeParams, $location, socialCardService, commonService, $localStorage, $mdDialog, $filter) {
			
			$scope.userSession = $localStorage.userSession;
			
			if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
				$location.path('/login');
			}
			
			$scope.socialCard = {};
			$scope.socialCard.id = $routeParams.id;
			
		
			
			
			//inizializzaizone strutture dati vuote
			
			 var templateAllegato = {
		    	nome: '',
		    	link: '',
//			    data: ''
		    	isNew: true,
			 }
			 var templateContatto = {
		    	nome: '',
		    	cognome: '',
		    	email: '',
		    	telefono: ''
			 }
			 
//			 INIZIO ANAGRAFICA
			
			var documentoVuoto = {
				    tipologia: '',
				    descrizione: '',
				    note:'',
				    allegato: templateAllegato
				  };
			
			var domicilioVuoto = {
				    nome: '',
				    tipoInserimento: '',
				    responsabile: '',
				    email: '',
				    telefono: '',
				    tempoPermanenza: new Date()
				  };
			$scope.addDomicilio = function() {
				$scope.socialCard.anagrafica.domicilio.push(angular.copy(domicilioVuoto));
			}
			$scope.addDocumento = function() {
				$scope.socialCard.anagrafica.documenti.push(angular.copy(documentoVuoto));
			}
			
			$scope.validateNumeroTutela = function(){
				var testo = $scope.socialCard.anagrafica.numeroTutela;
				if(testo.match(/^[0-9]+$/) != null || testo.includes("/")){
				console.log("ottimo");
				}
				
			}
			
			
			$scope.deleteDomicilio = function(_domicilio) {
				var deleteIndex = $scope.socialCard.anagrafica.domicilio.indexOf(_domicilio);
				if(deleteIndex>-1){
					$scope.socialCard.anagrafica.domicilio.splice(deleteIndex,1);
				}
			}
			$scope.deleteDocumento = function(_documento) {				
				var deleteIndex = $scope.socialCard.anagrafica.documenti.indexOf(_documento);
				if(deleteIndex>-1){
					$scope.socialCard.anagrafica.documenti.splice(deleteIndex,1);
				}
			}
//			$scope.socialCard = new Object();
//			$scope.socialCard.anagrafica = new Object();
//			$scope.socialCard.anagrafica.domicilio = [domicilioVuoto];
//			$scope.socialCard.anagrafica.documenti = [documentoVuoto];
			
//			FINE ANAGRAFICA
			
			
			
			
//			INIZIO AMMINISTRATIVA
			var permessoSoggTemplate = {
					stato:'',
					dataScadenza: new Date(),
					dataRilascio: new Date(),
					dataRichiesta: new Date(),
					rilasciatoDa: "",
					tipologia:'',
					allegato: templateAllegato,
				};
			
			$scope.addPermSogg = function() {
				if( !$scope.socialCard.amministrativa.permessoSoggiorno )
					$scope.socialCard.amministrativa.permessoSoggiorno = [];
				$scope.socialCard.amministrativa.permessoSoggiorno.push(angular.copy(permessoSoggTemplate));
			}
			$scope.deletePermSogg = function(_item) {
				var deleteIndex = $scope.socialCard.amministrativa.permessoSoggiorno.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.amministrativa.permessoSoggiorno.splice(deleteIndex,1);
				}
			}

			var docIdentitaTemplate = {
            					allegato: templateAllegato,
            				};

            			$scope.addDocumIdent = function() {
            				if( !$scope.socialCard.amministrativa.documentiIdentita )
            					$scope.socialCard.amministrativa.documentiIdentita = [];
            				$scope.socialCard.amministrativa.documentiIdentita.push(angular.copy(docIdentitaTemplate));
            			}
            			$scope.deleteDocIdent = function(_item) {
            				var deleteIndex = $scope.socialCard.amministrativa.documentiIdentita.indexOf(_item);
            				if(deleteIndex>-1){
            					$scope.socialCard.amministrativa.documentiIdentita.splice(deleteIndex,1);
            				}
            			}
			
			var allegatoAppuntamentoTemplate = {
					descrizione: '',
					allegato: templateAllegato
				}
				
				$scope.addAllegatoAppunt = function(appuntamento) {
					if( appuntamento.allegati == undefined )
						appuntamento.allegati = [];
					appuntamento.allegati.push(angular.copy(allegatoAppuntamentoTemplate));
				}
				$scope.deleteAllegatoAppunt = function(appuntamento, _item) {
					var deleteIndex = appuntamento.allegati.indexOf(_item);
					if(deleteIndex>-1){
						appuntamento.allegati.splice(deleteIndex,1);
					}
				}
				
				var	appuntamentoTemplate = {
					luogo: '',
					data:  new Date(),
					motivo: '',
					esito: '',
					allegati: []
				}
				
				$scope.addAppuntamento = function(procedura) {
					if( procedura.appuntamenti == undefined )
						procedura.appuntamenti = [];
					procedura.appuntamenti.push(angular.copy(appuntamentoTemplate));
				}
				$scope.deleteAppuntamento = function(procedura, _item) {
					var deleteIndex = procedura.appuntamenti.indexOf(_item);
					if(deleteIndex>-1){
						procedura.appuntamenti.splice(deleteIndex,1);
					}
				}			
				
				
				var allegatoRicorsoTemplate = {
					descrizione: '',
					allegato: templateAllegato
				}
				
				var udienzaTemplate = {
						dataUdienza: new Date()
				};
				
				$scope.addAllegatoRicorso = function(ricorso) {
					if( ricorso.allegati == undefined )
						ricorso.allegati = [];
					ricorso.allegati.push(angular.copy(ricorsoTemplate));
				}
				$scope.deleteAllegatoRicorso = function(ricorso, _item) {
					var deleteIndex = ricorso.allegati.indexOf(_item);
					if(deleteIndex>-1){
						ricorso.allegati.splice(deleteIndex,1);
					}
				}
				$scope.addUdienzaRicorso = function(ricorso) {
					if( ricorso.udienze == undefined )
						ricorso.udienze = [];
					ricorso.udienze.push(angular.copy(udienzaTemplate));
				}
				$scope.deleteUdienzaRicorso = function(ricorso, _item) {
					var deleteIndex = ricorso.udienze.indexOf(_item);
					if(deleteIndex>-1){
						ricorso.udienze.splice(deleteIndex,1);
					}
				}
				
				
				var ricorsoTemplate = {	
					data:  new Date(),
					avvocato: '',
					gratuito: '',
					patrocinio: '',
					udienze: [], 
					allegati: []
				}
				$scope.addRicorso = function(procedura) {
					if( procedura.ricorsiAmministrativi == undefined )
						procedura.ricorsiAmministrativi = [];
					procedura.ricorsiAmministrativi.push(angular.copy(ricorsoTemplate));
				}
				$scope.deleteRicorso = function(procedura, _item) {
					var deleteIndex = procedura.ricorsiAmministrativi.indexOf(_item);
					if(deleteIndex>-1){
						procedura.ricorsiAmministrativi.splice(deleteIndex,1);
					}
				}
				
				var dublinoTemplate ={
					paese: '',
					data: new Date(),
					statoProcedura: "",
					allegato: templateAllegato
				}
				var procLegTemplate = {
					dublino: dublinoTemplate,
					appuntamenti: [],
					ricorsiAmministrativi: []
				}
				
				$scope.addProcLeg = function() {
					if( !$scope.socialCard.amministrativa.procedureLegali )
						$scope.socialCard.amministrativa.procedureLegali = [];
					$scope.socialCard.amministrativa.procedureLegali.push(angular.copy(procLegTemplate));
				}
				$scope.deleteProcLeg = function(_item) {
					var deleteIndex = $scope.socialCard.amministrativa.procedureLegali.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.amministrativa.procedureLegali.splice(deleteIndex,1);
					}
				}
				
				var provvedimentoTemplate = {
						data: new Date(),
						tipo:"",
						istituzioneEmittente:"",
						avvocato:""
						
					}
				$scope.addProvvedimento = function() {
					if( !$scope.socialCard.amministrativa.provvedimentiGiudiziari )
						$scope.socialCard.amministrativa.provvedimentiGiudiziari = [];
					$scope.socialCard.amministrativa.provvedimentiGiudiziari.push(angular.copy(provvedimentoTemplate));
				}
				$scope.deleteProvvedimento = function(_item) {
					var deleteIndex = $scope.socialCard.amministrativa.provvedimentiGiudiziari.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.amministrativa.provvedimentiGiudiziari.splice(deleteIndex,1);
					}
				}
				
				
				var allegatoDelegaTemplate ={
					descrizione: '',
					allegato: templateAllegato
				}
				
				$scope.addAllegatoDelega = function() {
					if( !$scope.socialCard.amministrativa.delegaAmministrativa.allegati )
						$scope.socialCard.amministrativa.delegaAmministrativa.allegati = [];
					$scope.socialCard.amministrativa.delegaAmministrativa.allegati.push(angular.copy(allegatoDelegaTemplate));
				}
				$scope.deleteAllegatoDelega = function(_item) {
					var deleteIndex = $scope.socialCard.amministrativa.delegaAmministrativa.allegati.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.amministrativa.delegaAmministrativa.allegati.splice(deleteIndex,1);
					}
				}
			
//			FINE AMMINISTRATIVA

			// INIZIO PENALE
				
				var assistenteSocialeTemplate ={
						nome:'',
						cognome:'',
						mail:'',
						telefono:''
				}
				var peiTemplate2 ={
						descrizione: '',
						allegato: templateAllegato
				}
				
				var procPenaleTemplate = {
						statoProcedimento:'',
						assistenteSociale: assistenteSocialeTemplate,
						pei:peiTemplate2,
						
					}
					
					$scope.addProcPenale = function() {
						if( !$scope.socialCard.penale.procedimentiPenali)
							$scope.socialCard.penale.procedimentiPenali = [];
						$scope.socialCard.penale.procedimentiPenali.push(angular.copy(procPenaleTemplate));
					}
					$scope.deleteProcPenale = function(_item) {
						var deleteIndex = $scope.socialCard.penale.procedimentiPenali.indexOf(_item);
						if(deleteIndex>-1){
							$scope.socialCard.penale.procedimentiPenali.splice(deleteIndex,1);
						}
					}
				
			// FINE PENALE
				
				
			//INIZIO STORIA
			var familiareTemplate ={
				 relazione: '',
				 contatto:  templateContatto,
				 inVita: false,
				 paese: '',
				 boolean: false,
				 statusGiuridico: '',
				 paeseOrigine:'',
				 note: '',
				 allegato: templateAllegato
			}
			

			$scope.addFamiliare = function() {
				if( !$scope.socialCard.storia.familiari )
					$scope.socialCard.storia.familiari = [];
				$scope.socialCard.storia.familiari.push(angular.copy(familiareTemplate));
			}
			$scope.deleteFamiliare = function(_familiare) {
				var deleteIndex = $scope.socialCard.storia.familiari.indexOf(_familiare);
				if(deleteIndex>-1){
					$scope.socialCard.storia.familiari.splice(deleteIndex,1);
				}
			}
			
			var paeseAttraversatoTemplate ={
					nome: '',
					traumatico: false,
					dataPartenza: new Date(),
					dataArrivo: new Date(),
					visto: templateAllegato
			}
			
			$scope.addPaeseAttraversato = function() {
				if( !$scope.socialCard.storia.percorsoMigratorio)
					$scope.socialCard.storia.percorsoMigratorio =  new Object();
				if( !$scope.socialCard.storia.percorsoMigratorio.paesiAttraversati )
					$scope.socialCard.storia.percorsoMigratorio.paesiAttraversati = [];
				$scope.socialCard.storia.percorsoMigratorio.paesiAttraversati.push(angular.copy(paeseAttraversatoTemplate));
			}
			$scope.deletePaeseAttraversato = function(_paese) {
				var deleteIndex = $scope.socialCard.storia.percorsoMigratorio.paesiAttraversati.indexOf(_paese);
				if(deleteIndex>-1){
					$scope.socialCard.storia.percorsoMigratorio.paesiAttraversati.splice(deleteIndex,1);
				}
			}
			
			var allRitTemplate ={
					 tipo: '',
					 data: new Date(),
					 luogo: '',
					 comunicazione: templateAllegato
				}
			
			$scope.addAllRit = function() {			
				if( !$scope.socialCard.storia.accoglienza)
					$scope.socialCard.storia.accoglienza =  new Object();
				if( !$scope.socialCard.storia.accoglienza.allontanamentiRitrovamenti )
					$scope.socialCard.storia.accoglienza.allontanamentiRitrovamenti = [];
				$scope.socialCard.storia.accoglienza.allontanamentiRitrovamenti.push(angular.copy(allRitTemplate));
			}
			$scope.deleteAllRit = function(_allRit) {
				var deleteIndex = $scope.socialCard.storia.accoglienza.allontanamentiRitrovamenti.indexOf(_allRit);
				if(deleteIndex>-1){
					$scope.socialCard.storia.accoglienza.allontanamentiRitrovamenti.splice(deleteIndex,1);
				}
			}
			
			var peiTemplate ={
					descrizione: '',
					allegato: templateAllegato
			}
			
			$scope.addAccPei = function() {
				if( !$scope.socialCard.storia.accoglienza)
					$scope.socialCard.storia.accoglienza =  new Object();
				if( !$scope.socialCard.storia.accoglienza.pei )
					$scope.socialCard.storia.accoglienza.pei = [];
				$scope.socialCard.storia.accoglienza.pei.push(angular.copy(peiTemplate));
			}
			$scope.deleteAccPei = function(_item) {
				var deleteIndex = $scope.socialCard.storia.accoglienza.pei.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.storia.accoglienza.pei.splice(deleteIndex,1);
				}
			}
			
			var affidoTemplate ={
					dataAffido: new Date(),
					enteAffido: '',
					allegatoAffido: templateAllegato
			}
			
			$scope.addAffido = function() {
				if( !$scope.socialCard.storia.accoglienza)
					$scope.socialCard.storia.accoglienza =  new Object();
				if( !$scope.socialCard.storia.accoglienza.affidi )
					$scope.socialCard.storia.accoglienza.affidi = [];
				$scope.socialCard.storia.accoglienza.affidi.push(angular.copy(affidoTemplate));
			}
			$scope.deleteAffido = function(_item) {
				var deleteIndex = $scope.socialCard.storia.accoglienza.affidi.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.storia.accoglienza.affidi.splice(deleteIndex,1);
				}
			}
			
			var adozioneTemplate ={
					dataAdozione: new Date(),
					enteAdozione: '',
					allegatoAdozione: templateAllegato
			}
			
			$scope.addAdozione = function() {
				if( !$scope.socialCard.storia.accoglienza)
					$scope.socialCard.storia.accoglienza =  new Object();
				if( !$scope.socialCard.storia.accoglienza.adozioni )
					$scope.socialCard.storia.accoglienza.adozioni = [];
				$scope.socialCard.storia.accoglienza.adozioni.push(angular.copy(adozioneTemplate));
			}
			$scope.deleteAdozione = function(_item) {
				var deleteIndex = $scope.socialCard.storia.accoglienza.adozioni.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.storia.accoglienza.adozioni.splice(deleteIndex,1);
				}
			}
			var relazioneTemplate ={
					descrizione: '',
					allegato: templateAllegato
			}
			
			$scope.addRelazione = function() {
				if( !$scope.socialCard.storia.accoglienza)
					$scope.socialCard.storia.accoglienza =  new Object();
				if( !$scope.socialCard.storia.accoglienza.relazioniAssistenteSociale )
					$scope.socialCard.storia.accoglienza.relazioniAssistenteSociale = [];
				$scope.socialCard.storia.accoglienza.relazioniAssistenteSociale.push(angular.copy(relazioneTemplate));
			}
			$scope.deleteRelazione = function(_item) {
				var deleteIndex = $scope.socialCard.storia.accoglienza.relazioniAssistenteSociale.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.storia.accoglienza.relazioniAssistenteSociale.splice(deleteIndex,1);
				}
			}
			//FINE STORIA
			
			//INIZIO ISTRUZIONE

			var istrOrgTemplate = {
					saLeggere:'',
				    saScrivere: '',
					tipo:'',
					anni:'',
					descrizione:'',
					riconosciuto:'',
				    allegato: templateAllegato
				  };
	
			$scope.addIstrOrg = function() {
				if( !$scope.socialCard.istruzione.istruzioneOrigine )
					$scope.socialCard.istruzione.istruzioneOrigine = [];
				$scope.socialCard.istruzione.istruzioneOrigine.push(angular.copy(istrOrgTemplate));
			}
			$scope.deleteIstrOrg = function(item) {
				var deleteIndex = $scope.socialCard.istruzione.istruzioneOrigine.indexOf(item);
				if(deleteIndex>-1){
					$scope.socialCard.istruzione.istruzioneOrigine.splice(deleteIndex,1);
				}
			}
			
			var istrItaConTemplate = {
				    tipo: '',
					quale:'',
					tipo:'',
					inizio:  new Date(),
					fine:  new Date(),
					istituto:'',
				    allegato: templateAllegato
				  };
				  				  
			$scope.addIstItaCon = function() {
				if( !$scope.socialCard.istruzione.istruzioneItaliaConculsi )
					$scope.socialCard.istruzione.istruzioneItaliaConculsi = [];
				$scope.socialCard.istruzione.istruzioneItaliaConculsi.push(angular.copy(istrItaConTemplate));
			}
			$scope.deleteIstItaCon = function(item) {
				var deleteIndex = $scope.socialCard.istruzione.istruzioneItaliaConculsi.indexOf(item);
				if(deleteIndex>-1){
					$scope.socialCard.istruzione.istruzioneItaliaConculsi.splice(deleteIndex,1);
				}
			}
			
			
			var istrItaInCorsoTemplate = {
				    tipo: '',
					nome:'',
					inizio: new Date(),
					fine: new Date(),
					classeGruppo:'',
					orariGiorni:'',
					istituto:'',
					luogo:'',
				    pianoCPIA: templateAllegato,
					pattoCPIA: templateAllegato,
					progettoFormativo: templateAllegato,
					valutazioni:[]
				  };
				  							
			$scope.addIstItaInCorso = function() {
				if( !$scope.socialCard.istruzione.istruzioneItaliainCorso )
					$scope.socialCard.istruzione.istruzioneItaliainCorso = [];
				$scope.socialCard.istruzione.istruzioneItaliainCorso.push(angular.copy(istrItaInCorsoTemplate));
			}
			$scope.deleteIstItaInCorso = function(item) {
				var deleteIndex = $scope.socialCard.istruzione.istruzioneItaliainCorso.indexOf(item);
				if(deleteIndex>-1){
					$scope.socialCard.istruzione.istruzioneItaliainCorso.splice(deleteIndex,1);
				}
			}
			
			
			var valutazioneTemplate = {
					allegato:''
					};
			
			$scope.addValutazione = function(item) {
				if( item.valutazioni == undefined )
					item.valutazioni = [];
				item.valutazioni.push(angular.copy(valutazioneTemplate));
				
			}
			$scope.deleteValutazione = function(item, _item) {
				var deleteIndex = item.valutazioni.indexOf(_item);
				if(deleteIndex>-1){
					item.valutazioni.splice(deleteIndex,1);
				}
			}
			
			
			
//			FINE ISTRUZIONE
			
			//INIZIO SANITARIA
			var disabilitaVuota = {
				    nome: '',
				    allegato: templateAllegato
				  };
			
			$scope.addDisabilita = function() {
				if( !$scope.socialCard.sanitaria.specificheDisabilita )
					$scope.socialCard.sanitaria.specificheDisabilita = [];
				$scope.socialCard.sanitaria.specificheDisabilita.push(angular.copy(disabilitaVuota));
			}
			$scope.deleteDisabilita = function(_disabilita) {
				var deleteIndex = $scope.socialCard.sanitaria.specificheDisabilita.indexOf(_disabilita);
				if(deleteIndex>-1){
					$scope.socialCard.sanitaria.specificheDisabilita.splice(deleteIndex,1);
				}
			}
			
			var allergiaVuota = {
				    nome: '',
				    allegato: templateAllegato
				  };
			
			
			$scope.addAllergia = function() {
				if( !$scope.socialCard.sanitaria.patologieAllergiche )
					$scope.socialCard.sanitaria.patologieAllergiche = [];
				$scope.socialCard.sanitaria.patologieAllergiche.push(angular.copy(allergiaVuota));
			}
			
			$scope.deleteAllergia = function(_allergia) {				
				var deleteIndex = $scope.socialCard.sanitaria.patologieAllergiche.indexOf(_allergia);
				if(deleteIndex>-1){
					$scope.socialCard.sanitaria.patologieAllergiche.splice(deleteIndex,1);
				}
			}
			
			
			var eventoMedicoTemplate = {
					data: new Date(),
					tipo: '',
					presidioOspedaliero: '',
					unitaOperativa: '',
					medicoRiferimento: templateContatto,
				    allegato: templateAllegato
				  };
			
			
			$scope.addEventoMedico = function() {
				if( !$scope.socialCard.sanitaria.eventiMedici )
					$scope.socialCard.sanitaria.eventiMedici = [];
				$scope.socialCard.sanitaria.eventiMedici.push(angular.copy(eventoMedicoTemplate));
			}
			
			$scope.deleteEventoMedico = function(_eventoMed) {				
				var deleteIndex = $scope.socialCard.sanitaria.eventiMedici.indexOf(_eventoMed);
				if(deleteIndex>-1){
					$scope.socialCard.sanitaria.eventiMedici.splice(deleteIndex,1);
				}
			}
			var templateVaccino = {
				    vaccino: ''
				  };
			
			
			$scope.addVaccino = function() {
				if( !$scope.socialCard.sanitaria.vaccini )
					$scope.socialCard.sanitaria.vaccini = [];
				$scope.socialCard.sanitaria.vaccini.push(angular.copy(templateVaccino));
			}
			
			$scope.deleteVaccino = function(_vaccino) {				
				var deleteIndex = $scope.socialCard.sanitaria.vaccini.indexOf(_vaccino);
				if(deleteIndex>-1){
					$scope.socialCard.sanitaria.vaccini.splice(deleteIndex,1);
				}
			}
			
			
			
			
			
			
			//JACOPO addVaccino e deleteVaccino
			
			// FINE SANITARIA
			
			//INIZIO SOCIALE	
			var esperienzaTemplete = {
					pregressa: '',
					tipo: '',
					dataInizio:  new Date(),
					dataFine:  new Date(),
					giorniOrari: '',
					competenzeAcquisite: '',
				    referente: templateContatto,
				    allegato: templateAllegato
				  };
			
			$scope.addVolont = function() {
				if( !$scope.socialCard.sociale.volontariato )
					$scope.socialCard.sociale.volontariato = [];
				$scope.socialCard.sociale.volontariato.push(angular.copy(esperienzaTemplete));
			}
			$scope.deleteVolont = function(_item) {
				var deleteIndex = $scope.socialCard.sociale.volontariato.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.sociale.volontariato.splice(deleteIndex,1);
				}
			}

			$scope.addLab = function() {
				if( !$scope.socialCard.sociale.laboratori )
					$scope.socialCard.sociale.laboratori = [];
				$scope.socialCard.sociale.laboratori.push(angular.copy(esperienzaTemplete));
			}
			$scope.deleteLab = function(_lab) {
				var deleteIndex = $scope.socialCard.sociale.laboratori.indexOf(_lab);
				if(deleteIndex>-1){
					$scope.socialCard.sociale.laboratori.splice(deleteIndex,1);
				}
			}
			
			$scope.addSport = function() {
				if( !$scope.socialCard.sociale.sport )
					$scope.socialCard.sociale.sport = [];
				$scope.socialCard.sociale.sport.push(angular.copy(esperienzaTemplete));
			}
			$scope.deleteSport = function(_item) {
				var deleteIndex = $scope.socialCard.sociale.sport.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.sociale.sport.splice(deleteIndex,1);
				}
			}
			
			$scope.addGruppiAss = function() {
				if( !$scope.socialCard.sociale.gruppiAss )
					$scope.socialCard.sociale.gruppiAss = [];
				$scope.socialCard.sociale.gruppiAss.push(angular.copy(esperienzaTemplete));
			}
			$scope.deleteGruppiAss = function(_item) {
				var deleteIndex = $scope.socialCard.sociale.gruppiAss.indexOf(_item);
				if(deleteIndex>-1){
					$scope.socialCard.sociale.gruppiAss.splice(deleteIndex,1);
				}
			}
			
			//FINE SOCIALE
			
			
			//INIZIO LAVORO 
						
			
			var certLavTemplate = {
				    descrizione: '',
				    allegato: templateAllegato
				  };
			$scope.addCertificazione = function(_lavoro) {
				 var addIndex = $scope.socialCard.lavoro.indexOf(_lavoro);
					if(addIndex>-1){
						$scope.socialCard.lavoro[addIndex].certificazioni.push(angular.copy(certLavTemplate));
					}
//				if(lavoro.certificazioni == undefined) 
//					lavoro.certificazioni = [];
//				lavoro.certificazioni.push(angular.copy(allegatoVuoto));
			}
			
			$scope.deleteCertificazione = function(lavoro, _certificazione) {
				var deleteIndex = lavoro.certificazioni.indexOf(_certificazione);
				if(deleteIndex>-1){
					lavoro.certificazioni.splice(deleteIndex,1);
				}
			}
			
			 var esperienzaVuota = {

				 tipo:'',
				 stato:'',
				 tipologia:'',
				 ambito:'',
				 inquadramento:'',
				 orariGiorni:'',
				 dataInizio:  new Date(),
				 dataFine:  new Date(),
				 nomeAzienda:'',
				 luogoAzienda:'',
				 emailAzienda:'',
				 telefonoAzienda:'',
				 competenzeAcquisite:'',
				 certificazioni: []
		    }
				
				$scope.addEsperienza = function() {	
					if($scope.socialCard.lavoro == undefined) 
						$scope.socialCard.lavoro = [];
					$scope.socialCard.lavoro.push(angular.copy(esperienzaVuota));
				}
				
				$scope.deleteEsperienza = function(_esperienza) {
					var deleteIndex = $scope.socialCard.lavoro.indexOf(_esperienza);
					if(deleteIndex>-1){
						$scope.socialCard.lavoro.splice(deleteIndex,1);
					}
				}
			
			//FINE LAVORO
				
//				INIZIO COMPETENZE
				
				var linguaTemplate ={
						nome: '',
						livelloScritto: '',
						livelloOrale: ''
				}
				
				$scope.addLinguaDichiarata = function() {
					if( !$scope.socialCard.competenze.lingueDichiarate )
						$scope.socialCard.competenze.lingueDichiarate = [];
					$scope.socialCard.competenze.lingueDichiarate.push(angular.copy(linguaTemplate));
				}
				$scope.deleteLinguaDichiarata = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.lingueDichiarate.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.lingueDichiarate.splice(deleteIndex,1);
					}
				}
				
				$scope.addLinguaAttestata = function() {
					if( !$scope.socialCard.competenze.lingueAttestate )
						$scope.socialCard.competenze.lingueAttestate = [];
					$scope.socialCard.competenze.lingueAttestate.push(angular.copy(linguaTemplate));
				}
				$scope.deleteLinguaAttestata = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.lingueAttestate.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.lingueAttestate.splice(deleteIndex,1);
					}
				}

				
				$scope.addLinguaCertificata = function() {
					if( !$scope.socialCard.competenze.lingueCertificate )
						$scope.socialCard.competenze.lingueCertificate = [];
					$scope.socialCard.competenze.lingueCertificate.push(angular.copy(linguaTemplate));
				}
				$scope.deleteLinguaCertificata = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.lingueCertificate.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.lingueCertificate.splice(deleteIndex,1);
					}
				}
				
				
				
				var compDigTemplate ={
						nome: '',
						descrizione: '',
						tipo: '',
						attivitaSvolta: '',
						livello: '',
						certificazione: templateAllegato
				}
				
				$scope.addCompDig = function() {
					if( !$scope.socialCard.competenze.competenzeDigitali )
						$scope.socialCard.competenze.competenzeDigitali = [];
					$scope.socialCard.competenze.competenzeDigitali.push(angular.copy(compDigTemplate));
				}
				$scope.deleteCompDig = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.competenzeDigitali.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.competenzeDigitali.splice(deleteIndex,1);
					}
				}
				
				var compBaseTemplate ={
						nome: '',
						descrizione: '',
						tipo: '',
						attivitaSvolta: '',
						livello: '',
						certificazione: templateAllegato
				}
				
				$scope.addCompBase = function() {
					if( !$scope.socialCard.competenze.altreCompetenze )
						$scope.socialCard.competenze.altreCompetenze = [];
					$scope.socialCard.competenze.altreCompetenze.push(angular.copy(compBaseTemplate));
				}
				$scope.deleteCompBase = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.altreCompetenze.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.altreCompetenze.splice(deleteIndex,1);
					}
				}
				var patenteTemplate ={
						descrizione: '',
						allegato: templateAllegato
				}
				
				$scope.addPatente = function() {
					if( !$scope.socialCard.competenze.patenti )
						$scope.socialCard.competenze.patenti = [];
					$scope.socialCard.competenze.patenti.push(angular.copy(patenteTemplate));
				}
				$scope.deletePatente = function(_item) {
					var deleteIndex = $scope.socialCard.competenze.patenti.indexOf(_item);
					if(deleteIndex>-1){
						$scope.socialCard.competenze.patenti.splice(deleteIndex,1);
					}
				}
				
		
//				FINE COMPETENZE

// 			INIZIO Opzioni precompilate
			$scope.sessoOpzioni = sessoOpzioni;
			// JACOPO : aggiunta italOpzioni, tipoDomicOp, luogoOp
			$scope.italOpzioni = italOpzioni;
			$scope.tipoDomicOp = tipoDomicOp;
			$scope.tipologiaValutazioneOp = tipologiaValutazioneOp;
			$scope.luogoOp = luogoOp;
			$scope.secAccOp = secAccOp;
			$scope.booleanOp = booleanOp;
			$scope.motivazioneOp = motivazioneOp;
			$scope.penaleOp = penaleOp;
			$scope.istruzioneOp = istruzioneOp;
			$scope.linguaOp = linguaOp;
			$scope.tipologiaAdozioneOp = tipologiaAdozioneOp;
			
			
			// JACOPO : aggiunta documentoOp
			$scope.documentoOp = documentoOp;
			
			$scope.permSoggStatoOp = permSoggStatoOp;
			$scope.perSoggTipoOp = perSoggTipoOp;
			$scope.rifLegOp = rifLegOp;
			
			$scope.familiareOp = familiareOp;
			$scope.allRitOp = allRitOp;
			
			$scope.livelloLinguaOp = livelloLinguaOp;
			$scope.livelloOp = livelloOp;
			$scope.formInformOp = formInformOp;
			$scope.formNoFormOp = formNoFormOp;
			$scope.formTriFormOp = formTriFormOp;
			$scope.readWriteOp = readWriteOp;
			$scope.istrOrgAnniOp = istrOrgAnniOp;
			$scope.corsoRiconosciutoOp = corsoRiconosciutoOp;
			
			$scope.statoLavoroOp = statoLavoroOp;
			
			
			
// 			FINE Opzioni precompilate
			$scope.canWrite = function(section, subSetcion ) {
				return commonService.canWrite(section, subSetcion);
			};
	
			// callback for ng-click 'showSocialCard':
			$scope.showSocialCard = function(socialCardId) {				
				 var confirm = $mdDialog.confirm()
		          .title('Sicuro di voler tornare a Mostra Dettagli?')
		          .textContent('Eventuali modifiche non salvate saranno perse')
		          .ariaLabel('Lucky day')
		          .targetEvent(socialCardId)
		          .ok('Conferma')
		          .cancel('Annulla');

			    $mdDialog.show(confirm).then(function() {
			    	console.log("back to list confirmed");
			    	$location.path('/social-card-detail/' + $scope.socialCard.id);
			    }, function() {
			    	console.log("back to list cancel");
			    });
			};

			// callback for ng-click 'updateAnagrafica':
			$scope.updateAnagrafica  = function(ev) {
				$scope.loading = true;
				//updateUserDummy($scope.user);
				socialCardService.updateAnagrafica($scope.socialCard.id, angular.copy($scope.socialCard.anagrafica)).then(function(temp) {
					console.log("SUCCESS");
					
					 updateSuccess("Anagrafica", ev)
//					$scope.loading = false;
//					 var confirm = $mdDialog.confirm()
//			          .title('Anagrafica Modificata con successo')
//			          .textContent('Anagrafica Modificata con successo!')
//			          .ariaLabel('Lucky day')
//			          .targetEvent(ev)
//			          .ok('Vai in Visualizzazione')
//			          .cancel('Continua in Modifica');
//
//				    $mdDialog.show(confirm).then(function() {
//				    	$location.path('/social-card-detail/' + $scope.socialCard.id);
//				    }, function() {
//				    	$location.path('/social-card-edit/' + $scope.socialCard.id);
//				    });
					
				
					//$scope.user = temp;					
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});				
			};
			
			// callback for ng-click 'updateAmministrativa':
			$scope.updateAmministrativa  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateAmministrativa( $scope.socialCard.id, angular.copy($scope.socialCard.amministrativa)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Amministrativa", ev )
				}, function() {
					console.log("ERROR");
				});				
			};

			// callback for ng-click 'updateStoria':
			$scope.updateStoria  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateStoria( $scope.socialCard.id, angular.copy($scope.socialCard.storia)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Storia", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateSanitaria':
			$scope.updateSanitaria  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateSanitaria( $scope.socialCard.id, angular.copy($scope.socialCard.sanitaria)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Sanitaria", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateIstruzione':
			$scope.updateIstruzione  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateIstruzione( $scope.socialCard.id, angular.copy($scope.socialCard.istruzione)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Istruzione", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateLavoro':
			$scope.updateLavoro  = function(ev) {
				$scope.loading = false;
				
				//$scope.socialCard.lavoro.idAnagrafica = $scope.socialCard.id; 
				socialCardService.updateLavoro( $scope.socialCard.id, angular.copy($scope.socialCard.lavoro)).then(function(temp) {
					
					console.log("SUCCESS");
					updateSuccess("Lavoro", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateSociale':
			$scope.updateSociale  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateSociale( $scope.socialCard.id, angular.copy($scope.socialCard.sociale)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Sociale", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateCompetenze':
			$scope.updateCompetenze  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updateCompetenze( $scope.socialCard.id, angular.copy($scope.socialCard.competenze)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Competenze", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			// callback for ng-click 'updateDesideri':
			$scope.updateDesideri  = function(ev) {
				$scope.loading = false;
				//updateUserDummy($scope.user);
				//$scope.socialCard.desideri.socialCardId = $scope.socialCard.id; 
				socialCardService.updateDesideri($scope.socialCard.id, angular.copy($scope.socialCard.desideri)).then(function(temp) {
					
					console.log("SUCCESS");
					updateSuccess("Desideri", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			
			// callback for ng-click 'updatePenale':
			$scope.updatePenale  = function(ev) {
				$scope.loading = false;
				
				socialCardService.updatePenale( $scope.socialCard.id, angular.copy($scope.socialCard.penale)).then(function(temp) {
					console.log("SUCCESS");
					updateSuccess("Penale", ev )
				}, function() {
					console.log("ERROR");
				});				
			};
			
			
			
			function updateSuccess(sectionName, event){
			
				$scope.loading = false;
				 var confirm = $mdDialog.confirm()
		          .title('Sezione '+sectionName +' Modificata con successo')
		          .textContent('Sezione '+ sectionName +' Modificata con successo!')
		          .ariaLabel('Lucky day')
		          .targetEvent(event)
		          .ok('Vai in Visualizzazione')
		          .cancel('Continua in Modifica');
	
			    $mdDialog.show(confirm).then(function() {
			    	$location.path('/social-card-detail/' + $scope.socialCard.id);
			    }, function() {
			    	$location.path('/social-card-edit/' + $scope.socialCard.id);
			    });
			}

			// callback for ng-click 'back':
			$scope.back = function(ev) {
				 var confirm = $mdDialog.confirm()
		          .title('Sicuro di voler tornare a Elenco Cartelle Sociali?')
		          .textContent('Eventuali modifiche non salvate saranno perse')
		          .ariaLabel('Lucky day')
		          .targetEvent(ev)
		          .ok('Conferma')
		          .cancel('Annulla');

			    $mdDialog.show(confirm).then(function() {
			    	console.log("back to list  confirmed");
					$location.path('/social-card-list');
			    }, function() {
			    	console.log("back to list  cancel");
			    });
			};

			
//			 $scope.onDateChange = function() {
//                 if (this.node.mydate) {
//                   this.node.mydate = this.node.mydate.getTime();
//                 }
//               };
               
			
			//GESTIONE TAB START
			
			
			
			$scope.showTab = function(tabId) {

				switch(tabId) {
				    case "ANAGRAFICA":
				    	getAnagrafica();
				        break;
				    case "AMMINISTRATIVA":
				    	getAmministrativa();				        
				    	break;
				    case "STORIA":
				        getStoria();
				        break;
				    case "SANITARIA":
				        getSanitaria();
				        break;
				    case "ISTRUZIONE":
				        getIstruzione();
				        break;
				    case "LAVORO":
				        getLavoro();
				        break;
				    case "SOCIALE":
				        getSociale();
				        break;
				    case "COMPETENZE":
				        getCompetenze();
				        break;
				    case "DESIDERI":
				        getDesideri();
				        break;
				    case "PENALE":
				        getPenale();
				        break;
				        
				    default:
				        //text = "I have never heard of that fruit...";
				
				}				
				$scope.tab=tabId;
			};
			
			
			function getAnagrafica() {
				if($scope.socialCard.anagrafica==undefined){ 
					$scope.loading = true;
					socialCardService.getAnagrafica($routeParams.id).then(function(temp) {
						if( !temp.domicilio )
							temp.domicilio = [angular.copy(domicilioVuoto)];
						if(!temp.documenti) 
							temp.documenti = [angular.copy(documentoVuoto)];				
						$scope.socialCard.anagrafica = temp;
						turnAnagraficaCheckboxesOn();
						console.log("SUCCESS");
						$scope.loading = false;
					}, function() {
						console.log("ERROR");
						$scope.loading = false;
					});
				}
			}
			function turnAnagraficaCheckboxesOn(){
				
				if($scope.socialCard.anagrafica.mediatore!=undefined){
					$scope.socialCard.anagrafica.medYes = true;
				}
			}
			
			function getAmministrativa() {				
				if($scope.socialCard.amministrativa==undefined){
					$scope.loading = true;
					socialCardService.getAmministrativa($routeParams.id).then(
							function(temp) {
						$scope.socialCard.amministrativa = temp;
						turnAmministrativaCheckboxesOn();
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						$scope.loading=false;
						console.log("ERROR");
					});
				}
			}
			function turnAmministrativaCheckboxesOn(){
				if($scope.socialCard.amministrativa.procedureLegali != undefined ){
					$scope.socialCard.amministrativa.procedureLegali.forEach(function(evento){
						if(evento.dublino != undefined){
							evento.dublinoYes = true;	
							}
						})
				}
				if($scope.socialCard.amministrativa.proseguo21!=undefined){
					$scope.socialCard.amministrativa.proseguoYes = true;
				}
			}
			
			function getStoria() {
				if($scope.socialCard.storia==undefined){ 
					$scope.loading = true;
					socialCardService.getStoria($routeParams.id).then(function(temp) {
						if(temp==null){
							temp = new Object();
						}	
						$scope.socialCard.storia = temp;
						turnStoriaCheckboxesOn();
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			
			function turnStoriaCheckboxesOn(){
				
				if($scope.socialCard.storia.accoglienza!=undefined){
					if($scope.socialCard.storia.accoglienza.hotSpot!=undefined){
						$scope.socialCard.storia.accoglienzaYes = true;
					}
				}
				}
			
			function getSanitaria() {
				if($scope.socialCard.sanitaria==undefined){ 
					$scope.loading = true;
					socialCardService.getSanitaria($routeParams.id).then(function(temp) {
						if(temp==null){
							temp = new Object();
						}						
						$scope.socialCard.sanitaria = temp;
						turnSanitariaheckboxesOn();
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			
			function turnSanitariaheckboxesOn(){
				
				if($scope.socialCard.sanitaria.specificheDisabilita!=undefined){
					$scope.socialCard.sanitaria.disabilitaYes = true;
				}
				if($scope.socialCard.sanitaria.patologieAllergiche!=undefined){
					$scope.socialCard.sanitaria.patologiaYes = true;
				}
				if($scope.socialCard.sanitaria.medicoCurante!=undefined){
					$scope.socialCard.sanitaria.medicoYes = true;
				}
				if($scope.socialCard.sanitaria.presoInCarico!=undefined){
					$scope.socialCard.sanitaria.inCaricoYes = true;
				}
				if($scope.socialCard.sanitaria.vaccini!=undefined){
					$scope.socialCard.sanitaria.vaccinoYes = true;
				}
			}
			
			
			function getIstruzione() {
				if($scope.socialCard.istruzione==undefined){ 
					$scope.loading = true;
					socialCardService.getIstruzione($routeParams.id).then(function(temp) {
						if(temp==null){
							temp = new Object();
						}	
						$scope.socialCard.istruzione = temp;
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			
			function getLavoro() {
				if($scope.socialCard.lavoro==undefined){ 
					$scope.loading = true;
					socialCardService.getLavoro($routeParams.id).then(function(temp) {
						$scope.socialCard.lavoro = temp;
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			
			function getCompetenze() {
				if($scope.socialCard.competenze==undefined){ 
					$scope.loading = true;
					socialCardService.getCompetenze($routeParams.id).then(function(temp) {
						if(temp==null){
							temp = new Object();
						}
						$scope.socialCard.competenze = temp;
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			}
			

			function getSociale() {
				if($scope.socialCard.sociale==undefined){ 
					$scope.loading = true;
					socialCardService.getSociale($routeParams.id).then(function(temp) {
						if(temp==null){
							temp = new Object();
						}
						$scope.socialCard.sociale = temp;
						turnSocialeheckboxesOn();
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			function turnSocialeheckboxesOn(){
				
				if($scope.socialCard.sociale.volontariato!=undefined){
					$scope.socialCard.sociale.volontariatoYes = true;
				}
				if($scope.socialCard.sociale.laboratori!=undefined){
					$scope.socialCard.sociale.laboratoriYes = true;
				}
				if($scope.socialCard.sociale.sport!=undefined){
					$scope.socialCard.sociale.sportYes = true;
				}
				if($scope.socialCard.sociale.gruppiAss!=undefined){
					$scope.socialCard.sociale.gruppiYes = true;
				}

			}
			
			function getPenale() {
				if($scope.socialCard.penale==undefined){ 
					$scope.loading = true;
					socialCardService.getPenale($routeParams.id).then(function(temp) {
						$scope.socialCard.penale = temp;
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			
			
			function getDesideri() {
				if($scope.socialCard.desideri==undefined){ 
					$scope.loading = true;
					socialCardService.getDesideri($routeParams.id).then(function(temp) {
						$scope.socialCard.desideri = temp;
						console.log("SUCCESS");
						$scope.loading=false;
					}, function() {
						console.log("ERROR");
						$scope.loading=false;
					});
				}
			};
			

			
			$scope.tab = commonService.getFirstWriteSection();
			$scope.showTab($scope.tab);
			
			//FINE GESTIONE TAB
			
			// callback for ng-click logout:
			$scope.logout = function(ev) {			
				
				 var confirm = $mdDialog.confirm()
		          .title('Sicuro di voler uscire?')
		          .textContent('Eventuali modifiche non salvate saranno perse')
		          .ariaLabel('Lucky day')
		          .targetEvent(ev)
		          .ok('Conferma')
		          .cancel('Annulla');

			    $mdDialog.show(confirm).then(function() {
			    	console.log("logout confirmed");
				 	$localStorage.userSession = {};
					$location.path('/');
			    }, function() {
			    	console.log("logout cancel");
			    });
		    
			};

			//$scope.user = getDummyUser($routeParams.id); // TODO
															// SocialCardFactory.show({id:
															// $routeParams.id});
		}

]);
		
		
		
