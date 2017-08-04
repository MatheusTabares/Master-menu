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
	public Optional<Solicitation> findById(int id) {
		return solicitationDAO.findById(id);   
	}

	@Override
	public Solicitation insert(Solicitation solicitation) {
		if(validateSolicitation(solicitation)) {
			return solicitationDAO.insert(solicitation);
		}
		return null;
	}

	@Override
	public Solicitation update(int indexSolicitation, Solicitation solicitation) {
		if(validateSolicitation(solicitation)) {
			return solicitationDAO.update(indexSolicitation, solicitation);
		}
		return null;
	}
	
	@Override
	public boolean delete(int id) {
		Optional<Solicitation> solicitation = solicitationDAO.findById(id);
		if(solicitation.isPresent()) {
			return solicitationDAO.delete(id);
		}
		return false;
	}
	
	private boolean validateSolicitation(Solicitation solicitation) {
		if(!solicitation.getTitle().isEmpty() && solicitation.getTitle().trim().equals("")) {
			return false;
		}
		return true;
	}
}
