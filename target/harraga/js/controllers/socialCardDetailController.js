'use strict';

/* Controllers */

var app = angular.module('harragapp.socialCardDetailController', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
// app.run(function($rootScope, $templateCache) {
// $rootScope.$on('$viewContentLoaded', function() {
// $templateCache.removeAll();
// });
// });

app.controller('SocialCardDetailCtrl', [
		'$scope',
		'$routeParams',
		'$location',
		'socialCardService',
		'socialCardHistoryService',
		'commonService',
		'$localStorage',
		'$mdDialog',
		function($scope, $routeParams, $location, socialCardService,
				socialCardHistoryService, commonService, $localStorage,
				$mdDialog) {
			$scope.userSession = $localStorage.userSession;

			if ($scope.userSession == undefined
					|| Object.keys($scope.userSession).length === 0) {
				$location.path('/login');
			}

			$scope.socialCard = {};
			$scope.socialCard.id = $routeParams.id;

			// GESTIONE TAB START

			$scope.showTab = function(tabId) {

				switch (tabId) {
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
					// text = "I have never heard of that fruit...";

				}
				$scope.tab = tabId;
			};
			
			
			// INIZIO ANAGRAFICA
			function getAnagrafica() {
				console.log("Sono in anagrafica!");
				if ($scope.socialCard.anagrafica == undefined) {
					$scope.loading = true;
					socialCardService.getAnagrafica($routeParams.id).then(
							function(temp) {
								$scope.socialCard.anagraficaCurrent = temp; // SALVO ANAGRAFICA CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.anagrafica = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getAnagraficaHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.anagraficaHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.anagrafica = $scope.socialCard.anagraficaCurrent;					
				}
			}
			
			$scope.showAnagraficaCurrent = function(historyItem) {
				$scope.anaHistInfo = null;
				$scope.socialCard.anagrafica = $scope.socialCard.anagraficaCurrent;
			}
			
			$scope.getAnagraficaHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getAnagrafica($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.anaHistInfo = historyItem;
					
					$scope.socialCard.anagrafica = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};
			
			//FINE ANAGRAFICA	
//			INIZIO AMMINISTRATIVA

			function getAmministrativa() {
				if ($scope.socialCard.amministrativa == undefined) {
					$scope.loading = true;
					socialCardService.getAmministrativa($routeParams.id).then(
							function(temp) {
								$scope.socialCard.amministrativaCurrent = temp; // SALVO amministrativa CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.amministrativa = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getAmministrativaHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.amministrativaHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.amministrativa = $scope.socialCard.amministrativaCurrent;					
				}
			}
			
			
			$scope.showAmministrativaCurrent = function(historyItem) {
				$scope.ammHistInfo = null;
				$scope.socialCard.amministrativa = $scope.socialCard.amministrativaCurrent;
			}
			
			$scope.getAmministrativaHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getAmministrativa($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.ammHistInfo = historyItem;
					
					$scope.socialCard.amministrativa = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};
			
			
//			FINE AMMINISTRATIVA
// 			INIZIO STORIA
			function getStoria() {
				if ($scope.socialCard.storia == undefined) {
					$scope.loading = true;
					socialCardService.getStoria($routeParams.id).then(
							function(temp) {
								$scope.socialCard.storiaCurrent = temp; // SALVO storia CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.storia = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getStoriaHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.storiaHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.storia = $scope.socialCard.storiaCurrent;					
				}
			}
			
			
			$scope.showStoriaCurrent = function(historyItem) {
				$scope.stoHistInfo = null;
				$scope.socialCard.storia = $scope.socialCard.storiaCurrent;
			}
			
			$scope.getStoriaHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getStoria($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.stoHistInfo = historyItem;
					
					$scope.socialCard.storia = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};
//			FINE STORIA

// 			INIZIO SANITARIA
			function getSanitaria() {
				if ($scope.socialCard.sanitaria == undefined) {
					$scope.loading = true;
					socialCardService.getSanitaria($routeParams.id).then(
							function(temp) {
								$scope.socialCard.sanitariaCurrent = temp; // SALVO sanitaria CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.sanitaria = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getSanitariaHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.sanitariaHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.sanitaria = $scope.socialCard.sanitariaCurrent;					
				}
			}
			
			
			$scope.showSanitariaCurrent = function(historyItem) {
				$scope.sanHistInfo = null;
				$scope.socialCard.sanitaria = $scope.socialCard.sanitariaCurrent;
			}
			
			$scope.getSanitariaHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getSanitaria($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.sanHistInfo = historyItem;
					$scope.socialCard.sanitaria = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});
			}
//			FINE SANITARIA
// 			INIZIO ISTRUZIONE
			function getIstruzione() {
				if ($scope.socialCard.istruzione == undefined) {
					$scope.loading = true;
					socialCardService.getIstruzione($routeParams.id).then(
							function(temp) {
								$scope.socialCard.istruzioneCurrent = temp; // SALVO istruzione CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.istruzione = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getIstruzioneHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.istruzioneHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.istruzione = $scope.socialCard.istruzioneCurrent;					
				}
			}
			
			
			$scope.showIstruzioneCurrent = function(historyItem) {
				$scope.istHistInfo = null;
				$scope.socialCard.istruzione = $scope.socialCard.istruzioneCurrent;
			}
			
			$scope.getIstruzioneHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getIstruzione($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.istHistInfo = historyItem;
					
					$scope.socialCard.istruzione = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			}
			
//			FINE ISTRUZIONE
// 			INIZIO LAVORO
			function getLavoro() {
				if ($scope.socialCard.lavoro == undefined) {
					$scope.loading = true;
					socialCardService.getLavoro($routeParams.id).then(
							function(temp) {
								$scope.socialCard.lavoroCurrent = temp; // SALVO lavoro CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.lavoro = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getLavoroHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.lavoroHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.lavoro = $scope.socialCard.lavoroCurrent;					
				}
			}
			
			
			$scope.showLavoroCurrent = function(historyItem) {
				$scope.lavHistInfo = null;
				$scope.socialCard.lavoro = $scope.socialCard.lavoroCurrent;
			}
			
			$scope.getLavoroHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getLavoro($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.lavHistInfo = historyItem;
					
					$scope.socialCard.lavoro = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			}
//			FINE LAVORO
//			INIZIO COMPETENZE
			function getCompetenze() {
				if ($scope.socialCard.competenze == undefined) {
					$scope.loading = true;
					socialCardService.getCompetenze($routeParams.id).then(
							function(temp) {
								$scope.socialCard.competenzeCurrent = temp; // SALVO competenze CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.competenze = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getCompetenzeHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.competenzeHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.competenze = $scope.socialCard.competenzeCurrent;					
				}
			}
			
			$scope.showCompetenzeCurrent = function(historyItem) {
				$scope.comHistInfo = null;
				$scope.socialCard.competenze = $scope.socialCard.competenzeCurrent;
			}
			
			$scope.getCompetenzeHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getCompetenze($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.comHistInfo = historyItem;
					
					$scope.socialCard.competenze = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};

			
//			FINE COMPETENZE
			
			
//			INIZIO SOCIALE
			function getSociale() {
				if ($scope.socialCard.sociale == undefined) {
					$scope.loading = true;
					socialCardService.getSociale($routeParams.id).then(
							function(temp) {
								$scope.socialCard.socialeCurrent = temp; // SALVO sociale CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.sociale = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getSocialeHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.socialeHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.sociale = $scope.socialCard.socialeCurrent;					
				}
			}
			
			$scope.showSocialeCurrent = function(historyItem) {
				$scope.socHistInfo = null;
				$scope.socialCard.sociale = $scope.socialCard.socialeCurrent;
			}
			
			$scope.getSocialeHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getSociale($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.socHistInfo = historyItem;					
					$scope.socialCard.sociale = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};
			//	FINE SOCIALE
			
			
//			INIZIO PENALE
			
			function getPenale() {
				if ($scope.socialCard.penale == undefined) {
					$scope.loading = true;
					socialCardService.getPenale($routeParams.id).then(
							function(temp) {
								$scope.socialCard.penaleCurrent = temp; // SALVO penale CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.penale = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
					.getPenaleHistory($routeParams.id).then(
							function(temp) {
								$scope.socialCard.penaleHist = temp;
								console.log("SUCCESS");
							}, function() {
								console.log("ERROR");
							});
				}else{					
					$scope.socialCard.penale = $scope.socialCard.penaleCurrent;					
				}
			}
			
			
			$scope.showPenaleCurrent = function(historyItem) {
				$scope.penHistInfo = null;
				$scope.socialCard.penale = $scope.socialCard.penaleCurrent;
			}
			
			$scope.getPenaleHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getPenale($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.penHistInfo = historyItem;
					
					$scope.socialCard.penale = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			}
//			FINE PENALE
			
//			INIZIO DESIDERI
			function getDesideri() {
				if ($scope.socialCard.desideri == undefined) {
					$scope.loading = true;
					socialCardService.getDesideri($routeParams.id).then(
							function(temp) {
								$scope.socialCard.desideriCurrent = temp; // SALVO I DESIDERI CURRENT PER NON RECUPERARLI SEMPRE
								$scope.socialCard.desideri = temp;
								console.log("SUCCESS");
								$scope.loading = false;
							}, function() {
								console.log("ERROR");
								$scope.loading = false;
							});
					socialCardHistoryService
							.getDesideriHistory($routeParams.id).then(
									function(temp) {
										$scope.socialCard.desideriHist = temp;
										console.log("SUCCESS");
									}, function() {
										console.log("ERROR");
									});
				}else{					
					$scope.socialCard.desideri = $scope.socialCard.desideriCurrent;					
				}
			}
			$scope.showDesideriCurrent = function(historyItem) {
				$scope.desHistInfo = null;
				$scope.socialCard.desideri = $scope.socialCard.desideriCurrent;
			}
			
			$scope.getDesideriHistory = function(historyItem) {
				$scope.loading = true;
				socialCardService.getDesideri($routeParams.id,
						historyItem.timestamp).then(function(temp) {
					$scope.desHistInfo = historyItem;
					
					$scope.socialCard.desideri = temp;
					console.log("SUCCESS");
					$scope.loading = false;
				}, function() {
					console.log("ERROR");
					$scope.loading = false;
				});

			};
			
//			FINE  DESIDERI
			
			
			$scope.tab = commonService.getFirstReadSection();
			$scope.showTab($scope.tab);

			// FINE GESTIONE TAB

			$scope.canRead = function(section, subSection) {
				return commonService.canRead(section, subSection);
			};

			$scope.canEdit = function() {
				return commonService.canEdit();
			};

			// callback for ng-click 'editSocialCard':
			$scope.editSocialCard = function(socialCardId) {
				$location.path('/social-card-edit/' + socialCardId);
			};
			// callback for ng-click
			$scope.back = function() {
				$location.path('/social-card-list');
			};

			// callback for ng-click show file:
			$scope.getFile = function(fileName, link) {

				var fileExt = link.split('.').pop();
				var reqType = "application/" + fileExt;

				socialCardService.getFile(link, $scope.socialCard.id).then(
						function(temp) {
							$scope.img = temp;
							// var file = new Blob([temp], {type:
							// 'application/pdf'});
							// var fileURL = URL.createObjectURL(file);
							// window.open(fileURL);
							var file = new Blob([ temp ], {
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
							console.log("SUCCESS");
						}, function() {
							console.log("ERROR");
						});
			};
			
			// callback for ng-click downloadFolder
			$scope.downloadFolder = function(section) {

				var reqType = "application/zip";

				socialCardService.downloadFolder(section, $scope.socialCard.id).then(
						function(temp) {
							
							var file = new Blob([ temp ], {
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
							a.download = section+".zip";// + "."+fileExt;
							a.click();
							console.log("SUCCESS");
						}, function() {
							console.log("ERROR");
						});
			};

			
			// callback for ng-click export json
			$scope.exportJson = function(jsonObject, fileName) {

//				var fileName = "test.json";
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

		} ]);
