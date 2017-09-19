package br.com.mastermenu.product.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.product.model.Product;

public interface IProductService {
	public void create(Product product);
	
	public List<Product> read();
	
	public void update(Product productUpdated);
	
	public boolean delete(Product product);
	
	public Optional<Product> readById(int id);
	
	public List<Product> encontrarPorIdCategoria(int idCategoria);
}
