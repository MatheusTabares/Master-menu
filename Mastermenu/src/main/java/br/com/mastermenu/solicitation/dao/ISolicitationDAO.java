package br.com.mastermenu.solicitation.dao;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.solicitation.model.Solicitation;

public interface ISolicitationDAO {
	public void create(Solicitation solicitation);
	
	public List<Solicitation> readByIdHouse(int idHouse);
	
	public List<Solicitation> readByIdHouseAndIdCategory(int idHouse, String idCategory);
	
	public List<Solicitation> readByIdHouseAndIdUser(int idHouse, int idUser);
	
	public List<Solicitation> readByIdHouseAndStatus(int idHouse, String status);
	
	public void update(Solicitation solicitationUpdated);
	
	public boolean delete(Solicitation solicitation);
	
	public Optional<Solicitation> readById(int id);
}
