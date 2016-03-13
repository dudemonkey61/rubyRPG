var dummyModule = angular.module('rubyRPG', ['ngRoute', 'world', 'login', 'register', 'town', 'battle',])

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
	.when('/town', {
		templateUrl: 'townPage',
		controller: 'townController'
	})
	.when('/battle', {
		templateUrl: 'battlePage',
		controller: 'battleController'
	})
	.otherwise({
		redirectTo: '/login'
	})
}])