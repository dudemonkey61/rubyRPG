var battleController = function($scope, $http, userData) {	
	$scope.maxCharacterHealth = 10;
	$scope.maxEnemyHealth = 10;

	$scope.enemy = {name: "Goblin", health: 10, attack: 1};
	
	var refreshScopePlayer = function() {
		$scope.player = userData.character;
	}
	refreshScopePlayer();
	
	var generateEnemy = function() {		
		$http.post('/combat/pve', $scope.player, {})
		.success(function(data, status, headers, config) {
			console.log(data);
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			refreshScopePlayer();
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.attack = function() {
		var combatObject = {thePlayer: $scope.player, theEnemy: $scope.enemy};
		
		$http.post('/combat/pve/Attack', combatObject, {})
		.success(function(data, status, headers, config) {
			console.log(data);
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			refreshScopePlayer();
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.heal = function() {
		var combatObject = {thePlayer: $scope.player, theEnemy: $scope.enemy};
		
		$http.post('/combat/pve/Heal', combatObject, {})
		.success(function(data, status, headers, config) {
			console.log(data);
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			refreshScopePlayer();
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	generateEnemy();
}
