angular.module('battle', ['userInfo', 'ui.bootstrap'])
	.controller('userWinController', ['$scope', '$location', '$uibModalInstance', userWinController])
	.controller('userLossController', ['$scope', '$location', '$uibModalInstance', userLossController])
	.controller('battleController', ['$scope', '$http', '$uibModal', 'userData', battleController])