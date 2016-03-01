var dummyModule = angular.module('rubyRPG', ['ngRoute', 'worldPage', 'login', 'register'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/world', {
		templateUrl: 'worldPage',
		controller: 'worldController'
	})
	.when('/login', {
		templateUrl: 'loginPage',
		controller: 'loginController'
	})
	.when('/register', {
		templateUrl: 'registerPage',
		controller: 'registerController'
	})
	.otherwise({
		redirectTo: '/login'
	})
}])