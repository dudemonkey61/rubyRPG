var townController = function($scope, $http, $location) {
	$scope.thing = 'hello';
	$scope.shopList = [{
		name: 'sword',
		price: 233,
		description: 'A long sharp doubled edged blade'
	}, {
		name: 'hat',
		price: 50,
		description: 'A really cool hat'
	}]
	$scope.buyPotion = function() {
		console.log('You bought 1 potion');
	}
	$scope.buyAttack = function() {
		console.log('You bought 1 attack');
	}
	$scope.buyHealth = function() {
		console.log('You bought 1 health');
	}
	$scope.money = 100000
}