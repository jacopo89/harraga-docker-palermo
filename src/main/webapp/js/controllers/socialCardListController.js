'use strict';

/* Controllers */

var app = angular.module('harragapp.socialCardListController', []);



app.controller('SocialCardListCtrl', [
		'$scope',
		'$location',
		'$localStorage',
		'socialCardService',
		'commonService',
		'$mdDialog',
		function($scope, $location, $localStorage,
				socialCardService, commonService, $mdDialog) {
			$scope.currentPage = 0;
		    $scope.pageSize = 15;

		    $scope.updateCurrentPage = function(){
		    	console.log("updatecurrentpage");
		    	$scope.currentPage=0;
		    }


			$scope.userSession = $localStorage.userSession;

			if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
				$location.path('/login');
			}

			$scope.canWrite = function(section, subSetcion) {
				return commonService.canWrite(section, subSetcion);
			};

			$scope.canCreate = function() {
				return commonService.canCreate();
			};

			// callback for ng-click 'editSocialCard':
			$scope.editSocialCard = function(socialCardId) {
				$location.path('/social-card-edit/' + socialCardId);
			};

			// callback for ng-click 'showSocialCard':
			$scope.detailSocialCard = function(socialCardId) {
				$location.path('/social-card-detail/' + socialCardId);
			};

			// callback for ng-click 'deleteUser':
			$scope.deleteSocialCard = function(socialCardId) {

				var temp_id = socialCardId;
					 var confirm = $mdDialog.confirm()
			          .title('Sicuro di voler Eliminare la Cartella Sociale?')
			          .textContent('Questa operazione non potrà più essere annullata')
			          .ariaLabel('Lucky day')
			          .targetEvent(socialCardId)
			          .ok('Conferma')
			          .cancel('Annulla');

				    $mdDialog.show(confirm).then(function() {
				    	console.log("delete social card confirmed");
				    	socialCardService.deleteSocialCard(temp_id).then(function(temp) {

							$scope.socialCards = $scope.socialCards.filter(function( obj ) {
							    return obj.id !== temp_id;
							});

							console.log("SUCCESS");
						}, function() {
							console.log("ERROR");
						});

				    }, function() {
				    	console.log("delete social card cancel");
				    });

			};

			// callback for ng-click 'createUser':
			$scope.createNewSocialCard = function() {
				$location.path('/social-card-creation');
			};

			// callback for ng-click 'createUser':
			$scope.gestisciUtenti = function() {
				$location.path('/user-list');
			};

			// JACOPO : aggiunto metodo
			$scope.mostraItaliani = function() {
				$location.path('/filtri-list');
			}

			$scope.mostraMaggiorenni = function() {
				$location.path('/maggiorenni-list');
			}


			// callback for ng-click profilo:
			$scope.profilo = function(username) {
				$location.path('/user-profile/'+username);
			}
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


			// callback for ng-click export json
			$scope.exportJson = function(jsonObject) {

				var fileName = "socialCardList.json";
				var jsonText = JSON.stringify(jsonObject);

				var reqType = "application/json";
				var file = new Blob([ jsonText ], {
					type : reqType
				});
				var fileURL = window.URL.createObjectURL(file);
				var seconds = new Date().getTime() / 1000;
				// var fileName = "cert" + parseInt(seconds) +
				// ".pdf";
				var a = document.createElement("a");
				document.body.appendChild(a);
				a.style = "display: none";
				a.href = fileURL;
				a.download = fileName;// + "."+fileExt;
				a.click();
			};

			socialCardService.getSocialCardList().then(function(temp) {
				$scope.socialCards = temp;
				$scope.numeroCards = temp.length;
				console.log("SUCCESS");
			}, function() {
				console.log("ERROR");
			});


			$scope.importSocialCard = function(ev) {
				$location.path('/social-card-import');
			}

			$scope.sortRule = "anagrafica.cognome";
			$scope.sortReverse = false;
			$scope.sortBy = function(x){
				if(x==$scope.sortRule){
					$scope.sortReverse = !($scope.sortReverse);
				}else{
				$scope.sortRule = x;
				$scope.sortReverse = false;
				}
			}

			$scope.searchInput ="";
			$scope.searchCriteria="nome";
			$scope.myFilter = function(item){
				var numeroSchede = $scope.numeroSchede;
				var criterio = $scope.searchCriteria;
				switch(criterio){
				case "nome":
					if(item.anagrafica.nome.includes($scope.searchInput)){
						numeroSchede = numeroSchede +1;
						return item;
					}
					break;
				case "cognome":
					if(item.anagrafica.cognome.includes($scope.searchInput)){
						return item;
						numeroSchede = numeroSchede +1;
					}
					break;
				}

			}


			/*$scope.importSocialCard = function(ev) {
			    $mdDialog.show({
			      controller: DialogController,
			      templateUrl: 'partials/widget/importDialog.html',
			      parent: angular.element(document.body),
			      targetEvent: ev,
			      clickOutsideToClose:true,
			      bindToController: true,
			      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
			    })
			    .then(function(answer) {
			      $scope.status = 'You said the information was "' + answer + '".';
			    }, function() {
			      $scope.status = 'You cancelled the dialog.';
			    });
			  };






			function DialogController($scope, $mdDialog) {
			    $scope.hide = function() {
			      $mdDialog.hide();
			    };

			    $scope.cancel = function() {
			      $mdDialog.cancel();
			    };

			    $scope.answer = function(answer) {
			      $mdDialog.hide(answer);
			    };
			  }
			  */
		}
]);

