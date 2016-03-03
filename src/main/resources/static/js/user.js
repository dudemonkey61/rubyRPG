var userModule = angular.module('userInfo', [])
	.service('userData', function() {
		var userId = -1;
		var userName = false;
		
		return {
			userId: userId,
			userName: userName
		}
	})