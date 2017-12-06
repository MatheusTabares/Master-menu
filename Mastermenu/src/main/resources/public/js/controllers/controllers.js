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
			$scope.idUser = $routeParams.idUser;
			
			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
	 				function(data) {
	 					$scope.house = data;
	 				});
			
			$http.get("mastermenu/v1/produtoPorCategoria/1/"+$scope.idHouse).success(
					function(data) {
						$scope.produtos = data;
						$scope.showMenu();
					});
		}
		
		$scope.showMenu = function() {
			var date = new Date();
			var t = date.getTime();
			console.log("t: " + t);
			for (var i = 0; i < $scope.produtos.length; i++) { 
				if($scope.produtos[i].happyInit > t && t < $scope.produtos[i].happyEnd) {
					$scope.produtos[i].menu = false;
					$http.put("mastermenu/v1/product/"+$scope.id, $scope.produtos[i]).success(
							function(data) {});
					delete $scope.produtos[i];
				}
  			}
		}

		$scope.adicionar = function(produto) {
 			$scope.solicitation = {
 					"id" : null,
 					"product" : produto,
 					"quantity" : 1,
 					"idClient" : $scope.idUser,
 					"house" : $scope.house
 			}
 			$http.post("mastermenu/v1/solicitationTemp", $scope.solicitation).success(
 					function(data) {
 						alert(data);
			});	
 		}
		
		$scope.back = function() {
 			$location.path('menu/'+$scope.idHouse+'/'+$scope.idUser);
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
	 		$scope.idUser = $routeParams.idUser;
	 		
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
 					"idClient" : $scope.idUser,
 					"house" : $scope.house
 			}
 			$http.post("mastermenu/v1/solicitationTemp", $scope.solicitation).success(
 					function(data) {
 						alert(data);
			});	
 		}
 		
 		$scope.back = function() {
 			$location.path('menu/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('ListaDePedidosCtrl', [
  	'$scope',
  	'$http',
  	'$location',
  	'$routeParams',
  	'$route',
  	function($scope, $http, $location, $routeParams, $route) {
  		$scope.init = function(){                  
  			$scope.idHouse = $routeParams.idHouse;
  			$scope.idClient = $routeParams.idUser;
  			//$scope.idClient = 1;
  			
  			$scope.findOrders();
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
  			$scope.findOrders();
  			
  			$http.post("mastermenu/v1/commands", $scope.commands).success(
  					function(data) {
  					});
  			
  			delete $scope.solicitationsTemp; 
  			$http.delete("mastermenu/v1/solicitationsTemp?idClient="+$scope.idClient).success(
  					function(data) {});
  			alert("Pedidos enviados para produção!");
  			$route.reload();
  		}
  		
  		$scope.findOrders = function() {
  			$http.get("mastermenu/v1/solicitation/"+$scope.idHouse+"/house/"+$scope.idClient+"/user").success(
  	 				function(data) {
  	 					$scope.orders = data;
  	 				});
  		}
  		
  		$scope.back = function() {
 			$location.path('houseResource/'+$scope.idHouse+'/'+$scope.idClient);
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
  			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			
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
					$scope.back();
				});
 		}
  		
  		$scope.back = function() {
  			$location.path('category/'+$scope.idHouse+'/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
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
			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
		}
  		
  		$scope.addProduct = function(product) {
  			product.house = new Object();
  			product.house.id = $scope.idHouse;
  			product.compositions = $scope.selected;
  			product.optionsComposition = $scope.selectedOptions;
  			$http.post("mastermenu/v1/product", product).success(
  					function(data) {
  						$route.reload();
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
  		
  		$scope.actionHappyHour = function(product) {
  			$scope.productHappyHour = product;
  		}
  		
  		$scope.checkHappyHour = function() {
  			$scope.productHappyHour.happyInit = $scope.productHappyHour.happyInit.getTime();
  			$scope.productHappyHour.happyEnd = $scope.productHappyHour.happyEnd.getTime();
  			$http.put("mastermenu/v1/product/"+$scope.productHappyHour.id, $scope.productHappyHour).success(
  					function(data) {});
  		}
  		
  		$scope.init();
} ]);

mastermenuControllers.controller('MainCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$rootScope',
 	function($scope, $http, $location, $rootScope) {
 		$scope.init = function() {
 			$http.get("mastermenu/v1/house").success(
 	 				function(data) {
 	 					$scope.houses = data;
 	 			});
 		}
 		
 		$scope.login = function() {
 			$http.post("mastermenu/v1/authenticate", $scope.user).success(
				function(data) {
					if(data.id != undefined) {
						$('#modalLogin').modal('toggle');
						$('.modal-backdrop').remove();
						delete $scope.user.password;
						$rootScope.userLogged = $scope.user;
						$location.path('panel/'+data.id);
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
   	'$routeParams',
   	function($scope, $http, $location, $routeParams) {
   		$scope.init = function() {
   			$scope.idUser = $routeParams.idUser;
 			$http.get("mastermenu/v1/house/"+$scope.idUser+"/user").success(
 	 				function(data) {
 	 					$scope.houses = data;
 	 			});
 		}
 		
 		$scope.actionUpdate = function(house) {
  			var idHouse = house.id;
  			$location.path('updateHouse/'+idHouse+'/'+$scope.idUser);
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
  			$location.path('house/'+idHouse+'/'+$scope.idUser);
  		}
  		
  		$scope.callCreateHouse = function() {
  			$location.path('createHouse/'+$scope.idUser);
  		}
  		
  		$scope.back = function() {
  			$location.path('panel/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			
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
					$scope.back();
				});
 		}
  		
  		$scope.back = function() {
			$location.path('product/'+$scope.idHouse+'/'+$scope.idUser);
		}
  		
  		$scope.init();
   	} ]);

mastermenuControllers.controller('CreateHouseCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$window',
 	'$routeParams',
 	function($scope, $http, $location, $window, $routeParams) {
 		$scope.init = function() {
 			$scope.idUser = $routeParams.idUser;
 		}
 		
 		$scope.addHouse = function(house) {
 			house.idUser = $scope.idUser;
  			$http.post("mastermenu/v1/house", house).success(
  					function(data) {
  						$scope.back();
  					});
  		}
 		
 		$scope.back = function() {
 			$location.path("registration/"+$scope.idUser);
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('HouseCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$window',
 	'$routeParams',
 	function($scope, $http, $location, $window, $routeParams) {
 		$scope.init = function() {
 			$scope.idUser = $routeParams.idUser;
 			$scope.idHouse = $routeParams.idHouse;
 			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
 		}
 		
 		$scope.actionProduct = function() {
 			$location.path('product/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCategory = function() {
 			$location.path('category/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCallSolicitationFood = function() {
 			$location.path('solicitationFood/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCallClosedSolicitations = function() {
 			$location.path('closedSolicitations/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCallSolicitationDrink = function() {
 			$location.path('solicitationDrink/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCallCommands = function() {
 			$location.path('commands/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.actionCallDiningTable = function() {
 			$location.path('diningTable/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.back = function() {
 			$location.path('registration/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			$http.get("mastermenu/v1/house/"+$scope.id).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		}
  		
  		$scope.updateHouse = function(house) {
 			$http.put("mastermenu/v1/house/"+$scope.id, house).success(
				function(data) {
					$scope.back();
				});
 		}
  		
  		$scope.back = function() {
  			$location.path('registration/'+$scope.idUser);
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
   			$scope.idUser = $routeParams.idUser;
   			$http.get("mastermenu/v1/house").success(
 				function(data) {
 					$scope.houses = data;
 				});
   		} 
   		
   		$scope.actionHouse = function(house) {
   			$scope.idHouse = house.id;
   			$location.path('houseResource/'+ $scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.callRegistration = function() {
   			$location.path('registration/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		} 
   		
   		$scope.actionDrink = function() {
   			$location.path('bebidas/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.actionFood = function() {
   			$location.path('comidas/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.back = function() {
   			$location.path('houseResource/'+$scope.idHouse+'/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			
  			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.house = data;
  	 		});
  		} 
   		
   		$scope.actionMenu = function() {
   			$location.path('menu/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.actionShoppingList = function() {
   			$location.path('listaDePedidos/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.actionReserveDiningTable = function() {
   			$location.path('reserveDiningTable/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.back = function() {
   			$location.path('panel/'+$scope.idUser);
   		}
   		
   		$scope.init();
   	} ]);

mastermenuControllers.controller('SolicitationFoodCtrl', [
   	'$scope',
   	'$http',
   	'$location',
   	'$routeParams',
   	'$window',
   	'$interval',
   	'$route',
   	function($scope, $http, $location, $routeParams, $window, $interval, $route) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			$scope.idUser = $routeParams.idUser;
  			
  			$http.get("mastermenu/v1/solicitationByIdCategoryAndNotClosed/"+$scope.idHouse+"/2").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
   		}
   		
   		$scope.closeSolicitation = function(solicitation) {
   			solicitation.status = "ENCERRADO";
   			$http.put("mastermenu/v1/solicitation/"+solicitation.id, solicitation).success(
   					function(data) {});
   			alert("Encerrado com sucesso!");
   			$scope.init();
   			$route.reload();
   		}
   		
   		$scope.customStyle = {};
   		$interval(function (){
   			$scope.checkDate();
   		}, 30000);
   		
   		$scope.checkDate = function() {
   			$http.get("mastermenu/v1/solicitationByIdCategoryAndNotClosed/"+$scope.idHouse+"/2").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 		});
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
  			$scope.idUser = $routeParams.idUser;
  			
  			$http.get("mastermenu/v1/solicitationByIdCategoryAndNotClosed/"+$scope.idHouse+"/1").success(
  	 				function(data) {
  	 					$scope.solicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
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
   	'$interval',
   	function($scope, $http, $location, $routeParams, $interval) {
   		$scope.init = function() {
  			$scope.idHouse = $routeParams.idHouse;
  			$scope.idUser = $routeParams.idUser;
  			
  			$http.get("mastermenu/v1/closedSolicitations/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.closedSolicitations = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
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
  			$scope.idUser = $routeParams.idUser;
  			
  			$http.get("mastermenu/v1/commands/"+$scope.idHouse).success(
  	 				function(data) {
  	 					$scope.commands = data;
  	 		});
  		} 
   		
   		
   		$scope.back = function() {
   			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
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

mastermenuControllers.controller('DiningTableCtrl', [
 	'$scope',
 	'$http',
 	'$location',
 	'$routeParams',
 	'$route',
 	function($scope, $http, $location, $routeParams, $route) {
 		$scope.button = 'Criar';
 		$scope.init = function() {
			$scope.idHouse = $routeParams.idHouse;
			$scope.idUser = $routeParams.idUser;
			
			$http.get("mastermenu/v1/diningTable/"+$scope.idHouse+"/house").success(
	 				function(data) {
	 					$scope.diningTables = data;
	 		});
			
			$http.get("mastermenu/v1/house/"+$scope.idHouse).success(
	 				function(data) {
	 					$scope.house = data;
	 				});
		}
 		
 		$scope.checkReserve = function(reserve) {
 			if(reserve) {
 				return 'SIM';
 			} else {
 				return 'NÃO';
 			}
 		}
 		
 		$scope.edit = function(dts) {
 			delete $scope.dt;
 			$scope.dt = dts;
 			$scope.button = 'Editar';
 		}
 		
 		$scope.back = function() {
 			$location.path('house/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		
 		$scope.addDiningTable = function(dt) {
 			if($scope.button === 'Criar') {
	 			$scope.dt = {
	 					"id":null,
	 					"number":dt.number,
	 					"idHouse":$scope.idHouse,
	 					"idClient":$scope.idUser,
	 					"reserved":null
	 			}
	 			$http.post("mastermenu/v1/diningTable", $scope.dt).success(
	 					function(data) {
	 						alert(data);
	 						$route.reload();
				});
 			} else {
 				$http.put("mastermenu/v1/diningTable/"+$scope.dt.id, $scope.dt).success(
 						function(data) {
 							alert(data);
 							$route.reload();
 						});
 			}
 		}
 		
 		$scope.init();
 	} ]);

mastermenuControllers.controller('ReserveDiningTable', [
 	'$scope',
 	'$http',
 	'$location',
 	'$routeParams',
 	'$window',
 	'$interval',
 	'$route',
 	function($scope, $http, $location, $routeParams, $window, $interval, $route) {
 		$scope.init = function() {
			$scope.idHouse = $routeParams.idHouse;
			$scope.idUser = $routeParams.idUser;
			
			$http.get("mastermenu/v1//diningTable/"+$scope.idHouse+"/house").success(
	 				function(data) {
	 					$scope.diningTables = data;
	 					for (var i = 0; i < $scope.diningTables.length; i++) { 
	 						if($scope.diningTables[i].reserved === true) {
	 		  					$scope.diningTables[i].reserved = 'RESERVADO';
	 		  				}
	 		  			}
	 		});
		} 
 		
 		$scope.reserveDiningTable = function(reserve) {
 			var month = (reserve.date.getMonth() + 1).toString();
 			var day = reserve.date.getDate().toString();
 			var hours = reserve.date.getHours().toString();
 			var minutes = reserve.date.getMinutes().toString();
 			
 			reserve.reserved = true;
 			reserve.idClient = parseInt($scope.idUser);
 			if(month.length === 1) {
 				month = "0"+month;
 			} 
 			if(day.length === 1) {
 				day = "0"+day;
 			} 
 			if(hours.length === 1) {
 				hours = "0"+hours;
 			} 
 			if(minutes.length === 1) {
 				minutes = "0"+minutes;
 			}
 			reserve.date = reserve.date.getFullYear()+"-"+month+"-"+day+"T"+hours+":"+minutes;
 			$http.put("mastermenu/v1/diningTable/"+reserve.id, reserve).success(
 					function(data) {
 						$scope.init();
 					});
 		}
 		
 		$scope.cancelReserve = function(reserve) {
 			if(reserve.reserved) {
 				reserve.reserved = false;
 				reserve.time = "";
 				reserve.date = "";
 				reserve.peopleNumber = 0;
 				reserve.idClient = 0;
 				$http.put("mastermenu/v1/diningTable/"+reserve.id, reserve).success(
 	 					function(data) {
 	 					});
 				delete reserve;
 				$route.reload();
 			} else {
 				delete reserve;
 			}
 			
 		}
 		
 		$scope.actionReserve = function(reserve) {
 			$scope.reserve = reserve;
 			document.getElementById('reserveDate').value = reserve.date;
 		}
 		
 		$scope.back = function() {
 			$location.path('houseResource/'+$scope.idHouse+'/'+$scope.idUser);
 		}
 		 		
 		$scope.init();
 	} ]);

mastermenuControllers.run(['$rootScope', '$location',
                           function ($rootScope, $location) {
	 
	  //Rotas que necessitam do login
	  var rotasBloqueadasUsuariosNaoLogados = ['/panel', '/registration', '/bebidas', '/comidas', '/houseResource', '/listaDePedidos', '/main', '/menu', '/panel',
	                                           	'/category', '/closedSolicitations', '/commands', '/createHouse', '/house', '/modalUpdate', '/officialPanel', '/product', '/registration', 
	                                           		'/solicitationDrink', '/solicitationFood', '/updateHouse', '/updateProduct'];
	  $rootScope.$on('$locationChangeStart', function () { //iremos chamar essa função sempre que o endereço for alterado
	 
	      /*  podemos inserir a logica que quisermos para dar ou não permissão ao usuário.
	          Neste caso, vamos usar uma lógica simples. Iremos analisar se o link que o usuário está tentando acessar (location.path())
	          está no Array (rotasBloqueadasUsuariosNaoLogados) caso o usuário não esteja logado. Se o usuário estiver logado, iremos
	          validar se ele possui permissão para acessar os links no Array de strings 'rotasBloqueadasUsuariosComuns'
	      */
		  var path = $location.path();
		  path = path.substring(1, path.length);
		  console.log("path: " + path.substring(0, path.indexOf("/")));
	      if($rootScope.userLogged === undefined && rotasBloqueadasUsuariosNaoLogados.indexOf(path.substring(0, path.indexOf("/")))){
	          $location.path('/main');
	 
	      }/*else
	          if($rootScope.usuarioLogado != null &&
	               rotasBloqueadasUsuariosComuns.indexOf($location.path()) != -1 &&
	               $rootScope.usuarioLogado.admin == false){
	          $location.path('/acessoNegado')
	      }*/
	  });
	}]);

