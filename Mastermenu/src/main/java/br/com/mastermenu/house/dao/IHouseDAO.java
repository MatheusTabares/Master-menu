package br.com.mastermenu.house.dao;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.model.House;

public interface IHouseDAO {
	public void create(House house);
	
	public List<House> read();
	
	public void update(House houseUpdated);
	
	public boolean delete(House house);
	
	public Optional<House> readById(int id);
	
}
