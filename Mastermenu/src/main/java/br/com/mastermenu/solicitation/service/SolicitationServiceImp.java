package br.com.mastermenu.solicitation.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.solicitation.dao.SolicitationDAO;
import br.com.mastermenu.solicitation.dao.SolicitationDAOInMemory;
import br.com.mastermenu.solicitation.model.Solicitation;

public class SolicitationServiceImp implements SolicitationService {
	
	private SolicitationDAO solicitationDAO = new SolicitationDAOInMemory();
	
	@Override
	public List<Solicitation> list(Optional<String> filter) {
		if(filter.isPresent()) {
			return null;
		}
		return solicitationDAO.list();
	}

	@Override
	public Solicitation findById(int id) {
		return solicitationDAO.findById(id);   
	}

	@Override
	public Solicitation insert(Solicitation solicitation) {
		if(validateSolicitation(solicitation)) {
			return solicitationDAO.insert(solicitation);
		}
		return null;
	}
	
	private boolean validateSolicitation(Solicitation solicitation) {
		if(solicitation.getTitle().equals("") || solicitation.getTitle().isEmpty()) {
			return false;
		}
		return true;
	}
}
