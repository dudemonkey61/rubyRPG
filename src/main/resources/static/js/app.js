var dummyModule = angular.module('rubyRPG', ['ngRoute', 'worldPage'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl: 'worldPage',
		controller: 'worldController'
	}).
	otherwise({
		redirectTo: '/'
	})
}])