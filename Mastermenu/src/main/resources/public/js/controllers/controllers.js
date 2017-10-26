var mastermenuControllers = angular.module('mastermenuControllers', []);

mastermenuControllers.controller('BebidasCtrl', [
	'$scope',
	'$http',
	'$location',
	'$routeParams',
	'$rootScope',
	function($scope, $http, $location, $routeParams, $rootScope) {
		$scope.init = function() {
			$scope.idHouse = $routeParams.idHouse;
			
			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
	 				function(data) {
	 					$scope.house = data;
	 				});
			
			$http.get("mastermenu/v1/produtoPorCategoria/1/"+$scope.idHouse).success(
					function(data) {
						$scope.produtos = data;
					});
		}
		
		$scope.adicionar = function(produto) {
 			$scope.solicitation = {
 					"id" : null,
 					"product" : produto,
 					"quantity" : 1,
 					"idClient" : 1,
 					"house" : $scope.house
 			}
 			$http.post("mastermenu/v1/solicitationTemp", $scope.solicitation).success(
 					function(data) {
 						alert(data);
			});	
 		}
		
		$scope.back = function() {
 			$location.path('menu/'+$scope.idHouse);
 		}
		
		$scope.init();
	} ]);

mastermenuControllers.controller('ComidasCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$routeParams',
 	function($scope, $http, $location, $routeParams) {
 		$scope.init = function() {
	 		$scope.idHouse = $routeParams.idHouse;
	 		
	 		$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
 				function(data) {
 					$scope.house = data;
 				});
 		
 		
	 		$http.get("mastermenu/v1/produtoPorCategoria/2/"+$scope.idHouse).success(
 				function(data) {
 					$scope.produtos = data;
 				});
 		}
 		
 		$scope.adicionar = function(produto) {
 			$scope.solicitation = {
 					"id" : null,
 					"product" : produto,
 					"quantity" : 1,
 					"idClient" : 1,
 					"house" : $scope.house
 			}
 			$http.post("mastermenu/v1/solicitationTemp", $scope.solicitation).success(
 					function(data) {
 						alert(data);
			});	
 		}
 		
 		$scope.back = function() {
 			$location.path('menu/'+$scope.idHouse);
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('ListaDePedidosCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	'$routeParams',
  	function($scope, $http, $location, $routeParams) {
  		$scope.init = function(){                  
  			$scope.idHouse = $routeParams.idHouse;
  			$scope.idClient = 1;
  			
  			$http.get("mastermenu/v1/solicitationTemp/"+$scope.idHouse+"/"+$scope.idClient).success(
 				function(data) {
 					$scope.solicitationsTemp = data;
 				});
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 				});
  	 		
        }
  		
  		$scope.solicitationOrders = function() {
  			for (var i = 0; i < $scope.solicitationsTemp.length; i++) { 
  				$http.post("mastermenu/v1/solicitation", $scope.solicitationsTemp[i]).success(
  						function(data) {}
  				);
  			}
  			
  			$scope.commands = {
 					"id" : null,
 					"idClient" : $scope.idClient,
 					"idHouse" : $scope.idHouse,
 					"mesa" : 1,
 					"solicitations" : $scope.solicitationsTemp
 			}
  			
  			$http.post("mastermenu/v1/commands", $scope.commands).success(
  					function(data) {
  					});
  			
  			delete $scope.solicitationsTemp; 
  			$http.delete("mastermenu/v1/solicitationsTemp?idClient="+$scope.idClient).success(
  					function(data) {});
  			alert("Pedidos enviados para produção!");
  		}
  		
  		$scope.back = function() {
 			$location.path('houseResource/'+$scope.idHouse);
 		}
  		
        $scope.init();
  	} ]);

