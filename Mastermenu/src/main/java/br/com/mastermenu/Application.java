package br.com.mastermenu;

import static spark.Spark.*;
import java.util.Optional;
import com.google.gson.Gson;

import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.solicitation.service.SolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationServiceImp;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";

	public static void main(String[] args) {
		SolicitationService solicitationService = new SolicitationServiceImp();
		IProductService productService = new ProductService();
		
		Gson gson = new Gson();
		get(mastermenu + "/solicitation", (req, res) -> {
			String solicitations = gson.toJson(solicitationService.list(Optional.empty()));
			return  solicitations;
		});
		
		get(mastermenu + "/solicitation/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			if (solicitationService.findById(id).isPresent()) {
				Optional<Solicitation> solicitation = solicitationService.findById(id);
				return gson.toJson(solicitation);
			} else {
				res.status(404);
				return "Solicitation not found!";
			}
		});
		
		post(mastermenu + "/solicitation", (req, res) -> {
			String body = req.body();
			Solicitation solicitation = solicitationService.insert(parseSolicitationFromBody(body));
			if(solicitation != null) {
				return gson.toJson(solicitation);
			}
			res.status(404);
			return "Solicitation not inserted!";
		});
		
		put(mastermenu + "/solicitation/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Solicitation> solicitation = solicitationService.findById(id);
			if (solicitation.isPresent()) {
				String body = req.body();
				Solicitation solicitationUpdated = solicitationService.update(id, parseSolicitationFromBody(body));
				return gson.toJson(solicitationUpdated);
			} else {
				res.status(404);
				return "Solicitation not found for update!";
			}
		});
		
		delete(mastermenu + "/solicitation", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			if (solicitationService.delete(id)) {
				return true;
			} else {
				res.status(404);
				return false;
			}
		});
		
		get(mastermenu + "/product", (req, res) -> {
			String products = gson.toJson(productService.read(Optional.empty()));
			return  products;
		});
		
		get(mastermenu + "/product/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			if (productService.readById(id).isPresent()) {
				Optional<Product> product = productService.readById(id);
				return gson.toJson(product);
			} else {
				res.status(404);
				return "Product not found!";
			}
		});
		
		post(mastermenu + "/product", (req, res) -> {
			String body = req.body();
			Product product = productService.create(parseProductFromBody(body));
			if(product != null) {
				return gson.toJson(product);
			}
			res.status(404);
			return "Product not inserted!";
		});
		
		put(mastermenu + "/product/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Optional<Product> product = productService.readById(id);
			if (product.isPresent()) {
				String body = req.body();
				Product productUpdated = productService.update(id, parseProductFromBody(body));
				return gson.toJson(productUpdated);
			} else {
				res.status(404);
				return "Product not found for update!";
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

}
