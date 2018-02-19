'use strict';

angular.module('myApp.view2', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view2', {
           template: "<p>Authenticated</p>",
            // templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl',
            requireADLogin: true
        });
    }])

.controller('View2Ctrl', ['adalAuthenticationService', '$scope', '$rootScope', function(adalService, $scope, $rootScope) {

    console.log(adalService.userInfo.username);

  $scope.logout = function() {
    console.log("Logging out");
    adalService.logOut();
  };

    $rootScope.$on("adal:acquireTokenFailure", function (event, errorDesc, error) {
        console.log(errorDesc);
    });
    $rootScope.$on("adal:acquireTokenSuccess", function (event, tokenOut) {
        console.log("success:");
        console.log(event);
    });
}]);