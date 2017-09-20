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
		$scope.adicionar = function(produto) {
 			$scope.solicitation = {
 					"id" : null,
 					"product" : produto,
 			}
 			$http.post("mastermenu/v1/solicitation", $scope.solicitation).success(
				function(data) {
					 alert(data);
				});
 		}
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
 			$scope.solicitation = {
 					"id" : null,
 					"product" : produto,
 			}
 			$http.post("mastermenu/v1/solicitation", $scope.solicitation).success(
				function(data) {
					 alert(data);
				});
 		}
 	} ]);

mastermenuControllers.controller('ListaDePedidosCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	function($scope, $http, $location) {
  		$scope.init = function(){                   
  			$http.get("mastermenu/v1/solicitation").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 				});
        }
        $scope.init();
  	} ]);