var loginController = function($scope, $http, $location) {
	$scope.sendLogin = function() {
		var data = {
			userName: $scope.userName,
			password: $scope.password
		}
		var config = {}
		$http.post('/login', data, config)
		.success(function (data, status, headers, config) {
			$location.path('/worldPage');
		})
		.error(function (data, status, header, config) {
			console.error(data);
			console.error(status);
			console.error(headers);
		})
	}
	
	$scope.toRegister = function() {
		$location.path('/register');
	}
}