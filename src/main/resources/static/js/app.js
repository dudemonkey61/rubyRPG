var dummyModule = angular.module('rubyRPG', ['username', 'ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl: '',
		controller: ''
	}).
	when('/differentThing', {
		templateUrl: '',
		controller: ''
	}).
	otherwise({
		redirectTo: '/'
	})
}])

//https://docs.angularjs.org/tutorial/step_11
//https://docs.angularjs.org/tutorial/step_07
//https://spring.io/blog/2015/08/19/migrating-a-spring-web-mvc-application-from-jsp-to-angularjs
//https://samerabdelkafi.wordpress.com/2015/01/25/angularjs/