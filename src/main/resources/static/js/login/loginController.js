var loginController = function($scope, $http, $location, userData) {
	$scope.UsernamePasswordError = false;
	$scope.sendLogin = function() {
		var data = {
			userName: $scope.userName,
			password: $scope.password
		}
		var config = {}
		$http.post('/login', data, config)
		.success(function (data, status, headers, config) {
			console.log(data.userName);
			$scope.UsernamePasswordError = data.IncorrectUsernameOrPassword;
			if(!$scope.UsernamePasswordError) {
				userData.userId = data.userId;
				userData.userName = data.userName;
				$location.path('/world');
			}
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