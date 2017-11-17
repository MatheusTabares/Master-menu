package br.com.mastermenu.diningTable.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.diningTable.dao.DiningTableDAO;
import br.com.mastermenu.diningTable.dao.IDiningTableDAO;
import br.com.mastermenu.diningTable.model.DiningTable;

public class DiningTableService implements IDiningTableService {
	private IDiningTableDAO dtDAO = new DiningTableDAO();

	@Override
	public void create(DiningTable dt) {
		dtDAO.create(dt);
	}

	@Override
	public List<DiningTable> readByIdHouse(int idHouse) {
		return dtDAO.readByIdHouse(idHouse);
	}

	@Override
	public List<DiningTable> readByIdHouseAndIdUser(int idHouse, int idUser) {
		return dtDAO.readByIdHouseAndIdUser(idHouse, idUser);
	}

	@Override
	public List<DiningTable> readAllNotReservedByIdHouse(int idHouse) {
		return dtDAO.readAllNotReservedByIdHouse(idHouse);
	}

	@Override
	public void update(DiningTable dt) {
		dtDAO.update(dt);
	}

	@Override
	public boolean delete(DiningTable dt) {
		return dtDAO.delete(dt);
	}

	@Override
	public Optional<DiningTable> readById(int id) {
		return dtDAO.readById(id);
	}

}
