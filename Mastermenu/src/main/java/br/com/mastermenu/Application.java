package br.com.mastermenu;

import static spark.Spark.*;
import java.util.Optional;
import com.google.gson.Gson;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.solicitation.service.SolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationServiceImp;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";

	public static void main(String[] args) {
		SolicitationService solicitationService = new SolicitationServiceImp();
		Gson gson = new Gson();
		get(mastermenu + "/solicitation", (req, res) -> {
			String solicitations = gson.toJson(solicitationService.list(Optional.empty()));
			return  solicitations;
		});
		
		get(mastermenu + "/solicitation/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			if (solicitationService.findById(id) != null) {
				Solicitation solicitation = solicitationService.findById(id);
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
	}
	
	private static Solicitation parseSolicitationFromBody(String body) {
		//log.info(body);
		Gson gson = new Gson();
		return gson.fromJson(body, Solicitation.class);
	}

}