mastermenuControllers.controller('CategoryCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	'$routeParams',
  	'$window',
  	function($scope, $http, $location, $routeParams, $window) {
  		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  					function(data) {
  						$scope.house = data;
			});
	
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
					$window.location.reload();
				});
 		}
  		
  		$scope.actionUpdate = function(category) {
  			var idCategory = category.id;
  			$location.path('update/'+idCategory+'/'+$scope.idHouse);
  		}
  		
  		$scope.actionDelete = function(category) {
  			$scope.categoryDelete = category;
  		}
  		
  		$scope.deleteCategory = function() {
  			$http.delete("mastermenu/v1/category?id="+$scope.categoryDelete.id).success(
  					function(data) {
  						 $scope.init();
  					});
  		}
  		
  		$scope.cancel = function() {
  			delete $scope.categoryDelete;
  		}
  		
  		$scope.back = function() {
  			$location.path('house/'+$scope.idHouse);
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
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/category/"+$scope.id).success(
  	 				function(data) {
  	 					$scope.category = data;
  	 		});
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  					function(data) {
  						$scope.house = data;
			});
  		}
  		
  		$scope.updateCategory = function(category) {
 			$http.put("mastermenu/v1/category/"+$scope.id, category).success(
				function(data) {
					$location.path('category/'+$scope.idHouse);
				});
 		}
  		
  		$scope.back = function() {
  			$location.path('category/'+$scope.idHouse);
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
  			$scope.idHouse = $routeParams.idHouse;
  			$scope.selected = [];
  			$scope.selectedOptions = [];
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  					function(data) {
  						$scope.house = data;
			});
	
  			
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
  			$http.get("mastermenu/v1/product/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.products = data;
  	 		});
  			$http.get("mastermenu/v1/composition").success(
  	 				function(data) {
  	 					$scope.compositions = data;
  	 					$scope.optionsComposition = data;
  	 		});
  			
  		}
  		
  		$scope.back = function() {
			$location.path('house/'+$scope.idHouse);
		}
  		
  		$scope.addProduct = function(product) {
  			product.house = new Object();
  			product.house.id = $scope.idHouse;
  			product.compositions = $scope.selected;
  			product.optionsComposition = $scope.selectedOptions;
  			$http.post("mastermenu/v1/product", product).success(
  					function(data) {
  						$window.location.reload();
  					});
  		}
  		
  		$scope.closeModalOptionsComposition = function() {
  			$scope.selectedOptions = [];
  		}
  		
  		$scope.actionUpdate = function(product) {
  			var idProduct = product.id;
  			$location.path('updateProduct/'+idProduct+'/'+$scope.idHouse);
  		}
  		
  		$scope.actionDelete = function(productDeleted) {
  			$scope.productDeleted = productDeleted;
  		}
  		
  		$scope.deleteProduct = function() {
  			$http.delete("mastermenu/v1/product?id="+$scope.productDeleted.id).success(
  					function(data) {
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
 		$scope.init = function() {
 			$http.get("mastermenu/v1/house").success(
 	 				function(data) {
 	 					$scope.houses = data;
 	 			});
 		}
 		
 		$scope.login = function() {
 			$http.post("mastermenu/v1/authenticate", $scope.user).success(
				function(data) {
					if(data === "true") {
						$('#modalLogin').modal('toggle');
						$('.modal-backdrop').remove();
						$location.path('panel');
					} else {
						alert(data);
					}
				});
 		}
 		
 		$scope.closeModal = function() {
 			$("[data-dismiss=modal]").trigger({ type: "click" });
 		}
 		
 		$scope.reload = function() {
 			$location.path('panel');
 		}
 		
 		$scope.save = function() {
 			if($scope.user.password != $scope.confirmPassword) {
 				alert("Por favor, confirme a senha correta.");
 				$scope.confirmPassword = "";
 			} else {
 				$http.post("mastermenu/v1/user", $scope.user).success(
  					function(data) {
  						if(data === "E-mail já cadastrado!") {
  							delete $scope.user.email;
  		 					alert(data);
  		 				} else {
  		 					delete $scope.user;
  	  						delete $scope.confirmPassword;
  		 					$location.path("/panel");
  		 				}
  					});
 			}
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('RegistrationCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	function($scope, $http, $location) {
   		$scope.init = function() {
 			$http.get("mastermenu/v1/house").success(
 	 				function(data) {
 	 					$scope.houses = data;
 	 			});
 		}
 		
 		$scope.actionUpdate = function(house) {
  			var idHouse = house.id;
  			$location.path('updateHouse/'+idHouse);
  		}
 		
 		$scope.actionDelete = function(houseDeleted) {
  			$scope.houseDeleted = houseDeleted;
  		}
  		
  		$scope.deleteHouse = function() {
  			$http.delete("mastermenu/v1/house?id="+$scope.houseDeleted.id).success(
  					function(data) {
  						alert(data);
  						$scope.init();
  					});
  		}
  		
  		$scope.moreOptions = function(house) {
  			var idHouse = house.id;
  			$location.path('house/'+idHouse);
  		}
  		
  		$scope.init();
  		
   	} ]);

mastermenuControllers.controller('UpdateProductCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.id = $routeParams.idProduct;
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/productById/"+$scope.id).success(
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
					$location.path('product/'+$scope.idHouse);
				});
 		}
  		
  		$scope.back = function() {
			$location.path('product/'+$scope.idHouse);
		}
  		
  		$scope.init();
   	} ]);

mastermenuControllers.controller('CreateHouseCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$window',
 	function($scope, $http, $location, $window) {
 		$scope.addHouse = function(house) {
  			$http.post("mastermenu/v1/house", house).success(
  					function(data) {
  						$location.path("/registration");
  					});
  		}
 	} ]);

