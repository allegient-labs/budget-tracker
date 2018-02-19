'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl',
      requireADLogin: true
  });
}])

.controller('View1Ctrl', ['adalAuthenticationService', '$rootScope', '$http', '$scope', function(adalService, $rootScope, $http, $scope) {
    var self = this;

    $scope.logout = function() {
      console.log("logout button pressed");
      adalService.logOut();
    };

    function init() {
        $http({
            method:'GET',
            url: 'http://localhost:8080/reports/resource-plan/11'
        }).then(function(res) {
            console.log(res);
        }, function(err) {
            console.log(err);
        });
    }
    init();
  console.log(adalService.userInfo.userName);

    $rootScope.$on("adal:acquireTokenFailure", function (event, errorDesc, error) {
      console.log(errorDesc);
    });
    $rootScope.$on("adal:acquireTokenSuccess", function (event, tokenOut) {
      console.log("success:");
          console.log(event);
    });

}]);