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
			$scope.UsernamePasswordError = data.IncorrectUsernameOrPassword;
			if(!$scope.UsernamePasswordError) {
				userData.userId = data.userId;
				userData.characterId = data.playerData.getCharacterID();
				userData.characterName = data.playerData.getCharacterName();
				userData.currentHealth = data.playerData.getCurrentHealth();
				userData.maxHealth = data.playerData.getMaxHealth();
				userData.attack = data.playerData.getAttack();
				userData.healingItems = data.playerData.getHealItems();
				userData.money = data.playerData.getMoney();
				userData.town = data.playerData.getTown();
				console.log("userId: " + userData.userId);
				console.log("characterId: " + userData.characterId);
				console.log("characterName: " + userData.characterName);
				console.log("currentHealth: " + userData.currentHealth);
				console.log("maxHealth: " + userData.maxHealth);
				console.log("attack: " + userData.attack);
				console.log("healingItems: " + userData.healingItems);
				console.log("money: " + userData.money);
				console.log("town: " + userData.town);
				
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