mastermenuControllers.controller('HouseCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$window',
 	'$routeParams',
 	function($scope, $http, $location, $window, $routeParams) {
 		$scope.init = function() {
 			$scope.idHouse = $routeParams.idHouse;
 			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
 		}
 		
 		$scope.actionProduct = function() {
 			$location.path('product/'+$scope.idHouse);
 		}
 		
 		$scope.actionCategory = function() {
 			$location.path('category/'+$scope.idHouse);
 		}
 		
 		$scope.actionCallSolicitationFood = function() {
 			$location.path('solicitationFood/'+$scope.idHouse);
 		}
 		
 		$scope.actionCallClosedSolicitations = function() {
 			$location.path('closedSolicitations/'+$scope.idHouse);
 		}
 		
 		$scope.actionCallSolicitationDrink = function() {
 			$location.path('solicitationDrink/'+$scope.idHouse);
 		}
 		
 		$scope.actionCallCommands = function() {
 			$location.path('commands/'+$scope.idHouse);
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('UpdateHouseCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.id = $routeParams.idHouse;
  			$http.get("mastermenu/v1/house/"+$scope.id).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		}
  		
  		$scope.updateHouse = function(house) {
 			$http.put("mastermenu/v1/house/"+$scope.id, house).success(
				function(data) {
					$location.path('registration');
				});
 		}
  		
  		$scope.init();
   	} ]);

mastermenuControllers.controller('PanelCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
   			$http.get("mastermenu/v1/house").success(
 				function(data) {
 					$scope.houses = data;
 				});
   		} 
   		
   		$scope.actionHouse = function(house) {
   			$scope.idHouse = house.id;
   			$location.path('houseResource/'+ $scope.idHouse);
   		}
   		
   		$scope.init();
   	} ]);

mastermenuControllers.controller('OfficialPanelCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   	} ]);

mastermenuControllers.controller('MenuCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		} 
   		
   		$scope.actionDrink = function() {
   			$location.path('bebidas/'+$scope.idHouse);
   		}
   		
   		$scope.actionFood = function() {
   			$location.path('comidas/'+$scope.idHouse);
   		}
   		
   		$scope.back = function() {
   			$location.path('houseResource/'+$scope.idHouse);
   		}
   		$scope.init();
   	} ]);

mastermenuControllers.controller('HouseResourceCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		} 
   		
   		$scope.actionMenu = function() {
   			$location.path('menu/'+$scope.idHouse);
   		}
   		
   		$scope.actionShoppingList = function() {
   			$location.path('listaDePedidos/'+$scope.idHouse);
   		}
   		
   		$scope.init();
   	} ]);

mastermenuControllers.controller('SolicitationFoodCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	'$window',
   	function($scope, $http, $location, $routeParams, $window) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/solicitationByIdCategoryAndNotClosed/"+$scope.idHouse+"/2").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse);
   		}
   		
   		$scope.closeSolicitation = function(solicitation) {
   			solicitation.status = "ENCERRADO";
   			$http.put("mastermenu/v1/solicitation/"+solicitation.id, solicitation).success(
   					function(data) {});
   			alert("Encerrado com sucesso!");
   			$window.location.reload();
   		}
   		
   		$scope.init();
   	} ]);


mastermenuControllers.controller('SolicitationDrinkCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	'$window',
   	function($scope, $http, $location, $routeParams, $window) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/solicitationByIdCategoryAndNotClosed/"+$scope.idHouse+"/1").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse);
   		}
   		
   		$scope.closeSolicitation = function(solicitation) {
   			solicitation.status = "ENCERRADO";
   			$http.put("mastermenu/v1/solicitation/"+solicitation.id, solicitation).success(
   					function(data) {});
   			alert("Encerrado com sucesso!");
   			$window.location.reload();
   		}
   		
   		$scope.init();
   	} ]);


mastermenuControllers.controller('ClosedSolicitationsCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/closedSolicitations/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.closedSolicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse);
   		}
   		
   		$scope.closeSolicitation = function(solicitation) {
   			solicitation.status = "ENCERRADO";
   			$http.put("mastermenu/v1/solicitation/"+$scope.id, category).success(
   					function(data) {
   						alert(data);
   					});
   		}
   		
   		$scope.init();
   	} ]);

mastermenuControllers.controller('CommandsCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			
  			$http.get("mastermenu/v1/commands/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.commands = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse);
   		}
   		
   		$scope.closeCommands = function(c) {
   			c.status = "ENCERRADO";
   			$http.put("mastermenu/v1/commands/"+$scope.id, c).success(
   					function(data) {
   						alert(data);
   					});
   		}
   		
   		$scope.init();
   	} ]);

