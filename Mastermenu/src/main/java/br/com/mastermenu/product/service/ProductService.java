package br.com.mastermenu.product.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.product.dao.IProductDAO;
import br.com.mastermenu.product.dao.ProductDAO;
import br.com.mastermenu.product.model.Product;

public class ProductService implements IProductService{
	
	private IProductDAO productDAO = new ProductDAO();
	@Override
	public void create(Product product) {
		productDAO.create(product);
	}

	@Override
	public List<Product> read() {
		return productDAO.read();
	}

	@Override
	public void update(Product productUpdated) {
		productDAO.update(productUpdated);
	}

	@Override
	public boolean delete(Product productDeleted) {
		return productDAO.delete(productDeleted);
	}
	
	@Override
	public Optional<Product> readById(int id) {
		return productDAO.readById(id);
	}

	@Override
	public List<Product> encontrarPorIdCategoria(int idCategoria) {
		return productDAO.encontrarPorIdCategoria(idCategoria);
	}
		
}
