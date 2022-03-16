'use strict';

/* Controllers */

var app = angular.module('harragapp.controllers', []);




// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function($rootScope, $templateCache) {
//	$rootScope.$on('$viewContentLoaded', function() {
//		$templateCache.removeAll();
//	});
//});

app.controller('LoginCtrl', [
		'$scope',
		'userService',
		'$location',
		'$localStorage',
		'$mdDialog',
		function($scope, userService, $location, $localStorage, $mdDialog) {

			//TODO retrieve from server
			  $scope.data = {
					availableOptions: ruoli
			  };

			// callback for ng-click 'login':
			$scope.login = function(ev) {
				console.log("login: " + $scope.loginData.username + " - "
						+ $scope.loginData.password);


				userService.login($scope.loginData.username, $scope.loginData.password).then(function(temp) {

					if(temp.username!=undefined){
						var userSession = {
								username : temp.username,
								type : temp.type,
								token : temp.token,
							}

						console.log("SUCCESS");

						$localStorage.userSession = userSession;

						$location.path('/social-card-list');
					}
				}, function(temp) {
					var textContent ="";
					if(temp.status==401){
						textContent = "Verifica username e password"
					}else if(temp.status==451){
						textContent = "Utente non abilitato, rivolgersi al Comune di Marsala"
					}

					$mdDialog.show(
						      $mdDialog.alert()
						        .parent(angular.element(document.querySelector('#popupContainer')))
						        .clickOutsideToClose(true)
						        .title('Login fallito!')
						        .textContent(textContent)
						        .ariaLabel('Alert Dialog Demo')
						        .ok('OK!')
						        .targetEvent(ev)
						    );
					$scope.loginFailed= true;
					console.log("ERROR");
				});
			};



			// callback for ng-click 'login':
			$scope.register = function(ev) {
				console.log("register: " + $scope.user);



				var invalidUsername = $scope.user.username.length < 3;
				var invalidPassword = $scope.user.password.length < 3;
				var mismatchPassword =  $scope.user.password != $scope.user.repeatPassword



				if ( invalidUsername ||  invalidPassword || mismatchPassword ){

					var textContent = "";
					if(invalidUsername){
						textContent += " invalid_username_label ";
					}
					if(invalidPassword){
						textContent += " invalid_password_label ";
					}

					if(mismatchPassword){
						textContent += " mismatch_password_label ";
					}

					$mdDialog.show(
						      $mdDialog.alert()
						        .parent(angular.element(document.querySelector('#popupContainer')))
						        .clickOutsideToClose(true)
						        .title('Dati non validi')
						        .textContent(textContent)
						        .ariaLabel('Alert Dialog Demo')
						        .ok('OK!')
						        .targetEvent(ev)
						    );
					return;
				}



				if($scope.user.type=='Altro'){
					$scope.user.type=$scope.user.typeOther;
				}


				userService.register($scope.user).then(function(temp) {


//					alert("register OK");
					$mdDialog.show(
						      $mdDialog.alert()
						        .parent(angular.element(document.querySelector('#popupContainer')))
						        .clickOutsideToClose(true)
						        .title('Registrazione avvenuta con successo')
						        .textContent("Per poter utilizzare il tuo account è necessaria l'attivazione da parte del Comune di Marsala")
						        .ariaLabel('Alert Dialog Demo')
						        .ok('OK!')
						        .targetEvent(ev)
						    );



					console.log("SUCCESS");


				}, function(temp) {


					$mdDialog.show(
							   $mdDialog.alert()
						        .parent(angular.element(document.querySelector('#popupContainer')))
						        .clickOutsideToClose(true)
						        .title('Errore durante la registrazione')
						        .textContent("Errore: "+temp.status+". Se non riesci a risolvere contatta il supporto")
						        .ariaLabel('Alert Dialog Demo')
						        .ok('OK!')
						        .targetEvent(ev)
						    );
				});
			};


		} ]);


