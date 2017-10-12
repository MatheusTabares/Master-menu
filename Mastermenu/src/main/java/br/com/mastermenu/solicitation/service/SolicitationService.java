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
	public List<Solicitation> readByIdHouse(int idHouse) {
		return solicitationDAO.readByIdHouse(idHouse);
	}

	@Override
	public List<Solicitation> readByIdHouseAndIdCategory(int idHouse, String idCategory) {
		return solicitationDAO.readByIdHouseAndIdCategory(idHouse, idCategory);
	}
	
	@Override
	public void update(Solicitation solicitationUpdated) {
		solicitationDAO.update(solicitationUpdated);
	}

	@Override
	public boolean delete(Solicitation solicitation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Solicitation> readById(int id) {
		return solicitationDAO.readById(id);
	}

	@Override
	public List<Solicitation> readByIdHouseAndStatus(int idHouse, String status) {
		return solicitationDAO.readByIdHouseAndStatus(idHouse, status);
	}

}
