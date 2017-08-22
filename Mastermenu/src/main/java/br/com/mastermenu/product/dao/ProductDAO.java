package br.com.mastermenu.product.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.util.HibernateUtil;

public class ProductDAO implements IProductDAO{
	
	private final Session session;
	
	public ProductDAO() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public Product create(Product product) {
		Transaction t = session.beginTransaction();
		session.save(product);
		t.commit();
		return product;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Product> read(Optional<String> filter) {
		if(filter.isPresent()) {
			if(filter.get().trim().equals("title")) {
				return session.createCriteria(Composition.class)
						.add(Restrictions.eq("title", filter)).list();
			} /*else {
				TODO: BUSCAR POR CATEGORIA
				return session.createCriteria(Composition.class)
							.add(arg0)
			}*/
		}
		return session.createCriteria(Composition.class).list();
	}

	@Override
	public Product update(Product productUpdated) {
		Transaction t = session.beginTransaction();
		session.update(productUpdated);
		t.commit();
		return productUpdated;
	}

	@Override
	public boolean delete(Product product) {
		Transaction t = session.beginTransaction();
		session.delete(product);
		t.commit();
		return true;
	}

	@Override
	public Optional<Product> readById(int id) {
		return Optional.ofNullable(session.get(Product.class, id));
	}

}