app.controller('UserListCtrl', [
	'$scope',
	'$location',
	'$localStorage',
	'userService','$mdDialog',
	function($scope, $location, $localStorage,
			userService,$mdDialog) {

		$scope.userSession = $localStorage.userSession;

		if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
			$location.path('/login');
		}


		//GESTIONE TAB START
		$scope.tab='IN_ATTESA';

		$scope.showTab = function(tabId) {
			$scope.tab=tabId;
		};

		$scope.getTabStatus = function() {
			switch($scope.tab){
				case "IN_ATTESA": return 0;
				case "ABILITATI": return 1;
				case "RIFIUTATI": return 2;

			}
		};


		// callback for ng-click 'updateUser':
		$scope.cambiaStato = function(user, newStatus) {

			var userTemp = angular.copy(user);
			userTemp.stato=newStatus;

			userService.updateUser(userTemp).then(function(temp) {


				var index = $scope.users.indexOf(user);
				$scope.users.splice(index, 1);

				$scope.users.push(userTemp);

				$scope.$apply();
//				alert("register OK");
				$mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Utente Modificato con successo')
					        .textContent("Utente Modificato con successo")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(user)
					    );



				console.log("SUCCESS");


			}, function(temp) {


				$mdDialog.show(
						   $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Errore durante la modifica')
					        .textContent("Errore: "+temp.status+". Se non riesci a risolvere contatta il supporto")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(user)
					    );
			});
		};




		// callback for ng-click logout:
		$scope.logout = function(ev) {

			 var confirm = $mdDialog.confirm()
	          .title('Sicuro di voler escire?')
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

		// callback for ng-click 'showUserProfile':
		$scope.showUserProfile = function(user) {
			$location.path('/user-profile/' + user.username);
		};



		userService.getUserList().then(function(temp) {
			$scope.users = temp;
			console.log("SUCCESS");
		}, function() {
			console.log("ERROR");
		});
	} ]);



