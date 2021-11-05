'use strict';

/* Controllers */

var app = angular.module('harragapp.socialCardCreateController', []);




// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function($rootScope, $templateCache) {
//	$rootScope.$on('$viewContentLoaded', function() {
//		$templateCache.removeAll();
//	});
//});


app.controller('SocialCardCreationCtrl', [ '$scope', 'socialCardService', 'commonService', '$location',
		'$localStorage','$mdDialog',
		function($scope, socialCardService, commonService, $location, $localStorage,$mdDialog) {

			$scope.userSession = $localStorage.userSession;

			if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
				$location.path('/login');
			}
			
			$scope.canCreate = function() {
				return commonService.canCreate();
			};
			
//			//inizializzaizone strutture dati vuote 
//			var documentoVuoto = {
//				    tipo: '',
//				    descrizione: '',
//				    allegato: {
//				    	nome: '',
//				    	link: '',
////				    	data: ''
//				+    }
//				  };
//			
//			var domicilioVuoto = {
//				    nome: '',
//				    tipoInserimento: '',
//				    responsabile: '',
//				    email: '',
//				    telefono: ''
//				  };
//			$scope.addDomicilio = function() {
//				$scope.socialCard.anagrafica.domicilio.push(angular.copy(domicilioVuoto));
//			}
//			$scope.addDocumento = function() {
//				$scope.socialCard.anagrafica.documenti.push(angular.copy(documentoVuoto));
//			}
			
//			$scope.socialCard = new Object();
//			$scope.socialCard.anagrafica = new Object();
//			$scope.socialCard.anagrafica.domicilio = [domicilioVuoto];
//			$scope.socialCard.anagrafica.documenti = [documentoVuoto];
			
			//Opzioni precompilate
			$scope.sessoOpzioni= sessoOpzioni;
			//JACOPO : aggiunta italOpzioni
			$scope.italOpzioni = italOpzioni;
			
			// callback for ng-click 'createNewUser':
			$scope.createNewScoialCard = function() {

				var newSocialCard = angular.copy($scope.socialCard);
				
				//newSocialCard.anagrafica.assicurazione.allegato.nome="prova.pdf";
				
				socialCardService.createSocialCard(newSocialCard).then(function(temp) {
					console.log("SUCCESS");
					$location.path('/social-card-edit/' + temp.anagrafica.id);
					
				}, function() {
					console.log("ERROR");
				});				
				
				
				
			
			};
			
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

		} ]);
