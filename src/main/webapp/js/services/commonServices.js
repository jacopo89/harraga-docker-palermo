'use strict';

/* Common services */

/*
 * servizi usati da "tutti" come ad esempio retrieve di info generiche di
 * applicazione oppure controlo autorizzazioni
 * 
 */

var services = angular.module('harragapp.services', [ 'ngResource' ]);

function _convertDate(tempDate) {
	var newDate = undefined;
	if (tempDate) {
		newDate = new Date(tempDate);
	}
	return newDate;
};

services
		.factory(
				'commonService',
				[
						'$http',
						'$localStorage',
						function($http, $localStorage) {

							var service = {

								/**
								 * Verifica se l'utente loggato ha i diritti per
								 * visualizzare il campo
								 * 
								 * @function canRead
								 * @memberof socialCardService
								 * @param {subSection}
								 *            nome del campo
								 * @return true/false
								 */
								canRead : function(section, subSection) {

									if (section == undefined) {
										return false;
									}

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									var userPermission = PERMISSION
											.filter(function(obj) {
												return obj.userType == userSession.type
														&& obj.section == section;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0) {
										if (subSection == undefined) {
											return true;
										} else {
											if (userPermission[0].read.indexOf(subSection) >= 0)
												return true;
										}
									}
									return false;
								},

								
								
								/**
								 * CASO SEMPLICE
								 */
								canRead : function(section) {

									if (section == undefined) {
										return false;
									}

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									var userPermission = PERMISSION_F
											.filter(function(obj) {
												return obj.userType == userSession.type
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0
										&& userPermission[0].read.indexOf(section) >= 0)
												return true;
										
									
									return false;
								},
								
								
								getFirstReadSection : function() {
									
									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return null;
									}

									var userPermission = PERMISSION_F
											.filter(function(obj) {
												return obj.userType == userSession.type;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0 )
										return userPermission[0].read[0];
										
									return null;					
									
								},
								
								
								/**
								 * Verifica se l'utente loggato ha i diritti per
								 * modificare il campo
								 * 
								 * @function canWrite
								 * @memberof socialCardService
								 * @param {subSection}
								 *            nome del campo
								 * @return true/false
								 */
								canWrite : function(section , subSection) {

									if ( section == undefined) {
										return false;
									}
									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									var userPermission = PERMISSION
											.filter(function(obj) {
												return obj.userType == userSession.type
														&& obj.section == section;
												;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0) {
										if (subSection == undefined) {
											return true;
										} else {
											if (userPermission[0].write.indexOf(subSection) >= 0)
												return true;
										}
									}
									return false;					
									
								},
								
								
								/**
								 * CASO SEMPLICE
								 */
								
								
								canWrite : function(section) {

									if ( section == undefined) {
										return false;
									}
									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									var userPermission = PERMISSION_F
											.filter(function(obj) {
												return obj.userType == userSession.type;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0 && userPermission[0].write.indexOf(section) >= 0)
												return true;
										
									return false;					
									
								},

								
								getFirstWriteSection : function() {
									
									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return null;
									}

									var userPermission = PERMISSION_F
											.filter(function(obj) {
												return obj.userType == userSession.type;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0 )
										return userPermission[0].write[0];
										
									return null;					
									
								},
								
								/**
								 * Verifica se l'utente loggato ha i diritti per
								 * creare una SC
								 * 
								 * @function canCreate
								 * @memberof socialCardService
								 * @return true/false
								 */
								canCreate : function() {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									if (CREATOR_ROLE.indexOf(userSession.type) > -1)
										return true;
									return false;
								},
								
								/**
								 * Verifica se l'utente loggato è di tipo istituzionale
								 * 
								 * @function isInstitutional
								 * @memberof socialCardService
								 * @return true/false
								 */
								isInstitutional : function(userType) {

									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return false;
									}

									if (INSTITUTIONAL_ROLE.indexOf(userType) > -1)
										return true;
									return false;
								},
								
								/**
								 * Verifica se l'utente loggato ha i diritti per
								 * modificare almeno una sezione della SC
								 * 
								 * @function canEdit
								 * @memberof socialCardService
								 * @return true/false
								 */
								canEdit : function() {
									
									var userSession = $localStorage.userSession;

									if (Object.keys(userSession).length === 0) {
										return null;
									}

									var userPermission = PERMISSION_F
											.filter(function(obj) {
												return obj.userType == userSession.type;
											});

									// console.log(userPermission[0].read);
									if (userPermission.length > 0 )
										if(userPermission[0].write.length >0)
											return true;
										
									return false;					
									
								},
								
							}

							return service;

						} ]);
