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
  		
  		$scope.actionDelete = function(category) {
  			$scope.categoryDelete = category;
  		}
  		
  		$scope.deleteCategory = function() {
  			alert("id:" + $scope.categoryDelete.id);
  			/*$http.delete("mastermenu/v1/category?id="+$scope.categoryDelete.id).success(
  					function(data) {
  						 $scope.init();
  					});*/
  		}
  		
  		$scope.cancel = function() {
  			delete $scope.categoryDelete;
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
 			$http.put("mastermenu/v1/category/"+$scope.id, category).success(
				function(data) {
					$location.path('category');
				});
 		}
  		
  		$scope.init();
} ]);

mastermenuControllers.controller('ProductCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	'$routeParams',
  	'$route',
  	'$window',
  	function($scope, $http, $location, $routeParams, $route, $window) {
  		$scope.init = function() {
  			$scope.selected = [];
  			$scope.selectedOptions = [];
  			
  			$scope.exist = function(c) {
  				return $scope.selected.indexOf(c) > -1;
  			}
  			
  			$scope.toggleSelection = function(c) {
  				var idx = $scope.selected.indexOf(c);
  				if(idx > -1) {
  					$scope.selected.splice(idx, 1);
  				} else {
  					$scope.selected.push(c);
  				}
  			}
  			
  			$scope.existOptions = function(co) {
  				return $scope.selectedOptions.indexOf(co) > -1;
  			}
  			
  			$scope.toggleSelectionOptions = function(co) {
  				var idx = $scope.selectedOptions.indexOf(co);
  				if(idx > -1) {
  					$scope.selectedOptions.splice(idx, 1);
  				} else {
  					$scope.selectedOptions.push(co);
  				}
  			}
  			$http.get("mastermenu/v1/category").success(
  	 				function(data) {
  	 					$scope.categories = data;
  	 		});
  			$http.get("mastermenu/v1/product").success(
  	 				function(data) {
  	 					$scope.products = data;
  	 		});
  			$http.get("mastermenu/v1/composition").success(
  	 				function(data) {
  	 					$scope.compositions = data;
  	 					$scope.optionsComposition = data;
  	 		});
  			
  		}
  		
  		$scope.addProduct = function(product) {
  			product.compositions = $scope.selected;
  			product.optionsComposition = $scope.selectedOptions;
  			$http.post("mastermenu/v1/product", product).success(
  					function(data) {
  						$window.location.reload();
  						alert(data);
  					});
  		}
  		
  		$scope.closeModalOptionsComposition = function() {
  			$scope.selectedOptions = [];
  		}
  		
  		$scope.actionUpdate = function(product) {
  			var idProduct = product.id;
  			$location.path('updateProduct/'+idProduct);
  		}
  		
  		$scope.actionDelete = function(productDeleted) {
  			$scope.productDeleted = productDeleted;
  		}
  		
  		$scope.deleteProduct = function() {
  			$http.delete("mastermenu/v1/product?id="+$scope.productDeleted.id).success(
  					function(data) {
  						alert(data);
  						$scope.init();
  					});
  		}
  		$scope.init();
} ]);

mastermenuControllers.controller('MainCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	function($scope, $http, $location) {
 	
 	} ]);

mastermenuControllers.controller('RegistrationCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	function($scope, $http, $location) {
   	
   	} ]);

mastermenuControllers.controller('UpdateProductCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.id = $routeParams.idProduct;
  			$http.get("mastermenu/v1/product/"+$scope.id).success(
  	 				function(data) {
  	 					$scope.product = data;
  	 		});
  			$http.get("mastermenu/v1/category").success(
 				function(data) {
 					for(var i = data.length; i--;){
 						if (data[i].nome === $scope.product.categoria.nome) data.splice(i, 1);
 					}
 					$scope.categories = data;
 			});
  		}
  		
  		$scope.updateProduct = function(product) {
 			$http.put("mastermenu/v1/product/"+$scope.id, product).success(
				function(data) {
					$location.path('product');
				});
 		}
  		
  		$scope.init();
   	} ]);