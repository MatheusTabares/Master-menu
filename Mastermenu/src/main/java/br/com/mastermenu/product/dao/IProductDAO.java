package br.com.mastermenu.product.dao;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.product.model.Product;

public interface IProductDAO {
	public void create(Product product);
	
	public List<Product> read(int idHouse);
	
	public void update(Product productUpdated);
	
	public boolean delete(Product product);
	
	public Optional<Product> readById(int id);
	
	public List<Product> encontrarPorIdCategoria(int idCategoria, int idHouse);
}	
