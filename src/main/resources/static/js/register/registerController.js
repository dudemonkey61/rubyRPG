var registerController = function($scope, $http, $location) {
	$scope.sendRegister = function() {
		console.log("Sending register");
		var data = {
			userName: $scope.userName,
			email: $scope.email,
			password: $scope.password
		}
		var config = {}
		$http.post('/register', data, config)
		.success(function (data, status, headers, config) {
			$location.path('/');
		})
		.error(function (data, status, header, config) {
			console.error(data);
			console.error(status);
			console.error(headers);
		})
	}
	
	$scope.toLogin = function() {
		$location.path('/');
	}
}