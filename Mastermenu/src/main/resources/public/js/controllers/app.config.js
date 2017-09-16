angular.module('mastermenuModule', [ 'ngRoute', 'mastermenuControllers', 'mastermenuServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/menu', {
				templateUrl : '../../view/client/menu.html',
				controller : 'MenuCtrl'
			})
			.when('/bebidas', {
				templateUrl : '../../view/client/bebidas.html',
				controller : 'BebidasCtrl'
			})
			.when('/comidas', {
				templateUrl : '../../view/client/comidas.html',
				controller : 'ComidasCtrl'
			})
			.when('/main', {
				templateUrl : '../../view/client/main.html',
				controller : 'MainCtrl'
			})
			.otherwise({
				redirectTo : '/main'
			});
		} ]);