// JACOPO : CREAZIONE CONTROLLER PER PAGINA ITALIANI

app.controller('ItalianiListCtrl', [
                              		'$scope',
                              		'$location',
                              		'$localStorage',
                              		'socialCardService',
                              		'commonService',
                              		'$mdDialog',
                              		function($scope, $location, $localStorage,
                              				socialCardService, commonService, $mdDialog) {


                              			$scope.userSession = $localStorage.userSession;

                              			$scope.currentPage = 0;
                            		    $scope.pageSize = 15;

                              			if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
                              				$location.path('/login');
                              			}

                              			$scope.updateCurrentPage = function(){
                            		    	console.log("updatecurrentpage");
                            		    	$scope.currentPage=0;
                            		    }

                              			$scope.canWrite = function(section, subSetcion) {
                              				return commonService.canWrite(section, subSetcion);
                              			};

                              			$scope.canCreate = function() {
                              				return commonService.canCreate();
                              			};

                              			// callback for ng-click 'editSocialCard':
                              			$scope.editSocialCard = function(socialCardId) {
                              				$location.path('/social-card-edit/' + socialCardId);
                              			};

                              			// callback for ng-click 'showSocialCard':
                              			$scope.detailSocialCard = function(socialCardId) {
                              				$location.path('/social-card-detail/' + socialCardId);
                              			};

                              			// callback for ng-click 'deleteUser':
                              			$scope.deleteSocialCard = function(socialCardId) {

                              				var temp_id = socialCardId;
                              					 var confirm = $mdDialog.confirm()
                              			          .title('Sicuro di voler Eliminare la Cartella Sociale?')
                              			          .textContent('Questa operazione non potrà più essere annullata')
                              			          .ariaLabel('Lucky day')
                              			          .targetEvent(socialCardId)
                              			          .ok('Conferma')
                              			          .cancel('Annulla');

                              				    $mdDialog.show(confirm).then(function() {
                              				    	console.log("delete social card confirmed");
                              				    	socialCardService.deleteSocialCard(temp_id).then(function(temp) {

                              							$scope.socialCards = $scope.socialCards.filter(function( obj ) {
                              							    return obj.id !== temp_id;
                              							});

                              							console.log("SUCCESS");
                              						}, function() {
                              							console.log("ERROR");
                              						});

                              				    }, function() {
                              				    	console.log("delete social card cancel");
                              				    });

                              			};

                              			// callback for ng-click 'createUser':
                              			$scope.createNewSocialCard = function() {
                              				$location.path('/social-card-creation');
                              			};

                              			// callback for ng-click 'createUser':
                              			$scope.gestisciUtenti = function() {
                              				$location.path('/user-list');
                              			};

                              			// JACOPO : aggiunto metodo
                              			$scope.mostraItaliani = function() {
                              				$location.path('/filtri-list');
                              			}

                              			$scope.moveToSocialCardList = function(){
                            				$location.path('/social-card-list');
                            			}

                              			// callback for ng-click profilo:
                              			$scope.profilo = function(username) {
                              				$location.path('/user-profile/'+username);
                              			}
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


                              			// callback for ng-click export json
                              			$scope.exportJson = function(jsonObject) {

                              				var fileName = "socialCardList.json";
                              				var jsonText = JSON.stringify(jsonObject);

                              				var reqType = "application/json";
                              				var file = new Blob([ jsonText ], {
                              					type : reqType
                              				});
                              				var fileURL = window.URL.createObjectURL(file);
                              				var seconds = new Date().getTime() / 1000;
                              				// var fileName = "cert" + parseInt(seconds) +
                              				// ".pdf";
                              				var a = document.createElement("a");
                              				document.body.appendChild(a);
                              				a.style = "display: none";
                              				a.href = fileURL;
                              				a.download = fileName;// + "."+fileExt;
                              				a.click();
                              			};
                              			// JACOPO : cambiata la chiamata REST
                              			socialCardService.getItalianiList().then(function(temp) {
                              				$scope.socialCards = temp;
                              				console.log("SUCCESS");
                              			}, function() {
                              				console.log("ERROR");
                              			});


                              			$scope.importSocialCard = function(ev) {
                              				$location.path('/social-card-import');
                              			}

                              			$scope.searchInput ="";
                            			$scope.searchCriteria="nome";
                            			$scope.myFilter = function(item){

                            				var criterio = $scope.searchCriteria;
                            				switch(criterio){
                            				case "nome":
                            					if(item.anagrafica.nome.includes($scope.searchInput)){



                            						return item;
                            					}
                            					break;
                            				case "cognome":
                            					if(item.anagrafica.cognome.includes($scope.searchInput)){


                            						return item;
                            					}
                            					break;
                            				}

                            			}

                              			/*$scope.importSocialCard = function(ev) {
                              			    $mdDialog.show({
                              			      controller: DialogController,
                              			      templateUrl: 'partials/widget/importDialog.html',
                              			      parent: angular.element(document.body),
                              			      targetEvent: ev,
                              			      clickOutsideToClose:true,
                              			      bindToController: true,
                              			      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                              			    })
                              			    .then(function(answer) {
                              			      $scope.status = 'You said the information was "' + answer + '".';
                              			    }, function() {
                              			      $scope.status = 'You cancelled the dialog.';
                              			    });
                              			  };






                              			function DialogController($scope, $mdDialog) {
                              			    $scope.hide = function() {
                              			      $mdDialog.hide();
                              			    };

                              			    $scope.cancel = function() {
                              			      $mdDialog.cancel();
                              			    };

                              			    $scope.answer = function(answer) {
                              			      $mdDialog.hide(answer);
                              			    };
                              			  }
                              			  */
                              		}
                              ]);
