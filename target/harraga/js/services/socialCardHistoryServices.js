'use strict';

var services = angular.module('harragapp.socialCardHistoryServices', [ 'ngResource' ]);

services
		.factory(
				'socialCardHistoryService',
				[
						'$http',
						'$localStorage',
						'SERVER_INFO',
						function($http, $localStorage, SERVER_INFO) {
							
							var service = {

									
									/**
									 * Recupera ANAGRAFICA History in base al suo ID
									 * 
									 * @function getAnagraficaHistory
									 * @memberof socialCardService
									 * @param {Number}
									 *            idUser L'id della cartella sociale
									 * @return {Object}
									 */
									getAnagraficaHistory : function(socialCardId) {

										var userSession = $localStorage.userSession;

										if (Object.keys(userSession).length === 0) {
											return;
										}

										var params = {
											socialCardId : socialCardId
										};

										var requestToken = userSession.token;
										var req = {
											method : 'GET',
											url : SERVER_INFO.base_url
													+ SERVER_INFO.get_anagrafica_history_service_url,
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
									 * Recupera Istruzione History in base al suo ID
									 * 
									 * @function getIstruzioneHistory
									 * @memberof socialCardService
									 * @param {Number}
									 *            idUser L'id della cartella sociale
									 * @return {Object}
									 */
									getIstruzioneHistory : function(socialCardId) {

										var userSession = $localStorage.userSession;

										if (Object.keys(userSession).length === 0) {
											return;
										}

										var params = {
											socialCardId : socialCardId
										};

										var requestToken = userSession.token;
										var req = {
											method : 'GET',
											url : SERVER_INFO.base_url
													+ SERVER_INFO.get_istruzione_history_service_url,
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
									 * Recupera Amministrativa History in base al suo ID
									 * 
									 * @function getAmministrativaHistory
									 * @memberof socialCardService
									 * @param {Number}
									 *            idUser L'id della cartella sociale
									 * @return {Object}
									 */
									getAmministrativaHistory : function(socialCardId) {

										var userSession = $localStorage.userSession;

										if (Object.keys(userSession).length === 0) {
											return;
										}

										var params = {
											socialCardId : socialCardId
										};

										var requestToken = userSession.token;
										var req = {
											method : 'GET',
											url : SERVER_INFO.base_url
													+ SERVER_INFO.get_amministrativa_history_service_url,
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
								 * Recupera Desideri History in base al suo ID
								 * 
								 * @function getDesideriHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object} I desideri cercati. In caso
								 *         di user inesistente o non
								 *         visualizzahbile dall'utente il
								 *         servizio REST risponderà con un
								 *         errore 404.
								 */
								getDesideriHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_desideri_history_service_url,
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
								 * Recupera Competenze History in base al suo ID
								 * 
								 * @function getCompetenzeHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getCompetenzeHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_competenze_history_service_url,
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
								 * Recupera Penale History in base al suo ID
								 * 
								 * @function getPenaleHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getPenaleHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_penale_history_service_url,
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
								 * Recupera Lavoro History in base al suo ID
								 * 
								 * @function getLavoroHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getLavoroHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_lavoro_history_service_url,
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
								 * Recupera Sanitaria History in base al suo ID
								 * 
								 * @function getSanitariaHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getSanitariaHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_sanitaria_history_service_url,
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
								 * Recupera Storia History in base al suo ID
								 * 
								 * @function getStoriaHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getStoriaHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_storia_history_service_url,
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
								 * Recupera Sociale History in base al suo ID
								 * 
								 * @function getSocialeHistory
								 * @memberof socialCardService
								 * @param {Number}
								 *            idUser L'id della cartella sociale
								 * @return {Object}
								 */
								getSocialeHistory : function(socialCardId) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return;
									}

									var params = {
										socialCardId : socialCardId
									};

									var requestToken = userSession.token;
									var req = {
										method : 'GET',
										url : SERVER_INFO.base_url
												+ SERVER_INFO.get_sociale_history_service_url,
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

							}

							return service;

						} ]);