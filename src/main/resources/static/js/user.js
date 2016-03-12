var userModule = angular.module('userInfo', [])
	.service('userData', function() {
		var userId = -1;
		var characterId = -1;
		var characterName = '';
		var currentHealth = -1;
		var maxHealth = -1;
		var attack = -1;
		var healingItems = -1;
		var money = -1;
		var town = '';

		
		return {
			userId: userId,
			characterId: characterId,
			characterName: characterName,
			currentHealth: currentHealth,
			maxHealth: maxHealth,
			attack: attack,
			healingItems: healingItems,
			money: money,
			town: town
		}
	})