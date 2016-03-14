var townController = function($scope, $http, $location, userData) {	
	$scope.money = userData.character.money;
	$scope.buyPotion = function() {
		var object = {player: userData.character}
		$http.post('/buy/Potion', object, {})
		.success(function(data, status, headers, config) {
			userData.character = data.player;
			console.log(data);
			$scope.money = userData.character.money;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	$scope.buyAttack = function() {
		var object = {player: userData.character}
		$http.post('/buy/Attack', object, {})
		.success(function(data, status, headers, config) {
			userData.character = data.player;
			console.log(data);
			$scope.money = userData.character.money;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	$scope.buyHealth = function() {
		var object = {player: userData.character}
		$http.post('/buy/Health', object, {})
		.success(function(data, status, headers, config) {
			userData.character = data.player;
			console.log(data);
			$scope.money = userData.character.money;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.toWorld = function() {
		$location.path('/world');
	}
}