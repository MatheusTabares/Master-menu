package br.com.mastermenu;

import static spark.Spark.*;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.composition.service.CompositionService;
import br.com.mastermenu.composition.service.ICompositionService;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.product.service.IProductService;
import br.com.mastermenu.product.service.ProductService;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.solicitation.service.SolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationServiceImp;
import br.com.mastermenu.util.Connection;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";
	
	//private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

	public static void main(String[] args) throws Exception {
		//ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("db_mastermenu");
		
		//EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		/*EntityManager em = Connection.connection();
		Composition c = new Composition();
		c.setName("PRIMEIRO TESTE COMPOSIÇÃO");
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}*/
		
		
		
		SolicitationService solicitationService = new SolicitationServiceImp();
		IProductService productService = new ProductService();
		ICompositionService compositionService = new CompositionService();
		
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
