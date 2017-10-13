angular.module('mastermenuModule', [ 'ngRoute', 'mastermenuControllers', 'mastermenuServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/commands/:idHouse', {
				templateUrl : '../../view/registration/commands.html',
				controller : 'CommandsCtrl'
			})
			.when('/solicitationDrink/:idHouse', {
				templateUrl : '../../view/registration/solicitationDrink.html',
				controller : 'SolicitationDrinkCtrl'
			})
			.when('/closedSolicitations/:idHouse', {
				templateUrl : '../../view/registration/closedSolicitations.html',
				controller : 'ClosedSolicitationsCtrl'
			})
			.when('/solicitationFood/:idHouse', {
				templateUrl : '../../view/registration/solicitationFood.html',
				controller : 'SolicitationFoodCtrl'
			})
			.when('/houseResource/:idHouse', {
				templateUrl : '../../view/client/houseResource.html',
				controller : 'HouseResourceCtrl'
			})
			.when('/house/:idHouse', {
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
			.when('/listaDePedidos/:idHouse', {
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
			.when('/comidas/:idHouse', {
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