var battleController = function($scope) {
	
	$scope.maxCharacterHealth = 10;
	$scope.maxEnemyHealth = 10;
	$scope.characterHealth = 10;
	$scope.enemyHealth = 10;
	
	$scope.attack = function() {
		$scope.enemyHealth -= 1;
	}
	
	$scope.heal = function() {
		
	}
}
