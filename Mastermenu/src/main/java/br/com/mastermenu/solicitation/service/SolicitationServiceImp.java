package br.com.mastermenu.solicitation.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.solicitation.dao.SolicitationDAOInMemory;
import br.com.mastermenu.solicitation.model.Solicitation;

public class SolicitationServiceImp implements SolicitationService {
	
	private SolicitationDAOInMemory solicitationDAOInMemory;
	@Override
	public List<Solicitation> list(Optional<String> filter) {
		if(filter.isPresent()) {
			return null;
		}
		return solicitationDAOInMemory.list();
	}
	

}
