package br.com.mastermenu.product.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.composition.dao.CompositionDAO;
import br.com.mastermenu.composition.dao.ICompositionDAO;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.product.dao.IProductDAO;
import br.com.mastermenu.product.dao.ProductDAO;
import br.com.mastermenu.product.dao.ProductDAOInMemory;
import br.com.mastermenu.product.model.Product;

public class ProductService implements IProductService{
	
	private IProductDAO productDAO = new ProductDAO();
	@Override
	public Product create(Product product) {
		if(validationProduct(product, Optional.empty())) {
			return productDAO.create(product);
		}
		return null;
	}

	@Override
	public List<Product> read(Optional<String> filter) {
		List<Product> list = productDAO.read(filter);
		if(list.size() > 0) {
			return list;
		};
		return null;
	}

	@Override
	public Product update(Product productUpdated) {
		if(validationProduct(productUpdated, Optional.of("update"))) {
			return productDAO.update(productUpdated);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		Optional<Product> productDeleted = productDAO.readById(id);
		if(productDeleted.isPresent()) {
			productDAO.delete(productDeleted.get());
			return true;
		}
		return false;
	}

	@Override
	public Optional<Product> readById(int id) {
		return productDAO.readById(id);
	}
	
	private boolean validationProduct(Product product, Optional<String> toUpdate) {
		if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
			if(product.getId() == null) {
				return false;
			}
		}
		if(product.getTitle() != null && !product.getTitle().trim().equals("") && 
				product.getCategory() != null && !product.getCategory().trim().equals("")) {
			return true;
		}
		return false;
	}
	

}
