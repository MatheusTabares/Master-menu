package br.com.mastermenu.category.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.categoria.model.Categoria;
import br.com.mastermenu.category.dao.CategoryDAO;

public class CategoryService implements ICategoryService{
	
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	@Override
	public void create(Categoria category) {
		categoryDAO.create(category);
	}

	@Override
	public List<Categoria> read() {
		return categoryDAO.read();
	}

	@Override
	public void update(Categoria categoryUpdated) {
		categoryDAO.update(categoryUpdated);
	}

	@Override
	public boolean delete(Categoria category) {
		return categoryDAO.delete(category);
	}

	@Override
	public Optional<Categoria> readById(int id) {
		return categoryDAO.readById(id);
	}
	
}
