var battleController = function($scope, $http) {
	
	$scope.maxCharacterHealth = 10;
	$scope.maxEnemyHealth = 10;

	$scope.enemy = {name: "Goblin", health: 10, attack: 1};
	$scope.player = {userId: 1, characterID: 1, characterName: 'Charles', health: 10, attack: 1, healItems: 2, money: 1000};
	
	var generateEnemy = function() {		
		$http.post('/combat/pve', $scope.player, {})
		.success(function(data, status, headers, config) {
			$scope.player = data.thePlayer;
			$scope.enemy = data.theEnemy;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.attack = function() {
		var combatObject = {thePlayer: $scope.player, theEnemy: $scope.enemy};
		
		$http.post('/combat/pve/Attack', combatObject, {})
		.success(function(data, status, headers, config) {
			console.log(data.thePlayer);
			$scope.player = data.thePlayer;
			$scope.enemy = data.theEnemy;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.heal = function() {
		var combatObject = {thePlayer: $scope.player, theEnemy: $scope.enemy};
		
		$http.post('/combat/pve/Heal', combatObject, {})
		.success(function(data, status, headers, config) {
			console.log(data.thePlayer);
			$scope.player = data.thePlayer;
			$scope.enemy = data.theEnemy;
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	generateEnemy();
}
