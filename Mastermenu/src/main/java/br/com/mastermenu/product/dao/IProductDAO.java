package br.com.mastermenu.product.dao;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.product.model.Product;

public interface IProductDAO {
	public Product create(Product product);
	
	public List<Product> read(Optional<String> filter);
	
	public Product update(Product productUpdated);
	
	public boolean delete(Product product);
	
	public Optional<Product> readById(int id);
}	
