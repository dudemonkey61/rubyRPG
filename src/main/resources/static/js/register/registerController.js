var registerController = function($scope, $http, $location) {
	console.log("In register controller");
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
			$location.path('/login');
		})
		.error(function (data, status, header, config) {
			console.error(data);
			console.error(status);
			console.error(headers);
		})
	}
	
	$scope.toLogin = function() {
		console.log("To login screen");
		$location.path('/login');
	}
}