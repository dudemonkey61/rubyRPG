var worldController = function($scope, $window) {
	var canvas = document.getElementById("theCanvas");
	var context = canvas.getContext('2d');
	
	var initialResize = function() {
		$scope.width = $window.innerWidth;
		$scope.height = $window.innerHeight;
	}
	initialResize();
	
	var resizeScreen = function() {
		$scope.width = $window.innerWidth;
		$scope.height = $window.innerHeight;
		$scope.$digest();
	}
	angular.element($window).bind('resize', resizeScreen);
}