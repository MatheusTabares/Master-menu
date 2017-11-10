package br.com.mastermenu.solicitation.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.solicitation.model.Solicitation;

public interface ISolicitationService {
	public void create(Solicitation solicitation);
	
	public List<Solicitation> readByIdHouse(int idHouse);
	
	public List<Solicitation> readByIdHouseAndIdCategory(int idHouse, String idCategory);
	
	public List<Solicitation> readByIdHouseAndIdUser(int idHouse, int idUser);
	
	public List<Solicitation> readByIdHouseAndStatus(int idHouse, String status);
	
	public void update(Solicitation solicitationUpdated);
	
	public boolean delete(Solicitation solicitation);
	
	public Optional<Solicitation> readById(int id);
}
