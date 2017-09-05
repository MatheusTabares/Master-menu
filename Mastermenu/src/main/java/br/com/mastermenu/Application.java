package br.com.mastermenu;

import static spark.Spark.*;
import java.util.Optional;
import com.google.gson.Gson;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.composition.service.CompositionService;
import br.com.mastermenu.composition.service.ICompositionService;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";
	
	public static void main(String[] args) throws Exception {
		IProductService productService = new ProductService();
		ICompositionService compositionService = new CompositionService();
		
		Gson gson = new Gson();
		
		/**
		 * TODO: CRUD PRODUCT
		 */
		
		get(mastermenu + "/product/:filter", (req, res) -> {
			Optional<String> filter = Optional.ofNullable(req.params(":filter"));
			String products = gson.toJson(productService.read(filter));
			return  products;
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
		
		post(mastermenu + "/product", (req, res) -> {
			String body = req.body();
			Product product = productService.create(parseProductFromBody(body));
			if(product != null) {
				return gson.toJson(product);
			}
			res.status(404);
			return "Produto não inserido!";
		});
		
		put(mastermenu + "/product/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Product> product = productService.readById(id);
			if (product.isPresent()) {
				String body = req.body();
				Product productUpdated = productService.update(parseProductFromBody(body));
				return gson.toJson(productUpdated);
			} else {
				res.status(404);
				return "Produto não encontrado para atualizar!";
			}
		});
		
		delete(mastermenu + "/product", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			if (productService.delete(id)) {
				return true;
			} else {
				res.status(404);
				return false;
			}
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
