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

mastermenuControllers.controller('CategoryCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	function($scope, $http, $location) {
  		$scope.init = function() {
	  		$http.get("mastermenu/v1/category").success(
	 				function(data) {
	 					delete $scope.categories;
	 					$scope.categories = data;
	 				});
  		}
	  
  		$scope.addCategory = function(category) {
 			$scope.category = {
 					"id" : null,
 					"nome" : category.name,
 			}
 			$http.post("mastermenu/v1/category", $scope.category).success(
				function(data) {
					 $scope.init();
				});
 		}
  		
  		$scope.actionUpdate = function(category) {
  			var idCategory = category.id;
  			$location.path('update/'+idCategory);
  		}
  		
  		$scope.init();
  	} ]);

mastermenuControllers.controller('UpdateCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	'$routeParams',
  	function($scope, $http, $location, $routeParams) {
  		$scope.init = function() {
  			$scope.id = $routeParams.idCategory;
  			$http.get("mastermenu/v1/category/"+$scope.id).success(
  	 				function(data) {
  	 					$scope.category = data;
  	 		});
  		}
  		
  		$scope.updateCategory = function(category) {
 			$scope.category = {
 					"id" : null,
 					"nome" : category.name,
 			}
 			$http.put("mastermenu/v1/category/"+$scope.id, category).success(
				function(data) {
					$location.path('category');
				});
 		}
  		$scope.init();
} ]);