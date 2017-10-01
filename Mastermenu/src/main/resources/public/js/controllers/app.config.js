angular.module('mastermenuModule', [ 'ngRoute', 'mastermenuControllers', 'mastermenuServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/update/:idCategory', {
				templateUrl : '../../view/registration/modalUpdate.html',
				controller : 'UpdateCtrl'
			})
			.when('/category', {
				templateUrl : '../../view/registration/category.html',
				controller : 'CategoryCtrl'
			})
			.when('/product', {
				templateUrl : '../../view/registration/product.html',
				controller : 'ProductCtrl'
			})
			.when('/registration', {
				templateUrl : '../../view/registration/registration.html',
				controller : 'RegistrationCtrl'
			})
			.when('/listaDePedidos', {
				templateUrl : '../../view/client/listaDePedidos.html',
				controller : 'ListaDePedidosCtrl'
			})
			.when('/menu', {
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