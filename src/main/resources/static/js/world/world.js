angular.module('world', ['userInfo'])
	.controller('worldController', ['$scope', '$http', '$location', 'userData', worldController])