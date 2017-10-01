package br.com.mastermenu.category.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.categoria.model.Categoria;

public interface ICategoryService {
	public void create(Categoria category);
	
	public List<Categoria> read();
	
	public void update(Categoria categoryUpdated);
	
	public boolean delete(Categoria category);
	
	public Optional<Categoria> readById(int id);
	
}
