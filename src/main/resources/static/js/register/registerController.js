var registerController = function($scope, $http, $location) {
	$scope.userNameError = false;
	$scope.emailError = false;
	$scope.passwordError = false;
	$scope.sendRegister = function() {
		var data = {
			userName: $scope.userName,
			email: $scope.email,
			password: $scope.password,
			confirmPassword: $scope.confirmPassword
		}
		var config = {}
		$http.post('/register', data, config)
		.success(function (data, status, headers, config) {
			$scope.userNameError = data.UsernameTaken;
			$scope.emailError = data.EmailTaken;
			$scope.passwordError = data.PasswordMismatch;
	        if(!$scope.userNameError && !$scope.emailError && !$scope.passwordError && !data.databaseError) {
	        	$location.path('/login');
	        }
	        if(data.databaseError) {
	        	console.log("REGISTER");
	        	console.log(data.counter);
	        }
		})
		.error(function (data, status, header, config) {
			console.error(data);
			console.error(status);
			console.error(headers);
		})
	}
	
	$scope.toLogin = function() {
		$location.path('/login');
	}
}