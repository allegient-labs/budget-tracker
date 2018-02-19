'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.version',
    'AdalAngular'
]).
config(['$locationProvider', '$routeProvider', '$httpProvider', 'adalAuthenticationServiceProvider', function($locationProvider, $routeProvider, $httpProvider, adalProvider) {
    $locationProvider.html5Mode(false).hashPrefix('');
    adalProvider.init(
        {
            instance: 'https://login.microsoftonline.com/',
            tenant: '9cdd47ef-e773-44c1-8c21-0ec4bc673f75',
            clientId: 'b4135ffe-00e1-4324-b1ad-72edb3362727',
            postLogoutRedirectUri: 'http://localhost:8000',
            endpoints: {
                "http://localhost:8080/": 'b4135ffe-00e1-4324-b1ad-72edb3362727'
            }
        },
        $httpProvider
    );
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);
