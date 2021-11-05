'use strict';

/* Services */

/*
 * http://docs.angularjs.org/api/ngResource.$resource
 * 
 * Default ngResources are defined as
 * 
 * 'get': {method:'GET'}, 'save': {method:'POST'}, 'query': {method:'GET',
 * isArray:true}, 'remove': {method:'DELETE'}, 'delete': {method:'DELETE'}
 * 
 */

var services = angular.module('harragapp.socialCardServices', [ 'ngResource' ]);

services
		.factory(
				'socialCardService',
				[
						'$http',
						'$localStorage',
						'SERVER_INFO',
						function($http, $localStorage, SERVER_INFO) {

							/**
							 * Servizi per il recupero, la creazione e la
							 * manipolazione delle Cartelle Sociali.
							 * 
							 * @namespace socialCardService
							 */
							var service = {

								/**
								 * Recupera una Cartella Sociale in base al suo
								 * ID
								 * 
								 * @function getSocialCard
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} La cartella sociale cercata.
								 *         In caso di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 * 
								 * getSocialCard : function(socialCardId) {
								 * 
								 * var userSession = $localStorage.userSession;
								 * 
								 * if (Object.keys(userSession).length === 0) {
								 * return; }
								 * 
								 * var params = { socialCardId : socialCardId };
								 * 
								 * var requestToken = userSession.token; var req = {
								 * method : 'GET', url :
								 * "http://localhost:8080/Harraga/getSocialCard", //
								 * SERVER_INFO.base_url // + //
								 * SERVER_INFO.getUser_service_url, params :
								 * params, headers : { 'Accept' :
								 * 'application/json', 'Auth-Token' :
								 * requestToken } };
								 * 
								 * var promise = $http(req) .then(
								 * function(response) { console.log(response);
								 * var temp = JSON .parse(response.data);
								 *  // DATE FIXING temp.anagrafica.dataNascita =
								 * _convertDate(temp.anagrafica.dataNascita);
								 * 
								 * temp.anagrafica.dataArrivo =
								 * _convertDate(temp.anagrafica.dataArrivo);
								 * 
								 * temp.anagrafica.UOData =
								 * _convertDate(temp.anagrafica.UOData);
								 * 
								 * if (temp.anagrafica.tutore != undefined)
								 * temp.anagrafica.tutore.dataAssegnazione =
								 * _convertDate(temp.anagrafica.tutore.dataAssegnazione);
								 * 
								 * if (temp.anagrafica.assicurazione !=
								 * undefined)
								 * temp.anagrafica.assicurazione.dataInizio =
								 * _convertDate(temp.anagrafica.assicurazione.dataInizio);
								 * if (temp.anagrafica.assicurazione !=
								 * undefined)
								 * temp.anagrafica.assicurazione.dataFine =
								 * _convertDate(temp.anagrafica.assicurazione.dataFine);
								 * 
								 * return temp; });
								 * 
								 * return promise; },
								 */

								/**
								 * Recupera Anagrafica in base al suo ID
								 * 
								 * @function getAnagrafica
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} L'anagrafica cercata. In
								 *         caso di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getAnagrafica : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_anagrafica_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req)
											.then(
													function(response) {
														console.log(response);
														var temp = JSON
																.parse(response.data);

														// DATE FIXING
														temp.dataNascitaDichiarata = _convertDate(temp.dataNascitaDichiarata);
														temp.dataNascitaCorretta = _convertDate(temp.dataNascitaCorretta);

														temp.dataArrivo = _convertDate(temp.dataArrivo);

														temp.UOData = _convertDate(temp.UOData);

														if (temp.tutore != undefined)
															temp.tutore.dataAssegnazione = _convertDate(temp.tutore.dataAssegnazione);

														if (temp.assicurazione != undefined)
															temp.assicurazione.dataInizio = _convertDate(temp.assicurazione.dataInizio);
														if (temp.assicurazione != undefined)
															temp.assicurazione.dataFine = _convertDate(temp.assicurazione.dataFine);
														if(temp.domicilio != undefined){
															temp.domicilio.forEach(function(dom){
																dom.tempoPermanenza = _convertDate(dom.tempoPermanenza);
															})
													}
															
														return temp;
													});

									return promise;
								},

								/**
								 * Recupera Amministrativa in base al suo ID
								 * 
								 * @function getAmministrativa
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} L'Amministrativa cercata. In
								 *         caso di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getAmministrativa : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_amministrativa_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);
												if(temp==null){
													temp = new Object();
												}
												// DATE FIXING
										
												
												if (temp.permessoSoggiorno != undefined){
													temp.permessoSoggiorno.forEach(function(evento){
														evento.dataScadenza = _convertDate(evento.dataScadenza);
														evento.dataRilascio = _convertDate(evento.dataRilascio);
														evento.dataRichiesta = _convertDate(evento.dataRichiesta);
													})
												}
												// JACOPO : aggiunta la data dublino
												if (temp.procedureLegali != undefined){
													temp.procedureLegali.forEach(function(procLeg){
														procLeg.dublino.data = _convertDate(procLeg.dublino.data);
														
														procLeg.appuntamenti.forEach(function(appunt){
															appunt.data = _convertDate(appunt.data);
														})	
														procLeg.ricorsiAmministrativi.forEach(function(ricorso){
															ricorso.data = _convertDate(ricorso.data);
															ricorso.udienze.forEach(function(udienza){
																udienza.dataUdienza = _convertDate(udienza.dataUdienza);
															})
														})	
													})
												}
												if (temp.provvedimentiGiudiziari != undefined){
													temp.provvedimentiGiudiziari.forEach(function(provGiud){
														provGiud.data = _convertDate(provGiud.data);
													})
												}
												if (temp.delegaAmministrativa != undefined){
													temp.delegaAmministrativa.data = _convertDate(temp.delegaAmministrativa.data);	
												}
												if (temp.fotoSegnalazione != undefined){
                                                    temp.fotoSegnalazione.data = _convertDate(temp.fotoSegnalazione.data);
                                                }
												if (temp.proseguo21 != undefined){
													temp.proseguo21.dataFinale = _convertDate(temp.proseguo21.dataFinale);
													temp.proseguo21.dataAttribuzione = _convertDate(temp.proseguo21.dataAttribuzione);	
												}

												if (temp.affidoAmministrativo != undefined){
                                                    temp.affidoAmministrativo.dataProvvedimentoAffidamento = _convertDate(temp.affidoAmministrativo.dataProvvedimentoAffidamento);
                                                    temp.affidoAmministrativo.dataVerbaleAffidamento = _convertDate(temp.affidoAmministrativo.dataVerbaleAffidamento);
                                                }
												
												
												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Sanitaria in base all'ID
								 * 
								 * @function getSanitaria
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Sanitaria cercata. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getSanitaria : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_sanitaria_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												if (temp){
													if (temp.eventiMedici != undefined){
														temp.eventiMedici.forEach(function(evento){
															evento.data = _convertDate(evento.data);
														})
													}
												}
												
												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Storia in base all'ID
								 * 
								 * @function getStoria
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} La Storia cercata. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getStoria : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_storia_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												
												// DATE FIXING
												if(temp!=undefined && temp.percorsoMigratorio != undefined){
													if(temp.percorsoMigratorio.paesiAttraversati != undefined){
														temp.percorsoMigratorio.paesiAttraversati.forEach(function(evento){
															evento.dataArrivo = _convertDate(evento.dataArrivo);
															evento.dataPartenza = _convertDate(evento.dataPartenza);
														})
													}
												}
												if (temp!=undefined && temp.accoglienza != undefined){
													if (temp.accoglienza.hotSpotDataIngresso != undefined)
														temp.accoglienza.hotSpotDataIngresso = _convertDate(temp.accoglienza.hotSpotDataIngresso);
														if (temp.accoglienza.hotSpotDataUscita != undefined)
															temp.accoglienza.hotSpotDataUscita = _convertDate(temp.accoglienza.hotSpotDataUscita);
														if (temp.accoglienza.cpaDataIngresso != undefined)
															temp.accoglienza.cpaDataIngresso = _convertDate(temp.accoglienza.cpaDataIngresso);
														if (temp.accoglienza.cpaDataUscita != undefined)
															temp.accoglienza.cpaDataUscita = _convertDate(temp.accoglienza.cpaDataUscita);
														if (temp.accoglienza.secondaAccoglienzaDataIngresso != undefined)
															temp.accoglienza.secondaAccoglienzaDataIngresso = _convertDate(temp.accoglienza.secondaAccoglienzaDataIngresso);
														if (temp.accoglienza.secondaAccoglienzaDataUscita != undefined)
															temp.accoglienza.secondaAccoglienzaDataUscita = _convertDate(temp.accoglienza.secondaAccoglienzaDataUscita);
														
														if(temp.accoglienza.affidi !=undefined){
															temp.accoglienza.affidi.forEach(function(evento){
																evento.dataAffido = _convertDate(evento.dataAffido);
													
															})
														}
														
														if(temp.accoglienza.adozioni !=undefined){
															temp.accoglienza.adozioni.forEach(function(evento){
																evento.dataAdozione = _convertDate(evento.dataAdozione);
													
															})
														}
														
														if (temp.accoglienza.allontanamentiRitrovamenti != undefined){
															temp.accoglienza.allontanamentiRitrovamenti.forEach(function(evento){
																evento.data = _convertDate(evento.data);
														})
													}
												}
												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Istruzione in base all'ID
								 * 
								 * @function getIstruzione
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Istruzione cercata. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getIstruzione : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_istruzione_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												if (temp){
													if (temp.istruzioneItaliaConculsi != undefined){
														temp.istruzioneItaliaConculsi.forEach(function(evento){
															evento.inizio = _convertDate(evento.inizio);
															evento.fine = _convertDate(evento.fine);
														})
													}
													if (temp.istruzioneItaliainCorso != undefined){
														temp.istruzioneItaliainCorso.forEach(function(evento){
															evento.inizio = _convertDate(evento.inizio);
															evento.fine = _convertDate(evento.fine);
														})
													}
												}
												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Lavoro in base all'ID
								 * 
								 * @function getLavoro
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Lavoro cercata. In caso di
								 *         user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getLavoro : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_lavoro_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												if (temp){
													temp.forEach(function(evento){
														evento.dataInizio = _convertDate(evento.dataInizio);
														evento.dataFine = _convertDate(evento.dataFine);														
													})
													
												}
//												temp.dataInizio = _convertDate(temp.dataInizio);
//												temp.dataFine = _convertDate(temp.dataFine);

												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Competenze in base all'ID
								 * 
								 * @function getCompetenze
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Competenze cercata. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getCompetenze : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_competenze_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												// TODO

												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Sociale in base all'ID
								 * 
								 * @function getSociale
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Sociale cercata. In caso di
								 *         user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getSociale : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_sociale_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												if(temp!= undefined){
													if (temp.volontariato!= undefined){
														temp.volontariato.forEach(function(evento){
															evento.dataInizio = _convertDate(evento.dataInizio);
															evento.dataFine = _convertDate(evento.dataFine);
														})
													}
													if (temp.laboratori!= undefined){
														temp.laboratori.forEach(function(evento){
															evento.dataInizio = _convertDate(evento.dataInizio);
															evento.dataFine = _convertDate(evento.dataFine);
														})
													}
													if (temp.sport!= undefined){
														temp.sport.forEach(function(evento){
															evento.dataInizio = _convertDate(evento.dataInizio);
															evento.dataFine = _convertDate(evento.dataFine);
														})
													}
													if (temp.gruppiAss!= undefined){
														temp.gruppiAss.forEach(function(evento){
															evento.dataInizio = _convertDate(evento.dataInizio);
															evento.dataFine = _convertDate(evento.dataFine);
														})
													}
												}

												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Penale in base all'ID
								 * 
								 * @function getPenale
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} Penale cercata. In caso di
								 *         user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getPenale : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_penale_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);

												// DATE FIXING
												// TODO

												return temp;
											});

									return promise;
								},

								/**
								 * Recupera Desideri in base al suo ID
								 * 
								 * @function getDesideri
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} I desideri cercati. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getDesideri : function(socialCardId, timestamp) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId, 
										timestamp : timestamp
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_desideri_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												var temp = JSON
														.parse(response.data);
												return temp;
											});

									return promise;
								},
								/**
								 * Crea nuova socialCard in remoto
								 * 
								 * @function createSocialCard
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								createSocialCard : function(_socialCard) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									// CONVERT DATE TO MILLIS
									if (_socialCard.anagrafica.dataNascitaDichiarata != undefined)
										_socialCard.anagrafica.dataNascitaDichiarata = _socialCard.anagrafica.dataNascitaDichiarata
												.getTime();
									if (_socialCard.anagrafica.dataNascitaCorretta != undefined)
										_socialCard.anagrafica.dataNascitaCorretta = _socialCard.anagrafica.dataNascitaCorretta
												.getTime();
									if (_socialCard.anagrafica.dataArrivo != undefined)
										_socialCard.anagrafica.dataArrivo = _socialCard.anagrafica.dataArrivo
												.getTime();
									if (_socialCard.anagrafica.UOData != undefined)
										_socialCard.anagrafica.UOData = _socialCard.anagrafica.UOData
												.getTime();

									var params = {
										socialCard : _socialCard
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.create_social_card_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},

								/**
								 * Salva/aggiorna Anagrafica
								 * 
								 * @function updateAnagrafica
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateAnagrafica : function(socialCardId,
										anagrafica) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									// CONVERT DATE TO MILLIS
									if (anagrafica.dataNascitaDichiarata != undefined)
										anagrafica.dataNascitaDichiarata = anagrafica.dataNascitaDichiarata
												.getTime();
									if (anagrafica.dataNascitaCorretta != undefined)
										anagrafica.dataNascitaCorretta = anagrafica.dataNascitaCorretta
												.getTime();
									if (anagrafica.dataArrivo != undefined)
										anagrafica.dataArrivo = anagrafica.dataArrivo
												.getTime();
									if (anagrafica.UOData != undefined)
										anagrafica.UOData = anagrafica.UOData
												.getTime();
									if (anagrafica.tutore != undefined
											&& anagrafica.tutore.dataAssegnazione != undefined)
										anagrafica.tutore.dataAssegnazione = anagrafica.tutore.dataAssegnazione
												.getTime();
									if (anagrafica.assicurazione != undefined
											&& anagrafica.assicurazione.dataInizio != undefined)
										anagrafica.assicurazione.dataInizio = anagrafica.assicurazione.dataInizio
												.getTime();
									if (anagrafica.assicurazione != undefined
											&& anagrafica.assicurazione.dataFine != undefined)
										anagrafica.assicurazione.dataFine = anagrafica.assicurazione.dataFine
												.getTime();
									if (anagrafica.domicilio != undefined){
										anagrafica.domicilio.forEach(function(time){
											time.tempoPermanenza = time.tempoPermanenza.getTime();
										})
									}

									var params = {
										socialCardId : socialCardId,
										anagrafica : anagrafica
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_anagrafica_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								/**
								 * Salva/aggiorna Amministrativa
								 * 
								 * @function updateAmministrativa
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateAmministrativa : function(_socialCardId, _amministrativa) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if (_amministrativa.permessoSoggiorno != undefined){
										_amministrativa.permessoSoggiorno.forEach(function(evento){
											evento.dataScadenza = evento.dataScadenza.getTime();
										})
									}
									//JACOPO : AGGIUNTO dublin.data
									if (_amministrativa.procedureLegali != undefined){
										_amministrativa.procedureLegali.forEach(function(procLeg){
											
											procLeg.dublino.data = procLeg.dublino.data.getTime();
											
											
											procLeg.appuntamenti.forEach(function(appunt){
												appunt.data = appunt.data.getTime();
											})
											
											procLeg.ricorsiAmministrativi.forEach(function(ricorso){
												ricorso.data = ricorso.data.getTime();
												ricorso.udienze.forEach(function (udienza){
													udienza.dataUdienza = udienza.dataUdienza.getTime();
												})
											
											})
											
											
										})
									}
									
									if (_amministrativa.provvedimentiGiudiziari != undefined){
										_amministrativa.provvedimentiGiudiziari.forEach(function(provGiud){
											
											provGiud.data = provGiud.data.getTime();
										})
									}
									if(_amministrativa.proseguo21 != undefined){
										_amministrativa.proseguo21.dataFinale = _amministrativa.proseguo21.dataFinale.getTime();
										_amministrativa.proseguo21.dataAttribuzione = _amministrativa.proseguo21.dataAttribuzione.getTime();
									}

									if (_amministrativa.delegaAmministrativa != undefined){
										_amministrativa.delegaAmministrativa.data = _amministrativa.delegaAmministrativa.data.getTime();
									}

									if (_amministrativa.fotoSegnalazione != undefined){
									    if(_amministrativa.fotoSegnalazione.data != undefined){
									        _amministrativa.fotoSegnalazione.data = _amministrativa.fotoSegnalazione.data.getTime();
									    }
                                    }

									if (_amministrativa.affidoAmministrativo != undefined){
									    if(_amministrativa.affidoAmministrativo.dataVerbaleAffidamento != undefined){
									       _amministrativa.affidoAmministrativo.dataVerbaleAffidamento = _amministrativa.affidoAmministrativo.dataVerbaleAffidamento.getTime();
									    }
									    if(_amministrativa.affidoAmministrativo.dataProvvedimentoAffidamento != undefined){
                                           _amministrativa.affidoAmministrativo.dataProvvedimentoAffidamento = _amministrativa.affidoAmministrativo.dataProvvedimentoAffidamento.getTime();
                                        }
									}
									
									
									var params = {		
										socialCardId: _socialCardId,
										amministrativa : _amministrativa
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_amministrativa_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								

								/**
								 * Salva/aggiorna Storia
								 * 
								 * @function updateStoria
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateStoria : function(_socialCardId, _storia) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if(_storia.percorsoMigratorio != undefined){
										if(_storia.percorsoMigratorio.paesiAttraversati != undefined){
											_storia.percorsoMigratorio.paesiAttraversati.forEach(function(evento){
												evento.dataArrivo = evento.dataArrivo.getTime();
												evento.dataPartenza = evento.dataPartenza.getTime();
											})
										}
									}
									
									if (_storia.accoglienza != undefined){
										if (_storia.accoglienza.hotSpotDataIngresso != undefined)
										_storia.accoglienza.hotSpotDataIngresso = _storia.accoglienza.hotSpotDataIngresso.getTime();
										if (_storia.accoglienza.hotSpotDataUscita != undefined)
											_storia.accoglienza.hotSpotDataUscita = _storia.accoglienza.hotSpotDataUscita.getTime();
										if (_storia.accoglienza.cpaDataIngresso != undefined)
											_storia.accoglienza.cpaDataIngresso = _storia.accoglienza.cpaDataIngresso.getTime();
										if (_storia.accoglienza.cpaDataUscita != undefined)
											_storia.accoglienza.cpaDataUscita = _storia.accoglienza.cpaDataUscita.getTime();
										if (_storia.accoglienza.secondaAccoglienzaDataIngresso != undefined)
											_storia.accoglienza.secondaAccoglienzaDataIngresso = _storia.accoglienza.secondaAccoglienzaDataIngresso.getTime();
										if (_storia.accoglienza.secondaAccoglienzaDataUscita != undefined)
											_storia.accoglienza.secondaAccoglienzaDataUscita = _storia.accoglienza.secondaAccoglienzaDataUscita.getTime();
										
										if (_storia.accoglienza.allontanamentiRitrovamenti != undefined){
											_storia.accoglienza.allontanamentiRitrovamenti.forEach(function(evento){
												evento.data = evento.data.getTime();
											})
										}
										if (_storia.accoglienza.affidi != undefined){
											_storia.accoglienza.affidi.forEach(function(evento){
												evento.dataAffido = evento.dataAffido.getTime();
											})
										}
										if (_storia.accoglienza.adozioni != undefined){
											_storia.accoglienza.adozioni.forEach(function(evento){
												evento.dataAdozione = evento.dataAdozione.getTime();
											})
										}
										
									}
									
									
									var params = {		
										socialCardId: _socialCardId,
										storia : _storia
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_storia_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								
								/**
								 * Salva/aggiorna Sanitaria
								 * 
								 * @function updateSanitaria
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateSanitaria : function(_socialCardId, _sanitaria) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if (_sanitaria.eventiMedici != undefined){
										_sanitaria.eventiMedici.forEach(function(evento){
											evento.data = evento.data.getTime();
										})
									}
																		
									
									var params = {		
										socialCardId: _socialCardId,
										sanitaria : _sanitaria
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_sanitaria_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								/**
								 * Salva/aggiorna Istruzione
								 * 
								 * @function updateIstruzione
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateIstruzione : function(_socialCardId, _istruzione) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if (_istruzione.istruzioneItaliaConculsi != undefined){
										_istruzione.istruzioneItaliaConculsi.forEach(function(evento){
											evento.inizio = evento.inizio.getTime();
											evento.fine = evento.fine.getTime();
										})
									}
									if (_istruzione.istruzioneItaliainCorso != undefined){
										_istruzione.istruzioneItaliainCorso.forEach(function(evento){
											evento.inizio = evento.inizio.getTime();
											evento.fine = evento.fine.getTime();
										})
									}
									
									
									var params = {		
										socialCardId: _socialCardId,
										istruzione : _istruzione
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_istruzione_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								/**
								 * Salva/aggiorna Lavoro
								 * 
								 * @function updateLavoro
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateLavoro : function(_socialCardId, _lavoro) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if (_lavoro){
										_lavoro.forEach(function(evento){
											evento.dataInizio = evento.dataInizio.getTime();
											evento.dataFine = evento.dataFine.getTime();
										})
										
									}
									
									
									var params = {		
										socialCardId: _socialCardId,
										lavoro : _lavoro
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_lavoro_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								/**
								 * Salva/aggiorna Sociale
								 * 
								 * @function updateSociale
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateSociale : function(_socialCardId, _sociale) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									if (_sociale.volontariato!= undefined){
										_sociale.volontariato.forEach(function(evento){
											evento.dataInizio = evento.dataInizio.getTime();
											evento.dataFine = evento.dataFine.getTime();
										})
									}
									if (_sociale.laboratori!= undefined){
										_sociale.laboratori.forEach(function(evento){
											evento.dataInizio = evento.dataInizio.getTime();
											evento.dataFine = evento.dataFine.getTime();
										})
									}
									if (_sociale.sport!= undefined){
										_sociale.sport.forEach(function(evento){
											evento.dataInizio = evento.dataInizio.getTime();
											evento.dataFine = evento.dataFine.getTime();
										})
									}
									if (_sociale.gruppiAss!= undefined){
										_sociale.gruppiAss.forEach(function(evento){
											evento.dataInizio = evento.dataInizio.getTime();
											evento.dataFine = evento.dataFine.getTime();
										})
									}
									
									
									var params = {		
										socialCardId: _socialCardId,
										sociale : _sociale
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_sociale_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								/**
								 * Salva/aggiorna Competenze
								 * 
								 * @function updateCompetenze
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateCompetenze : function(_socialCardId, _competenze) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									//TODO
									
									
									var params = {		
										socialCardId: _socialCardId,
										competenze : _competenze
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_competenze_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								/**
								 * Salva/aggiorna Desideri
								 * 
								 * @function updateDesideri
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updateDesideri : function(_socialCardId, desideri) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {				
										socialCardId: _socialCardId,
										desideri : desideri
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_desideri_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								/**
								 * Salva/aggiorna Penale
								 * 
								 * @function updatePenale
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								updatePenale : function(_socialCardId, _penale) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									
									// CONVERT DATE TO MILLIS
									//TODO
									
									
									var params = {		
										socialCardId: _socialCardId,
										penale : _penale
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.update_penale_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								

								/**
								 * Recupera lista di tutte le cartelle sociali
								 * 
								 * @function getSocialCardList
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								getSocialCardList : function() {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_social_card_list_service_url,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								// JACOPO : AGGIUNTA CHIAMATA REST PER ITALIANI
								/**
								 * Recupera lista di tutte le cartelle sociali di italiani
								 * 
								 * @function getItalianiList
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								getItalianiList : function() {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
										// bisogna chiamare la URL diversa
												+ SERVER_INFO.get_italiani_list_service_url,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								// JACOPO : AGGIUNTA CHIAMATA REST PER Maggiorenni
								/**
								 * Recupera lista di tutte le cartelle sociali di maggiorenni >18
								 * 
								 * @function getMaggiorenniList
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								getMaggiorenniList : function() {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
										// bisogna chiamare la URL diversa
												+ SERVER_INFO.get_maggiorenni_list_service_url,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								

								/**
								 * Elimina una Cartella Sociale in base al suo
								 * ID
								 * 
								 * @function deleteSocialCard
								 * @memberof socialCardService
								 * @param {Number}
								 *            idSocialCard L'id della cartella
								 *            sociale
								 * @return {Object} Lo user da eliminare
								 */
								deleteSocialCard : function(idSocialCard) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : idSocialCard
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.delete_social_Card_service_url,
										params : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},

								/**
								 * Recupera file protetto se autorizzato
								 * 
								 * @function getFile
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								getFile : function(fileName, socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										fileName : fileName,
										socialCardId : socialCardId
									}

									var requestToken = userSession.token;

									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.download_service_url, // getUser_service_url,
										params : params,
										headers : {
											'Accept' : 'application/pdf',
											'Auth-Token' : requestToken,
										},
										responseType : 'arraybuffer',
									};

									var promise = $http(req).then(
											function(response) {
												// console.log(response);
												return response.data;
											});

									return promise;
								},

								
								/**
								 * Recupera folder zip protetto se autorizzato
								 * 
								 * @function downloadFolder
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								downloadFolder : function(section, socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										section : section,
										socialCardId : socialCardId
									}

									var requestToken = userSession.token;

									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.download_folder_service_url,
										params : params,
										headers : {
											'Accept' : 'application/zip',
											'Auth-Token' : requestToken,
										},
										responseType : 'arraybuffer',
									};

									var promise = $http(req).then(
											function(response) {
												// console.log(response);
												return response.data;
											});

									return promise;
								},
								
								
								
								
								/**
								 * 
								 * 
								 * @function importSocialCardList
								 * @memberof socialCardService
								 * @return {Array[Object]}
								 */
								importSocialCardList : function(_socialCardList) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}
									// CONVERT DATE TO MILLIS
									
									
									var params = {
											socialCardList : _socialCardList
									};

									var requestToken = userSession.token;
									var req = {
										method : 'POST',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.import_social_card_list_service_url,
										data : params,
										headers : {
											'Accept' : 'application/json',
											'Auth-Token' : requestToken,
											'Content-Type' : 'application/json',
											'dataType' : 'json',
										}
									};

									var promise = $http(req).then(
											function(response) {
												console.log(response);
												return JSON
														.parse(response.data);
											});

									return promise;
								},
								
								
								
							}

							return service;

						} ]);