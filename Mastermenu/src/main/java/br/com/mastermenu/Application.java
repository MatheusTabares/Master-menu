package br.com.mastermenu;

import static spark.Spark.*;
import java.util.Optional;

import br.com.mastermenu.solicitation.service.SolicitationService;
import br.com.mastermenu.solicitation.service.SolicitationServiceImp;

public class Application {
	
	private static final String mastermenu = "mastermenu/v1";

	public static void main(String[] args) {
		SolicitationService solicitationService = new SolicitationServiceImp();
		get("/solicitation", (req, res) -> 
			solicitationService.list(Optional.empty())
		);
		
		get(mastermenu + "/solicitation", (req, res) -> "Hello World");
	}

}
