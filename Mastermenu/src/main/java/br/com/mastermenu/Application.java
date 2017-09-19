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
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.composition.service.CompositionService;
import br.com.mastermenu.composition.service.ICompositionService;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;
import spark.ModelAndView;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";
	
	public static void main(String[] args) throws Exception {
		IProductService productService = new ProductService();
		ICompositionService compositionService = new CompositionService();
		
		Gson gson = new Gson();
		
		staticFileLocation("/public");
		//URL DE ACESSO: http://localhost:4567/index.html
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
				return gson.toJson(product);
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
