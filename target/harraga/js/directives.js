'use strict';

/* Directives */

angular.module('harragapp.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]).directive("fileread", [function () {
	    return {
	        scope: {
	            fileread: "="
	        },
	        link: function (scope, element, attributes) {
	            element.bind("change", function (changeEvent) {
	                var reader = new FileReader();
	                reader.onload = function (loadEvent) {
	                    scope.$apply(function () {
	                    	var oldId = null;
	                    	if(scope.fileread != undefined)
	                    		oldId = scope.fileread.id
//	                        scope.fileread = loadEvent.target.result;
	                        scope.fileread = {
	                                lastModified: changeEvent.target.files[0].lastModified,
	                                lastModifiedDate: changeEvent.target.files[0].lastModifiedDate,
	                                nome: changeEvent.target.files[0].name,
	                                dimensione: changeEvent.target.files[0].size,
	                                tipo: changeEvent.target.files[0].type,
	                                encodedFile: loadEvent.target.result,
	                                isNew: true,
	                                id : oldId
	                            };
	                    });
	                }
	                reader.readAsDataURL(changeEvent.target.files[0]);
	            });
	        }
	    }
	}]).directive('fileReader', function() {
		  return {
			    scope: {
			      fileReader:"="
			    },
			    link: function(scope, element) {
			      $(element).on('change', function(changeEvent) {
			        var files = changeEvent.target.files;
			        if (files.length) {
			          var r = new FileReader();
			          r.onload = function(e) {
			              var contents = e.target.result;
			              scope.$apply(function () {
			                scope.fileReader = contents;
			              });
			          };

			          r.readAsText(files[0]);
			        }
			      });
			    }
			  };
			});;
			
			
