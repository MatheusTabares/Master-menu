package br.com.mastermenu;

import static spark.Spark.*;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.google.gson.Gson;

import br.com.mastermenu.categoria.model.Categoria;
import br.com.mastermenu.category.service.CategoryService;
import br.com.mastermenu.category.service.ICategoryService;
import br.com.mastermenu.commands.model.Commands;
import br.com.mastermenu.commands.service.CommandsService;
import br.com.mastermenu.commands.service.ICommandsService;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.composition.service.CompositionService;
import br.com.mastermenu.composition.service.ICompositionService;
import br.com.mastermenu.house.service.HouseService;
import br.com.mastermenu.house.service.IHouseService;
import br.com.mastermenu.model.House;
import br.com.mastermenu.passwordSecurity.model.PasswordSecurity;
import br.com.mastermenu.passwordSecurity.service.IPasswordSecurityService;
import br.com.mastermenu.passwordSecurity.service.PasswordSecurityService;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.solicitation.service.ISolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationService;
import br.com.mastermenu.user.model.User;
import br.com.mastermenu.user.service.IUserService;
import br.com.mastermenu.user.service.UserService;
import br.com.mastermenu.util.HashUtil;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";
	private static final List<Solicitation> teste = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		IProductService productService = new ProductService();
		ICompositionService compositionService = new CompositionService();
		ISolicitationService solicitationService = new SolicitationService();
		ICategoryService categoryService = new CategoryService();
		IHouseService houseService = new HouseService();
		ICommandsService commandsService = new CommandsService();
		IUserService userService = new UserService();
		IPasswordSecurityService psService = new PasswordSecurityService();
		
		Gson gson = new Gson();
		
		staticFileLocation("/public");
		//URL DE ACESSO: http://localhost:4567/index.html
		/**
		 * TODO: CRUD USER
		 */
		
		post(mastermenu + "/user", (req, res) -> {
			String body = req.body();
			User u = parseUserFromBody(body);
			User uReturn = userService.findByEmail(u.getEmail());
			if(uReturn != null) {
				return "E-mail já cadastrado!";
			}
			if(validationUser(u, Optional.empty()) == true) {
				PasswordSecurity ps = new PasswordSecurity();
				u.setPassword(HashUtil.generateHash(u.getPassword(), ps.getSALT()));
				userService.create(u);
				ps.setUser(u);
				psService.create(ps);
				
				return gson.toJson("Usuário - " + u.getName() +", inserido com sucesso!");
			}
			res.status(404);
			return "Problemas ao inserir usuário!";
		});
		
		post(mastermenu + "/authenticate", (req, res) -> {
			User u = parseUserFromBody(req.body());
			User uReturn = userService.findByEmail(u.getEmail());
			String password = "";
			if(uReturn != null) {
				Optional<PasswordSecurity> ps;
				ps = psService.readByIdUser(uReturn.getId());
				if(ps.isPresent()) {
					password = HashUtil.generateHash(u.getPassword(), ps.get().getSALT());
				}
				if(password.equals(uReturn.getPassword())) {
					return gson.toJson(uReturn);
				} else {
					return "Senha inválida";
				}
			}
			return "Email inválido";
			
		});
		
				/**
		 * TODO: CRUD COMMANDS
		 */
		
		post(mastermenu + "/commands", (req, res) -> {
			String body = req.body();
			Commands c = parseCommandsFromBody(body); 
			if(c != null) {
				commandsService.create(c);
				return gson.toJson("Comanda adicionada a lista!");
			}
			res.status(404);
			return "Comanda não inserida!";
		});
		
		put(mastermenu + "/commands/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Commands> c = commandsService.readById(id);
			if (c.isPresent()) {
				String body = req.body();
				commandsService.update(parseCommandsFromBody(body));
				return gson.toJson("Comanda atualizada com sucesso!");
			} else {
				res.status(404);
				return "Erro ao atualizar uma Comanda!";
			}
		});
		
		get(mastermenu + "/commands/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			List<Commands> c = commandsService.read(id);
			if (c.size() != 0) {
				return gson.toJson(c);
			} else {
				res.status(404);
				return "Comanda não encontrada!";
			}
		});
		
		delete(mastermenu + "/commands", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<Commands> c = commandsService.readById(id);
			if(c.isPresent()) {
				return commandsService.delete(c.get());
			} else {
				res.status(404);
				return false;
			}
		});
		/**
		 * TODO: CRUD PRODUCT
		 */
		
		post(mastermenu + "/house", (req, res) -> {
			String body = req.body();
			House houseValidated = parseHouseFromBody(body);
			if(validationHouse(houseValidated, Optional.empty()) == true) {
				houseService.create(houseValidated);
				return gson.toJson("Estabelecimento - " + houseValidated.getName() +", inserido com sucesso!");
			}
			res.status(404);
			return "Estabelecimento não inserido!";
		});
		
		get(mastermenu + "/house/:idUser/user", (req, res) -> {
			int idUser = Integer.parseInt(req.params(":idUser"));
			String houses = gson.toJson(houseService.readByIdUser(idUser));
			if(!houses.trim().equals(""))
				return  houses;
			else
				return "Sem estabaelecimentos!";
		});
		
		put(mastermenu + "/house/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<House> house = houseService.readById(id);
			if (house.isPresent()) {
				String body = req.body();
				houseService.update(parseHouseFromBody(body));
				return gson.toJson("Estabelecimento atualizado com sucesso!");
			} else {
				res.status(404);
				return "Erro ao atualizar um Estabalecimento!";
			}
		});
		
		delete(mastermenu + "/house", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<House> houseDeleted = houseService.readById(id);
			if(houseDeleted.isPresent()) {
				houseService.delete(houseDeleted.get());
				return houseDeleted.get().getName() + " excluído com sucesso!";
			} else {
				res.status(404);
				return false;
			}
		});
		
		get(mastermenu + "/house/:idHouse", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			Optional<House> house = houseService.readById(idHouse);
			if (house.isPresent()) {
				return gson.toJson(house.get());
			} else {
				res.status(404);
				return "Estabelecimento não encontrado!";
			}
		});
		
		get(mastermenu + "/house", (req, res) -> {
			List<House> houses = houseService.read();
			if (houses.size() != 0) {
				return gson.toJson(houses);
			} else {
				res.status(404);
				return "Estabelecimento não encontrado!";
			}
		});
		
		/**
		 * TODO: CRUD CATEGORY
		 */
		
		post(mastermenu + "/category", (req, res) -> {
			String body = req.body();
			Categoria category = parseCategoryFromBody(body); 
			if(category != null) {
				categoryService.create(category);
				return gson.toJson("Categoria - " + category.getNome() +", adicionada a lista!");
			}
			res.status(404);
			return "Categoria não inserido!";
		});
		
		get(mastermenu + "/category", (req, res) -> {
			String categories = gson.toJson(categoryService.read());
			if(!categories.trim().equals(""))
				return categories;
			else
				return "Sem produtos!";
		});
		
		put(mastermenu + "/category/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Categoria> categoria = categoryService.readById(id);
			if (categoria.isPresent()) {
				String body = req.body();
				categoryService.update(parseCategoryFromBody(body));
				return gson.toJson("Categoria atualizada com sucesso!");
			} else {
				res.status(404);
				return "Erro ao atualizar uma Categoria!";
			}
		});
		
		get(mastermenu + "/category/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Categoria> category = categoryService.readById(id);
			if (category.isPresent()) {
				return gson.toJson(category.get());
			} else {
				res.status(404);
				return "Categoria não encontrada!";
			}
		});
		
		delete(mastermenu + "/category", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<Categoria> categoryDeleted = categoryService.readById(id);
			if(categoryDeleted.isPresent()) {
				return categoryService.delete(categoryDeleted.get());
			} else {
				res.status(404);
				return false;
			}
		});
		
		/**
		 * TODO: CRUD SOLICITATION
		 */
		
		post(mastermenu + "/solicitationTemp", (req, res) -> {
			String body = req.body();
			Solicitation solicitation = parseSolicitationFromBody(body); 
			if(solicitation != null) {
				teste.add(solicitation);
				return gson.toJson("Pedido - " + solicitation.getProduct().getName() +", adicionado a lista!");
			}
			res.status(404);
			return "Pedido não inserido!";
		});
		
		get(mastermenu + "/solicitationTemp/:idHouse/:idClient", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			int idClient = Integer.parseInt(req.params(":idClient"));
			List<Solicitation> mySolicitations = new ArrayList<>();
			for(Solicitation sol : teste) {
				if(sol.getHouse().getId() == idHouse && sol.getIdClient() == idClient) {
					mySolicitations.add(sol);
				}
			}
			if(mySolicitations.size() != 0) {
				return gson.toJson(mySolicitations);
			} else {
				return "Sem produtos!";
			}
		});
		
		post(mastermenu + "/solicitation", (req, res) -> {
			String body = req.body();
			Solicitation solicitation = parseSolicitationFromBody(body); 
			if(solicitation != null) {
				if(solicitation.getProduct().getCategoria().getId() == 1) {
					solicitation.setTypeCategory("1");
				} else {
					solicitation.setTypeCategory("2");
				}
				solicitation.setCurrentDate(LocalDateTime.now());
				solicitation.setEstimatedDate(solicitation.getCurrentDate());
				estimateTime(solicitation);
				solicitationService.create(solicitation);
				return gson.toJson("Pedido - " + solicitation.getProduct().getName() +", adicionado a lista!");
			}
			res.status(404);
			return "Pedido não inserido!";
		});
		
		get(mastermenu + "/solicitation/:idHouse", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			List<Solicitation> solicitations = solicitationService.readByIdHouse(idHouse);
			List<Solicitation> solList = new ArrayList<>();
			if(solicitations.size() != 0) {
				for(Solicitation s : solicitations) {
					if(s.getCurrentDate() != null && s.getEstimatedDate() != null) {
						solList.add(s);
					}
				}
				return gson.toJson(solList);
			} else {
				return "Sem produtos!";
			}
		});
		get(mastermenu + "/solicitation/:idHouse/house/:idUser/user", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			int idUser = Integer.parseInt(req.params(":idUser"));
			List<Solicitation> solicitations = solicitationService.readByIdHouseAndIdUser(idHouse, idUser);
			List<Solicitation> solList = new ArrayList<>();
			if(solicitations.size() != 0) {
				for(Solicitation s : solicitations) {
					if(s.getCurrentDate() != null && s.getEstimatedDate() != null) {
						solList.add(s);
					}
				}
				return gson.toJson(solList);
			} else {
				return "Sem produtos!";
			}
		});
		
		get(mastermenu + "/closedSolicitations/:idHouse", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			String solicitations = gson.toJson(solicitationService.readByIdHouseAndStatus(idHouse, "ENCERRADO"));
			if(!solicitations.trim().equals(""))
				return solicitations;
			else
				return "Erro ao encerrar o pedido!";
		});
		get(mastermenu + "/solicitationByIdCategoryAndNotClosed/:idHouse/:idCategoria", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			String idCategory = req.params(":idCategoria");
			String solicitations = gson.toJson(solicitationService.readByIdHouseAndIdCategory(idHouse, idCategory));
			if(!solicitations.trim().equals(""))
				return solicitations;
			else
				return "Sem produtos!";
		});
		
		put(mastermenu + "/solicitation/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Solicitation> solicitation = solicitationService.readById(id);
			if (solicitation.isPresent()) {
				String body = req.body();	
				Solicitation s = parseSolicitationFromBody(body);
				s.setFinalized(LocalDateTime.now());
				s.setProductionTime(ChronoUnit.MINUTES.between(s.getCurrentDate(), s.getFinalized()));
				solicitationService.update(s);
				
				return gson.toJson("Pedido Encerrado!");
			} else {
				res.status(404);
				return "Erro ao encerrar o Pedido!";
			}
		});
		
		delete(mastermenu + "/solicitationsTemp", (req, res) -> {
			int idClient = Integer.parseInt(req.queryParams("idClient"));
			for(int i = 0; i < teste.size(); i++) {
				if(teste.get(i).getIdClient() == idClient) {
					teste.remove(i);
					i--;
				}
			}
			return "Pedidos retirados da lista temporária com sucesso!";
			
		});
		/**
		 * TODO: CRUD PRODUCT
		 */
		
		post(mastermenu + "/product", (req, res) -> {
			String body = req.body();
			Product productValidated = parseProductFromBody(body); 
			if(validationProduct(productValidated, Optional.empty()) == true) {
				productService.create(productValidated);
				return gson.toJson("Produto - " + productValidated.getName() +", inserido com sucesso!");
			}
			res.status(404);
			return "Produto não inserido!";
		});
		
		get(mastermenu + "/product/:idHouse", (req, res) -> {
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			String products = gson.toJson(productService.read(idHouse));
			if(!products.trim().equals(""))
				return  products;
			else
				return "Sem produtos!";
		});
		
		put(mastermenu + "/product/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Product> product = productService.readById(id);
			if (product.isPresent()) {
				String body = req.body();
				productService.update(parseProductFromBody(body));
				return gson.toJson("Produto atualizado com sucesso!");
			} else {
				res.status(404);
				return "Erro ao atualizar um Produto!";
			}
		});
		
		delete(mastermenu + "/product", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<Product> productDeleted = productService.readById(id);
			if(productDeleted.isPresent()) {
				productService.delete(productDeleted.get());
				return productDeleted.get().getName() + " excluído com sucesso!";
			} else {
				res.status(404);
				return false;
			}
		});
		
		get(mastermenu + "/productById/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Product> product = productService.readById(id);
			if (product.isPresent()) {
				return gson.toJson(product.get());
			} else {
				res.status(404);
				return "Produto não encontrado!";
			}
		});
		
		get(mastermenu + "/produtoPorCategoria/:idCategoria/:idHouse", (req, res) -> {
			int idCategoria = Integer.parseInt(req.params(":idCategoria"));
			int idHouse = Integer.parseInt(req.params(":idHouse"));
			String products = gson.toJson(productService.encontrarPorIdCategoria(idCategoria, idHouse));
			if(!products.trim().equals(""))
				return  products;
			else
				return "Sem produtos!";
		});
		/**
		 * TODO: CRUD COMPOSITION
		 */
		post(mastermenu + "/composition", (req, res) -> {
			String body = req.body();
			Composition compositionValidated = parseCompositionFromBody(body); 
			if(validationComposition(compositionValidated, Optional.empty()) == true) {
				compositionService.create(compositionValidated);
				return gson.toJson("Composição - " + compositionValidated.getName() +", inserido com sucesso!");
			}
			res.status(404);
			return "Composição não inserido!";
		});
		
		get(mastermenu + "/composition", (req, res) -> {
			String compositions = gson.toJson(compositionService.read());
			if(!compositions.trim().equals(""))
				return  compositions;
			else
				return "Sem composição!";
		});
		
		put(mastermenu + "/composition", (req, res) -> {
			String body = req.body();
			Composition compositionValidated = parseCompositionFromBody(body);
			if (validationComposition(compositionValidated, Optional.of("update")) == true) {
				compositionService.update(compositionValidated);
				return gson.toJson("Composição - " + compositionValidated.getName() + ", atualizada com sucesso!");
			} else {
				res.status(404);
				return "Composição não encontrada para atualizar!";
			}
		});
		
		delete(mastermenu + "/composition", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<Composition> compostionDeleted = compositionService.readById(id);
			if(compostionDeleted.isPresent()) {
				return compositionService.delete(compostionDeleted.get());
			} else {
				res.status(404);
				return false;
			}
		});
		
		get(mastermenu + "/composition/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Composition> composition = compositionService.readById(id);
			if (composition.isPresent()) {
				return gson.toJson(composition);
			} else {
				res.status(404);
				return "Composição não encontrada!";
			}
		});
	}
	
	private static boolean validationComposition(Composition composition, Optional<String> toUpdate) {
		if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
			if(composition.getId() != null && composition.getName() != null && !composition.getName().trim().equals("")) {
				return true;
			}
		} else {
			if(composition.getId() == null && composition.getName() != null && !composition.getName().trim().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	private static void estimateTime(Solicitation sol) {
		int time = 0;
		if(sol.getTypeCategory().equals("1"))
			time = 4;
		else 
			time = 7;
		List<Solicitation> solicitations = new ArrayList<>();
		ISolicitationService solService = new SolicitationService();
		solicitations = solService.readByIdHouseAndIdCategory(
				sol.getHouse().getId(), sol.getProduct().getCategoria().getId().toString());
		if(solicitations.size() == 0) {
			sol.setEstimatedDate(sol.getCurrentDate().plusMinutes(time));
		} else if(solicitations.size() >= 3) {
			sol.setEstimatedDate(sol.getCurrentDate().plusMinutes(time * 4));
		}
		else {
			sol.setEstimatedDate(sol.getCurrentDate().plusMinutes(time * (solicitations.size() + 1)));
		}
	}
	
	private static boolean validationCategory(Categoria category, Optional<String> toUpdate) {
		return true;
	}
	
	private static boolean validationUser(User u, Optional<String> toUpdate) {
		if(u.getEmail() != null && !u.getEmail().trim().equals("") 
					&& u.getPassword() != null && !u.getPassword().trim().equals("")) {
			if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
				if(u.getId() != null) {
					return true;
				}
			} else {
				if(u.getId() == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean validationHouse(House house, Optional<String> toUpdate) {
		if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
			if(house.getId() != null && house.getName() != null && !house.getName().trim().equals("")) {
				return true;
			}
		} else {
			if(house.getId() == null && house.getName() != null && !house.getName().trim().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean validationProduct(Product product, Optional<String> toUpdate) {
		if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
			if(product.getId() != null && product.getName() != null && !product.getName().trim().equals("")
					&& product.getPrice() != 0.0 && product.getCompositions().size() != 0
					&& product.getOptionsComposition().size() != 0) {
				return true;
			}
		} else {
			if(product.getId() == null && product.getName() != null && !product.getName().trim().equals("")
					&& product.getPrice() != 0.0 && product.getCompositions() != null && product.getCompositions().size() != 0) {
				return true;
			}
		}
		return false;
	}
	
	private static Commands parseCommandsFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Commands.class);
	}
	
	private static Categoria parseCategoryFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Categoria.class);
	}

	private static Solicitation parseSolicitationFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Solicitation.class);
	}
	
	private static User parseUserFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, User.class);
	}
	
	private static House parseHouseFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, House.class);
	}
	
	private static Product parseProductFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Product.class);
	}
	
	private static Composition parseCompositionFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Composition.class);
	}
	
}
