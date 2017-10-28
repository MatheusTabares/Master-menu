package br.com.mastermenu.house.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.model.House;

public interface IHouseService {
public void create(House house);
	
	public List<House> read();
	
	public void update(House houseUpdated);
	
	public boolean delete(House house);
	
	public Optional<House> readById(int id);
	
	public List<House> readByIdUser(int id);
}
