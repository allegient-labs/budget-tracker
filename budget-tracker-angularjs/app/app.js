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
            tenant: '40d2219d-b636-4bed-9185-e876d026d77b',
            clientId: '295103ad-e41c-45a3-a14b-9dc7ddfbf2b1',
            postLogoutRedirectUri: 'http://localhost:8000',
            endpoints: {
                "http://localhost:8080/": '295103ad-e41c-45a3-a14b-9dc7ddfbf2b1'
            }
        },
        $httpProvider
    );
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);
