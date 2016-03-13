var userModule = angular.module('userInfo', [])
	.service('userData', function() {
		var userId = -1;
		var userName = false;
		var character = {
				userId: 1,
				characterID: 1,
				characterName: 'Charles',
				currentHealth: 10,
				maxHealth: 10,
				maxHealth: 10,
				attack: 1,
				healItems: 2,
				money: 1000
		}
		
		return {
			userId: userId,
			userName: userName,
			character: character
		}
	})