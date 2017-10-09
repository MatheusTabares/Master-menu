angular.module('mastermenuModule', [ 'ngRoute', 'mastermenuControllers', 'mastermenuServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/house/:idHouse', {
				templateUrl : '../../view/registration/house.html',
				controller : 'HouseCtrl'
			})
			.when('/panel', {
				templateUrl : '../../view/client/panel.html',
				controller : 'PanelCtrl'
			})
			.when('/officialPanel', {
				templateUrl : '../../view/registration/officialPanel.html',
				controller : 'OfficialPanelCtrl'
			})
			.when('/updateHouse/:idHouse', {
				templateUrl : '../../view/registration/updateHouse.html',
				controller : 'UpdateHouseCtrl'
			})
			.when('/createHouse', {
				templateUrl : '../../view/registration/createHouse.html',
				controller : 'CreateHouseCtrl'
			})
			.when('/updateProduct/:idProduct', {
				templateUrl : '../../view/registration/updateProduct.html',
				controller : 'UpdateProductCtrl'
			})
			.when('/update/:idCategory', {
				templateUrl : '../../view/registration/modalUpdate.html',
				controller : 'UpdateCtrl'
			})
			.when('/category', {
				templateUrl : '../../view/registration/category.html',
				controller : 'CategoryCtrl'
			})
			.when('/product/:idHouse', {
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
			.when('/menu/:idHouse', {
				templateUrl : '../../view/client/menu.html',
				controller : 'MenuCtrl'
			})
			.when('/bebidas/:idHouse', {
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