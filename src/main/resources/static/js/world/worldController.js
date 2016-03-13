var worldController = function($scope, $window, $location) {
	$scope.toBattle = function(town) {
		$location.path('/battle');
	}
}