app.controller('UserCtrl', [
	'$scope',
	'$location',
	'$localStorage',
	'userService',
	'$mdDialog',
	'$routeParams',
	'socialCardService',
	'commonService',
	function($scope, $location, $localStorage,
			userService, $mdDialog, $routeParams, socialCardService, commonService) {

		$scope.userSession = $localStorage.userSession;

		if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
			$location.path('/login');
		}




		//TODO retrieve from server
		  $scope.data = {
				availableOptions: ruoli
		  };

		//GESTIONE TAB START
		$scope.tab='PROFILO';

		$scope.showTab = function(tabId) {
			$scope.tab=tabId;
		};


    $scope.gestisciUtenti = function() {
      $location.path('/user-list');
    };

    // JACOPO : aggiunto metodo
    $scope.mostraItaliani = function() {
      $location.path('/filtri-list');
    }


    // call for ng-click 'updateUser':
		$scope.updateUser = function(ev) {

			var invalidUsername = $scope.user.username.length < 3;
			var invalidPassword = false;
			var mismatchPassword = false;

			if($scope.user.new_password !=undefined && $scope.user.new_password.length>0){
				invalidPassword = $scope.user.new_password.length < 3;
				mismatchPassword =  $scope.user.new_password != $scope.user.new_repeatPassword
			}
			//var invalidPassword = $scope.user.new_password.length < 3;
			//var mismatchPassword =  $scope.user.new_password != $scope.user.new_repeatPassword

			if ( invalidUsername ||  invalidPassword || mismatchPassword ){

				var textContent = "";
				if(invalidUsername){
					textContent += " invalid_username_label ";
				}
				if(invalidPassword){
					textContent += " invalid_password_label ";
				}

				if(mismatchPassword){
					textContent += " mismatch_password_label ";
				}

				$mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Dati non validi')
					        .textContent(textContent)
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(ev)
					    );
				return;
			}
			if($scope.user.new_password !=undefined && $scope.user.new_password.length>0)
				$scope.user.password = $scope.user.new_password;

			userService.updateUser($scope.user).then(function(temp) {


				$mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Utente Modificato con successo')
					        .textContent("Utente Modificato con successo")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(ev)
					    );



				console.log("SUCCESS");


			}, function(temp) {


				$mdDialog.show(
						   $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Errore durante la modifica')
					        .textContent("Errore: "+temp.status+". Se non riesci a risolvere contatta il supporto")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(ev)
					    );
			});
		};

		// call for ng-click 'associa':
		$scope.associa = function(soaialCardId) {
			var BreakException = {};

			userService.associaUser($scope.user.username, soaialCardId).then(function(temp) {

				$scope.elencoCartelleNonAss.forEach(function(entry) {
					    if(entry.id == soaialCardId){
					    	$scope.elencoCartelleAss.push(entry);
					    }
				});

				const index = $scope.elencoCartelleNonAss.findIndex(item => item.id === soaialCardId);

				$scope.elencoCartelleNonAss.splice(index,1);

				$mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Associazione avvenuta con successo')
					        .textContent("Associazione avvenuta con successo")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(soaialCardId)
					    );




				console.log("SUCCESS");


			}, function(temp) {


				$mdDialog.show(
						   $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Errore durante la modifica')
					        .textContent("Errore: "+temp.status+". Se non riesci a risolvere contatta il supporto")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(soaialCardId)
					    );
			});
		};





		// call for ng-click 'deassocia':
		$scope.deassocia = function(soaialCardId) {
			var BreakException = {};

			userService.deassociaUser($scope.user.username, soaialCardId).then(function(temp) {

				$scope.elencoCartelleAss.forEach(function(entry) {
				    if(entry.id == soaialCardId){
				    	$scope.elencoCartelleNonAss.push(entry);
				    }
				});

				const index = $scope.elencoCartelleAss.findIndex(item => item.id === soaialCardId);

				$scope.elencoCartelleAss.splice(index,1);

				$mdDialog.show(
					      $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Modifica avvenuta con successo')
					        .textContent("Modifica avvenuta con successo")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(soaialCardId)
					    );



				console.log("SUCCESS");


			}, function(temp) {


				$mdDialog.show(
						   $mdDialog.alert()
					        .parent(angular.element(document.querySelector('#popupContainer')))
					        .clickOutsideToClose(true)
					        .title('Errore durante la modifica')
					        .textContent("Errore: "+temp.status+". Se non riesci a risolvere contatta il supporto")
					        .ariaLabel('Alert Dialog Demo')
					        .ok('OK!')
					        .targetEvent(soaialCardId)
					    );
			});
		};


		// call for ng-click logout:
		$scope.logout = function(ev) {

			 var confirm = $mdDialog.confirm()
	          .title('Sicuro di voler escire?')
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


		$scope.back = function(ev) {
			 var confirm = $mdDialog.confirm()
	          .title('Sicuro di voler tornare a Elenco Utenti?')
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


		userService.getUser($routeParams.username).then(function(temp) {
			delete temp.password;
			$scope.user = temp;
//			$scope.user.repeatPassword= temp.password;
			console.log("SUCCESS");

			$scope.isInstitutional = function() {
				return commonService.isInstitutional(temp.type);
			};

			$scope.elencoCartelleNonAss = [];
			$scope.elencoCartelleAss = [];

			socialCardService.getSocialCardList().then(function(tempList) {
				tempList.forEach(function(entry) {
				    if($scope.user.elencoCartelleAssociate!=null && $scope.user.elencoCartelleAssociate.includes(entry.id)){
				    	$scope.elencoCartelleAss.push(entry)
				    }else{
				    	$scope.elencoCartelleNonAss.push(entry)
				    }
				});


				console.log("SUCCESS");
			}, function() {
				console.log("ERROR");
			});

		}, function() {
			console.log("ERROR");
		});




	} ]);
