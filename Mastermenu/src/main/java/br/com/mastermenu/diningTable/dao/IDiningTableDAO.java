package br.com.mastermenu.diningTable.dao;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.diningTable.model.DiningTable;

public interface IDiningTableDAO {
	public void create(DiningTable dt);
	
	public List<DiningTable> readByIdHouse(int idHouse);
	
	public List<DiningTable> readByIdHouseAndIdUser(int idHouse, int idUser);
	
	public List<DiningTable> readAllNotReservedByIdHouse(int idHouse);
	
	public void update(DiningTable dt);
	
	public boolean delete(DiningTable dt);
	
	public Optional<DiningTable> readById(int id);
}
