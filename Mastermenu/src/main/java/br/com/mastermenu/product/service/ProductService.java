package br.com.mastermenu.product.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.product.dao.IProductDAO;
import br.com.mastermenu.product.dao.ProductDAOInMemory;
import br.com.mastermenu.product.model.Product;

public class ProductService implements IProductService{
	
	private IProductDAO productDAO = new ProductDAOInMemory();

	@Override
	public Product create(Product product) {
		if(validationProduct(product)) {
			return productDAO.create(product);
		}
		return null;
	}

	@Override
	public List<Product> read(Optional<String> filter) {
		return productDAO.read();
	}

	@Override
	public Product update(int indexProduct, Product productUpdated) {
		if(validationProduct(productUpdated)) {
			return productDAO.update(indexProduct, productUpdated);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		Optional<Product> product = productDAO.readById(id);
		if(product.isPresent()) {
			return productDAO.delete(id);
		}
		return false;
	}

	@Override
	public Optional<Product> readById(int id) {
		return productDAO.readById(id);
	}
	
	private boolean validationProduct(Product product) {
		List<Product> listProduct = productDAO.read();
		for(Product p : listProduct) {
			if(product.getTitle() != null && p.getTitle().trim().equalsIgnoreCase(product.getTitle().trim())) {
				return false;
			}
		}
		if(product.getCategory() != null && !product.getCategory().trim().equals("") &&
			!product.getTitle().trim().equals("") && product.getListComposition() != null &&
			product.getListComposition().size() != 0) {
			
			return true;
		}
		return false;
	}

}
