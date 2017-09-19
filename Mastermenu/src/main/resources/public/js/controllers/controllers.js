var mastermenuControllers = angular.module('mastermenuControllers', []);
mastermenuControllers.controller('BebidasCtrl', [
	'$scope',
	'$http',
	'$location',
	function($scope, $http, $location) {
		$http.get("mastermenu/v1/produtoPorCategoria/1").success(
				function(data) {
					$scope.produtos = data;
				});
	} ]);

mastermenuControllers.controller('ComidasCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	function($scope, $http, $location) {
 		$http.get("mastermenu/v1/produtoPorCategoria/2").success(
 				function(data) {
 					$scope.produtos = data;
 				});
 		$scope.adicionar = function(produto) {
 			window.sessionStorage.setItem('produto', JSON.stringify(produto));
            window.location.href='#/edit.html'
 		}
 	} ]);

mastermenuControllers.controller('ListaDePedidosCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	function($scope, $http, $location) {
  		$scope.init = function(){                   
            $scope.produto = JSON.parse(window.sessionStorage.getItem('produto'));
            window.sessionStorage.removeItem('produto');
        }
        $scope.init();
  	} ]);