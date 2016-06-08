var myApp = angular.module('xmlApp',['ngRoute', 'ngStorage', 'ui.bootstrap','angular-jwt']);
myApp.controller('appCtrl', require('./appController.js'));
myApp.controller('mainCtrl', require('./mainController.js'));
myApp.controller('loginCtrl', require('./loginController.js'));
myApp.controller('usvojeniAktiCtrl', require('./usvojeniAktiController.js'));
myApp.controller('aktiUProceduriCtrl', require('./aktiUProceduriController.js'));
myApp.service('loginService', require('./loginService.js'));

myApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/main', {
        templateUrl: '/angular/dist/templates/main.html',
        controller: 'mainCtrl'
      }).
	when('/usvojeni-akti', {
		templateUrl: '/angular/dist/templates/usvojeni-akti.html',
		controller: 'usvojeniAktiCtrl'
	}).
	when('/akti-u-proceduri', {
		templateUrl: '/angular/dist/templates/akti-u-proceduri.html',
		controller: 'aktiUProceduriCtrl'
	}).
	when('/kreiraj-akt', {
		templateUrl: '/angular/dist/templates/kreiraj-akt.html',
		controller: 'aktiUProceduriCtrl'
	}).
	when('/login', {
		templateUrl: '/angular/dist/templates/login.html',
		controller: 'loginCtrl'
	}).
      otherwise({
      	redirectTo: '/main'
      });

}]);


