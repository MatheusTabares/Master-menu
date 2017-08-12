package br.com.mastermenu.product.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.product.model.Product;

public interface IProductService {
	
	public Product create(Product product);
	
	public List<Product> read(Optional<String> filter);
	
	public Product update(Product productUpdated);
	
	public boolean delete(int id);
	
	public Optional<Product> readById(int id);
	
}