app.controller('MaggiorenniListCtrl', [
                              		'$scope',
                              		'$location',
                              		'$localStorage',
                              		'socialCardService',
                              		'commonService',
                              		'$mdDialog',
                              		function($scope, $location, $localStorage,
                              				socialCardService, commonService, $mdDialog) {

                              			$scope.userSession = $localStorage.userSession;

                              			if ($scope.userSession==undefined || Object.keys($scope.userSession).length === 0) {
                              				$location.path('/login');
                              			}

                              			$scope.currentPage = 0;
                            		    $scope.pageSize = 15;

                              			$scope.canWrite = function(section, subSetcion) {
                              				return commonService.canWrite(section, subSetcion);
                              			};

                              			$scope.updateCurrentPage = function(){
                            		    	console.log("updatecurrentpage");
                            		    	$scope.currentPage=0;
                            		    }

                              			$scope.canCreate = function() {
                              				return commonService.canCreate();
                              			};

                              			// callback for ng-click 'editSocialCard':
                              			$scope.editSocialCard = function(socialCardId) {
                              				$location.path('/social-card-edit/' + socialCardId);
                              			};

                              			// callback for ng-click 'showSocialCard':
                              			$scope.detailSocialCard = function(socialCardId) {
                              				$location.path('/social-card-detail/' + socialCardId);
                              			};

                              			// callback for ng-click 'deleteUser':
                              			$scope.deleteSocialCard = function(socialCardId) {

                              				var temp_id = socialCardId;
                              					 var confirm = $mdDialog.confirm()
                              			          .title('Sicuro di voler Eliminare la Cartella Sociale?')
                              			          .textContent('Questa operazione non potrà più essere annullata')
                              			          .ariaLabel('Lucky day')
                              			          .targetEvent(socialCardId)
                              			          .ok('Conferma')
                              			          .cancel('Annulla');

                              				    $mdDialog.show(confirm).then(function() {
                              				    	console.log("delete social card confirmed");
                              				    	socialCardService.deleteSocialCard(temp_id).then(function(temp) {

                              							$scope.socialCards = $scope.socialCards.filter(function( obj ) {
                              							    return obj.id !== temp_id;
                              							});

                              							console.log("SUCCESS");
                              						}, function() {
                              							console.log("ERROR");
                              						});

                              				    }, function() {
                              				    	console.log("delete social card cancel");
                              				    });

                              			};

                              			// callback for ng-click 'createUser':
                              			$scope.createNewSocialCard = function() {
                              				$location.path('/social-card-creation');
                              			};

                              			// callback for ng-click 'createUser':
                              			$scope.gestisciUtenti = function() {
                              				$location.path('/user-list');
                              			};

                              			// JACOPO : aggiunto metodo
                              			$scope.mostraItaliani = function() {
                              				$location.path('/filtri-list');
                              			}

                              			$scope.moveToSocialCardList = function(){
                            				$location.path('/social-card-list');
                            			}

                              			// callback for ng-click profilo:
                              			$scope.profilo = function(username) {
                              				$location.path('/user-profile/'+username);
                              			}
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


                              			// callback for ng-click export json
                              			$scope.exportJson = function(jsonObject) {

                              				var fileName = "socialCardList.json";
                              				var jsonText = JSON.stringify(jsonObject);

                              				var reqType = "application/json";
                              				var file = new Blob([ jsonText ], {
                              					type : reqType
                              				});
                              				var fileURL = window.URL.createObjectURL(file);
                              				var seconds = new Date().getTime() / 1000;
                              				// var fileName = "cert" + parseInt(seconds) +
                              				// ".pdf";
                              				var a = document.createElement("a");
                              				document.body.appendChild(a);
                              				a.style = "display: none";
                              				a.href = fileURL;
                              				a.download = fileName;// + "."+fileExt;
                              				a.click();
                              			};
                              			// JACOPO : cambiata la chiamata REST per i maggiorenni
                              			socialCardService.getMaggiorenniList().then(function(temp) {
                              				$scope.socialCards = temp;
                              				console.log("SUCCESS");
                              			}, function() {
                              				console.log("ERROR");
                              			});


                              			$scope.importSocialCard = function(ev) {
                              				$location.path('/social-card-import');
                              			}

                              			$scope.searchInput ="";
                            			$scope.searchCriteria="nome";
                            			$scope.myFilter = function(item){

                            				var criterio = $scope.searchCriteria;
                            				switch(criterio){
                            				case "nome":
                            					if(item.anagrafica.nome.includes($scope.searchInput)){



                            						return item;
                            					}
                            					break;
                            				case "cognome":
                            					if(item.anagrafica.cognome.includes($scope.searchInput)){


                            						return item;
                            					}
                            					break;
                            				}

                            			}

                              			/*$scope.importSocialCard = function(ev) {
                              			    $mdDialog.show({
                              			      controller: DialogController,
                              			      templateUrl: 'partials/widget/importDialog.html',
                              			      parent: angular.element(document.body),
                              			      targetEvent: ev,
                              			      clickOutsideToClose:true,
                              			      bindToController: true,
                              			      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                              			    })
                              			    .then(function(answer) {
                              			      $scope.status = 'You said the information was "' + answer + '".';
                              			    }, function() {
                              			      $scope.status = 'You cancelled the dialog.';
                              			    });
                              			  };






                              			function DialogController($scope, $mdDialog) {
                              			    $scope.hide = function() {
                              			      $mdDialog.hide();
                              			    };

                              			    $scope.cancel = function() {
                              			      $mdDialog.cancel();
                              			    };

                              			    $scope.answer = function(answer) {
                              			      $mdDialog.hide(answer);
                              			    };
                              			  }
                              			  */
                              		}
                              ]);
