'use strict';

var services = angular.module('harragapp.userServices', [ 'ngResource' ]);

services.factory('userService', [ '$http', '$localStorage','SERVER_INFO',
		function($http, $localStorage,SERVER_INFO) {

			/**
			 * Servizio per il login
			 *
			 * @namespace userService
			 */
			var service = {

				/**
				 * Effettua login
				 *
				 * @function login
				 * @memberof userService
				 * @param {LoginData}
				 *            dati per il login
				 * @return {String} token
				 */
				login : function(username, password) {

					var _loginData = {
						username : username,
						password : password
					}

					var params = {
						loginData : _loginData
					};

					var req = {
						method : 'POST',
						url : SERVER_INFO.base_url + SERVER_INFO.login_service_url,
						data : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},

				/**
				 * Effettua registrazione nuovo utente
				 *
				 * @function register
				 * @memberof userService
				 * @param {User}
				 *            dati per la registrazione
				 * @return {Boolean} status
				 */
				register : function(_user) {

					var params = {
						user : _user
					};

					var req = {
						method : 'POST',
						url :  SERVER_INFO.base_url + SERVER_INFO.register_service_url,//"http://localhost:8080/Harraga/register",
						data : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
						}
					};

					var promise = $http(req).then(function(response) {
					  console.log("Parametri", params);
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},

				/**
				 * Effettua update utente (puo farlo il comune e l'utente stesso)
				 *
				 * @function update
				 * @memberof userService
				 * @param {User}
				 *            dati per la registrazione
				 * @return {Boolean} status
				 */
				updateUser : function(_user) {

					var userSession = $localStorage.userSession;

					if (Object.keys(userSession).length === 0) {
						return;
					}

					var params = {
						user : _user
					};

					var requestToken = userSession.token;

					var req = {
						method : 'POST',
						url : SERVER_INFO.base_url + SERVER_INFO.update_user_service_url,//"http://localhost:8080/Harraga/updateUser",
						data : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
							'Auth-Token' : requestToken
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},


				/**
				 * Effettua associazione utente/cartella (solo il comune puo farlo)
				 *
				 * @function associaUser
				 * @memberof userService
				 * @param {_username, _scoialCardId}
				 *           identificativi di utente e cartella
				 * @return {Boolean} status
				 */
				associaUser : function(_username, _scoialCardId) {

					var userSession = $localStorage.userSession;

					if (Object.keys(userSession).length === 0) {
						return;
					}

					var params = {
						username : _username,
						socialCardId: _scoialCardId
					};

					var requestToken = userSession.token;

					var req = {
						method : 'POST',
						url :  SERVER_INFO.base_url + SERVER_INFO.associa_user_service_url,//"http://localhost:8080/Harraga/associaUser",
						data : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
							'Auth-Token' : requestToken
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},


				/**
				 * Effettua la rimozione dell'associazione utente/cartella (solo il comune puo farlo)
				 *
				 * @function associaUser
				 * @memberof userService
				 * @param {_username, _scoialCardId}
				 *           identificativi di utente e cartella
				 * @return {Boolean} status
				 */
				deassociaUser : function(_username, _scoialCardId) {

					var userSession = $localStorage.userSession;

					if (Object.keys(userSession).length === 0) {
						return;
					}

					var params = {
						username : _username,
						socialCardId: _scoialCardId
					};

					var requestToken = userSession.token;

					var req = {
						method : 'POST',
						url : SERVER_INFO.base_url + SERVER_INFO.deassocia_user_service_url,//"http://localhost:8080/Harraga/deassociaUser",
						data : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
							'Auth-Token' : requestToken
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},

				/**
				 * recupera lista di utenti
				 *
				 * @function register
				 * @memberof userService
				 * @param {User}
				 *            dati per la registrazione
				 * @return {Boolean} status
				 */
				getUserList : function(_user) {

					var userSession = $localStorage.userSession;

					if (Object.keys(userSession).length === 0) {
						return;
					}

					var requestToken = userSession.token;

					var req = {
						method : 'GET',
						url : SERVER_INFO.base_url + SERVER_INFO.get_user_list_service_url,//"http://localhost:8080/Harraga/getUserList",
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
							'Auth-Token' : requestToken
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},

				/**
				 * Recupera User
				 *
				 * @function getUser
				 * @memberof userService
				 * @param {User}
				 *            dati per la registrazione
				 * @return {User} user
				 */
				getUser : function(_username) {

					var userSession = $localStorage.userSession;
					if (Object.keys(userSession).length === 0) {
						return;
					}
					var requestToken = userSession.token;

					var params = {
						username : _username
					};

					var req = {
						method : 'GET',
						url : SERVER_INFO.base_url + SERVER_INFO.get_user_service_url,//"http://localhost:8080/Harraga/getUser",
						params : params,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json',
							'dataType' : 'json',
							'Auth-Token' : requestToken
						}
					};

					var promise = $http(req).then(function(response) {
						console.log(response);
						return JSON.parse(response.data);
					});

					return promise;
				},
			}

			return service;

		} ]);
