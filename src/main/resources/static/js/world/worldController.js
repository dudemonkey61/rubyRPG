var worldController = function($scope, $window, $location) {
	$scope.toTown = function() {
		$location.path('/town');
	}
	
	$scope.toBattle = function() {
		$location.path('/battle');
	}
}