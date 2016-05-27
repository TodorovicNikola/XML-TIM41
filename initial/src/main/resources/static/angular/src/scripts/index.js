var myApp = angular.module('xmlApp',['ngRoute', 'ngStorage', 'ui.bootstrap','angular-jwt']);
myApp.controller('appCtrl', require('./appController.js'));
myApp.controller('mainCtrl', require('./mainController.js'));
myApp.controller('loginCtrl', require('./loginController.js'));
myApp.controller('aktiCtrl', require('./aktiController.js'));
myApp.service('loginService', require('./loginService.js'));

myApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/main', {
        templateUrl: '/angular/dist/templates/main.html',
        controller: 'mainCtrl'
      }).
	  when('/akti', {
		templateUrl: '/angular/dist/templates/akti.html',
		controller: 'aktiCtrl'
	}).
	  when('/login', {
		templateUrl: '/angular/dist/templates/login.html',
		controller: 'loginCtrl'
	}).
      otherwise({
      	redirectTo: '/main'
      });

}]);


