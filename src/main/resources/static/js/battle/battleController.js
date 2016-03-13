var battleController = function($scope, $http, $uibModal, userData) {	
	$scope.maxEnemyHealth;

	$scope.enemy = {name: "Goblin", health: 10, attack: 1};
	
	var refreshScopePlayer = function() {
		$scope.player = userData.character;
	}
	refreshScopePlayer();
	
	var generateEnemy = function() {		
		$http.post('/combat/pve', $scope.player, {})
		.success(function(data, status, headers, config) {
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			$scope.maxEnemyHealth = data.theEnemy.health;
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
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			refreshScopePlayer();
			determineEnded(data);
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	$scope.heal = function() {
		var combatObject = {thePlayer: $scope.player, theEnemy: $scope.enemy};
		
		$http.post('/combat/pve/Heal', combatObject, {})
		.success(function(data, status, headers, config) {
			userData.character = data.thePlayer;
			$scope.enemy = data.theEnemy;
			refreshScopePlayer();
			determineEnded(data);
		})
		.error(function(data, status, headers, config) {
			console.error(data);
		})
	}
	
	var playerWin = function() {
		var thePopup = $uibModal.open({
			animation: true,
			templateUrl: 'userWin.html',
			controller: 'userWinController',
			size: 'lg',
		})
		userData.character.currentHealth = userData.character.maxHealth;
	}
	
	var playerLoss = function() {
		var thePopup = $uibModal.open({
			animation: true,
			templateUrl: 'userLoss.html',
			controller: 'userLossController',
			size: 'lg',
		})
		userData.character.currentHealth = userData.character.maxHealth;
	}
	
	var determineEnded = function(data) {
		if (data.thePlayer.currentHealth <= 0) {
			playerLoss();
		}
		else if (data.theEnemy.health <= 0) {
			playerWin();
		}
	}
	
	generateEnemy();
}
