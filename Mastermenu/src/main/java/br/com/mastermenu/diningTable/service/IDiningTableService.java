package br.com.mastermenu.diningTable.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.diningTable.model.DiningTable;

public interface IDiningTableService {
	public void create(DiningTable dt);
	
	public Optional<DiningTable> readById(int id);	
	
	public List<DiningTable> readByIdHouse(int idHouse);
	
	public List<DiningTable> readByIdHouseAndIdUser(int idHouse, int idUser);
	
	public List<DiningTable> readAllNotReservedByIdHouse(int idHouse);
	
	public void update(DiningTable dt);
	
	public boolean delete(DiningTable dt);
	
}
