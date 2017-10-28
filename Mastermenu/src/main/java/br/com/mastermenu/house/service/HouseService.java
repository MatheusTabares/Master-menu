package br.com.mastermenu.house.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.house.dao.HouseDAO;
import br.com.mastermenu.house.dao.IHouseDAO;
import br.com.mastermenu.model.House;
import br.com.mastermenu.product.dao.IProductDAO;
import br.com.mastermenu.product.dao.ProductDAO;
import br.com.mastermenu.product.model.Product;

public class HouseService implements IHouseService {

	private IHouseDAO houseDAO = new HouseDAO();
	@Override
	public void create(House house) {
		houseDAO.create(house);
	}

	@Override
	public List<House> read() {
		return houseDAO.read();
	}

	@Override
	public void update(House houseUpdated) {
		houseDAO.update(houseUpdated);
	}

	@Override
	public boolean delete(House houseDeleted) {
		return houseDAO.delete(houseDeleted);
	}
	
	@Override
	public Optional<House> readById(int id) {
		return houseDAO.readById(id);
	}

	@Override
	public List<House> readByIdUser(int idUser) {
		return houseDAO.readByIdUser(idUser);
	}

}
