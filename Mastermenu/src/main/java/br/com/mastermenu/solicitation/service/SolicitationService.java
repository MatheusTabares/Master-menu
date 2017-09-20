package br.com.mastermenu.solicitation.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.solicitation.dao.ISolicitationDAO;
import br.com.mastermenu.solicitation.dao.SolicitationDAO;
import br.com.mastermenu.solicitation.model.Solicitation;

public class SolicitationService implements ISolicitationService {

	private ISolicitationDAO solicitationDAO = new SolicitationDAO();
	@Override
	public void create(Solicitation solicitation) {
		solicitationDAO.create(solicitation);
	}

	@Override
	public List<Solicitation> read() {
		return solicitationDAO.read();
	}

	@Override
	public void update(Solicitation solicitationUpdated) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Solicitation solicitation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Solicitation> readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
