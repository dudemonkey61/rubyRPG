var userLossController = function($scope, $location, $uibModalInstance) {
	$scope.toTown = function() {
		$uibModalInstance.dismiss('cancel');
		$location.path('/town');
	}
	$scope.toWorld = function() {
		$uibModalInstance.dismiss('cancel');
		$location.path('/world')
	}
}