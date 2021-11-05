'use strict';

/* Controllers */

var app = angular.module('harragapp.socialCardImportController', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
// app.run(function($rootScope, $templateCache) {
// $rootScope.$on('$viewContentLoaded', function() {
// $templateCache.removeAll();
// });
// });

app.controller('SocialCardImportCtrl', [
		'$scope',
		'$location',
		'socialCardService',		
		'$localStorage',
		'$mdDialog',
		function($scope, $location, socialCardService, $localStorage, $mdDialog ,
				) {

			$scope.userSession = $localStorage.userSession;

			if ($scope.userSession == undefined
					|| Object.keys($scope.userSession).length === 0) {
				$location.path('/login');
			}

//			$scope.socialCard = {};
//			$scope.socialCard.id = $routeParams.id;

			

			// callback for ng-click 'home':
			$scope.back = function() {
				$location.path('/');
			};
			
			$scope.importSocialCardList  = function(ev) {
				
				
				
				
				
				
				
//				var jsonObject = JSON.parse($scope.socialCardImportList);
//				console.log(jsonObject );
				
				var jsonObject = csvJSON($scope.fileContent);
				
				console.log(jsonObject );
				
				
				socialCardService.importSocialCardList(jsonObject).then(function(temp) {				
					
					$mdDialog.show(
						      $mdDialog.alert()
						        .parent(angular.element(document.querySelector('#popupContainer')))
						        .clickOutsideToClose(true)
						        .title('Import avvenuto con successo!')
						        .textContent("Import avvenuto con successo")
						        .ariaLabel('Alert Dialog Demo')
						        .ok('OK!')
						        .targetEvent(ev)
						    );
					
					
					
					console.log("SUCCESS");
				}, function() {
					console.log("ERROR");
				});
				
				
			};
			
			
			// callback for ng-click logout:
			$scope.logout = function(ev) {

				var confirm = $mdDialog.confirm().title(
						'Sicuro di voler uscire?').textContent(
						'Eventuali modifiche non salvate saranno perse')
						.ariaLabel('Lucky day').targetEvent(ev).ok('Conferma')
						.cancel('Annulla');

				$mdDialog.show(confirm).then(function() {
					console.log("logout confirmed");
					$localStorage.userSession = {};
					$location.path('/');
				}, function() {
					console.log("logout cancel");
				});

			};
			
			
			//var csv is the CSV file with headers
			function csvJSON(csv){

			  var lines=csv.split("\n");

			  var result = [];

			  var headers=lines[0].split(";");

			  for(var i=1;i<lines.length;i++){

				  var obj = {};
				  var currentline=lines[i].split(";");

				  if(currentline.length == headers.length ){
					  for(var j=0;j<headers.length;j++){
						  obj[headers[j]] = currentline[j];
					  }
	
					  obj.dataNascitaDichiarata = moment(obj.dataNascitaDichiarata, "DD/MM/YYYY").toDate().getTime();
					  obj.dataNascitaCorretta = moment(obj.dataNascitaCorretta, "DD/MM/YYYY").toDate().getTime();
					  obj.dataArrivo = moment(obj.dataArrivo, "DD/MM/YYYY").toDate().getTime();
					  obj.UOData = moment(obj.UOData, "DD/MM/YYYY").toDate().getTime();

					  result.push(obj);
				  }

			  }
			  
			  return result; //JavaScript object
			  //return JSON.stringify(result); //JSON
			}

		} ]);
