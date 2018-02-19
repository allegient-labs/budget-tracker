'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl',
      requireADLogin: true
  });
}])

.controller('View1Ctrl', ['adalAuthenticationService', '$rootScope', function(adalService, $rootScope) {

  console.log(adalService.userInfo.userName);

    $rootScope.$on("adal:acquireTokenFailure", function (event, errorDesc, error) {
      console.log(errorDesc);
    });
    $rootScope.$on("adal:acquireTokenSuccess", function (event, tokenOut) {
      console.log("success:");
          console.log(event);
    });

}]);