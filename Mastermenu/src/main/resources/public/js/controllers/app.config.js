angular.module('mastermenuModule', [ 'ngRoute', 'mastermenuControllers', 'mastermenuServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/diningTable/:idHouse/:idUser', {
				templateUrl : '../../view/registration/diningTable.html',
				controller : 'DiningTableCtrl'
			})
			.when('/commands/:idHouse/:idUser', {
				templateUrl : '../../view/registration/commands.html',
				controller : 'CommandsCtrl'
			})
			.when('/solicitationDrink/:idHouse/:idUser', {
				templateUrl : '../../view/registration/solicitationDrink.html',
				controller : 'SolicitationDrinkCtrl'
			})
			.when('/closedSolicitations/:idHouse/:idUser', {
				templateUrl : '../../view/registration/closedSolicitations.html',
				controller : 'ClosedSolicitationsCtrl'
			})
			.when('/solicitationFood/:idHouse/:idUser', {
				templateUrl : '../../view/registration/solicitationFood.html',
				controller : 'SolicitationFoodCtrl'
			})
			.when('/houseResource/:idHouse/:idUser', {
				templateUrl : '../../view/client/houseResource.html',
				controller : 'HouseResourceCtrl'
			})
			.when('/house/:idHouse/:idUser', {
				templateUrl : '../../view/registration/house.html',
				controller : 'HouseCtrl'
			})
			.when('/panel/:idUser', {
				templateUrl : '../../view/client/panel.html',
				controller : 'PanelCtrl'
			})
			.when('/officialPanel', {
				templateUrl : '../../view/registration/officialPanel.html',
				controller : 'OfficialPanelCtrl'
			})
			.when('/updateHouse/:idHouse/:idUser', {
				templateUrl : '../../view/registration/updateHouse.html',
				controller : 'UpdateHouseCtrl'
			})
			.when('/createHouse/:idUser', {
				templateUrl : '../../view/registration/createHouse.html',
				controller : 'CreateHouseCtrl'
			})
			.when('/updateProduct/:idProduct/:idHouse', {
				templateUrl : '../../view/registration/updateProduct.html',
				controller : 'UpdateProductCtrl'
			})
			.when('/update/:idCategory/:idHouse', {
				templateUrl : '../../view/registration/modalUpdate.html',
				controller : 'UpdateCtrl'
			})
			.when('/category/:idHouse/:idUser', {
				templateUrl : '../../view/registration/category.html',
				controller : 'CategoryCtrl'
			})
			.when('/product/:idHouse/:idUser', {
				templateUrl : '../../view/registration/product.html',
				controller : 'ProductCtrl'
			})
			.when('/registration/:idUser', {
				templateUrl : '../../view/registration/registration.html',
				controller : 'RegistrationCtrl'
			})
			.when('/listaDePedidos/:idHouse/:idUser', {
				templateUrl : '../../view/client/listaDePedidos.html',
				controller : 'ListaDePedidosCtrl'
			})
			.when('/menu/:idHouse/:idUser', {
				templateUrl : '../../view/client/menu.html',
				controller : 'MenuCtrl'
			})
			.when('/bebidas/:idHouse/:idUser', {
				templateUrl : '../../view/client/bebidas.html',
				controller : 'BebidasCtrl'
			})
			.when('/comidas/:idHouse/:idUser', {
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