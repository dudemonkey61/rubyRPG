var worldController = function($scope, $window, $location, userData) {
	$scope.toBattle = function(town) {
		userData.town = town;
		$location.path('/battle');
	}
}