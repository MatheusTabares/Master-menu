package br.com.mastermenu;

import static spark.Spark.*;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;
import com.google.gson.Gson;

import br.com.mastermenu.categoria.model.Categoria;
import br.com.mastermenu.category.service.CategoryService;
import br.com.mastermenu.category.service.ICategoryService;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.composition.service.CompositionService;
import br.com.mastermenu.composition.service.ICompositionService;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.solicitation.service.ISolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationService;
import spark.ModelAndView;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";
	
	public static void main(String[] args) throws Exception {
		IProductService productService = new ProductService();
		ICompositionService compositionService = new CompositionService();
		ISolicitationService solicitationService = new SolicitationService();
		ICategoryService categoryService = new CategoryService();
		
		Gson gson = new Gson();
		
		staticFileLocation("/public");
		//URL DE ACESSO: http://localhost:4567/index.html
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
		
		post(mastermenu + "/solicitation", (req, res) -> {
			String body = req.body();
			Solicitation solicitation = parseSolicitationFromBody(body); 
			if(solicitation != null) {
				solicitationService.create(solicitation);
				return gson.toJson("Pedido - " + solicitation.getProduct().getName() +", adicionado a lista!");
			}
			res.status(404);
			return "Pedido não inserido!";
		});
		
		get(mastermenu + "/solicitation", (req, res) -> {
			String solicitations = gson.toJson(solicitationService.read());
			if(!solicitations.trim().equals(""))
				return solicitations;
			else
				return "Sem produtos!";
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
		
		get(mastermenu + "/product", (req, res) -> {
			String products = gson.toJson(productService.read());
			if(!products.trim().equals(""))
				return  products;
			else
				return "Sem produtos!";
		});
		
		put(mastermenu + "/product", (req, res) -> {
			String body = req.body();
			Product productValidated = parseProductFromBody(body);
			if (validationProduct(productValidated, Optional.of("update")) == true) {
				productService.update(productValidated);
				return gson.toJson("Produto - " + productValidated.getName() + ", atualizado com sucesso!");
			} else {
				res.status(404);
				return "Produto não encontrado para atualizar!";
			}
		});
		
		delete(mastermenu + "/product", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Optional<Product> productDeleted = productService.readById(id);
			if(productDeleted.isPresent()) {
				return productService.delete(productDeleted.get());
			} else {
				res.status(404);
				return false;
			}
		});
		
		get(mastermenu + "/product/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Product> product = productService.readById(id);
			if (product.isPresent()) {
				return gson.toJson(product.get());
			} else {
				res.status(404);
				return "Produto não encontrado!";
			}
		});
		
		get(mastermenu + "/produtoPorCategoria/:idCategoria", (req, res) -> {
			int idCategoria = Integer.parseInt(req.params(":idCategoria"));
			String products = gson.toJson(productService.encontrarPorIdCategoria(idCategoria));
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
	
	private static boolean validationCategory(Categoria category, Optional<String> toUpdate) {
		return true;
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
					&& product.getPrice() != 0.0 && product.getCompositions().size() != 0
					&& product.getOptionsComposition().size() != 0) {
				return true;
			}
		}
		return false;
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
