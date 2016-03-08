var townController = function($scope, $http, $location) {
	$scope.thing = 'hello';
	$scope.money = 100000;
	
	var object = { player: {userId: 1, characterID: 1, characterName: 'Charles', health: 10, attack: 1, healItems: 2, money: 1000} };
	$scope.buyPotion = function() {
		$http.post('/buy/Potion', object, {})
		.success(function(data, status, headers, config) {
			console.log(data);
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	$scope.buyAttack = function() {
		$http.post('/buy/Attack', object, {})
		.success(function(data, status, headers, config) {
			console.log(data);
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	$scope.buyHealth = function() {
		$http.post('/buy/Health', object, {})
		.success(function(data, status, headers, config) {
			console.log(data);
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.toWorld = function() {
		$location.path('/world');
	}
}