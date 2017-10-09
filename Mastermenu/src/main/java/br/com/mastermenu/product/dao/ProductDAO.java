package br.com.mastermenu.product.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.util.Connection;

public class ProductDAO implements IProductDAO{
	
	private final EntityManager em;
	
	public ProductDAO() {
		this.em = Connection.connection();
	}
	
	@Override
	public void create(Product p) {
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Product> read(int idHouse) {
		return em.createQuery("FROM " + Product.class.getName())
				.setParameter("house_id", idHouse).getResultList();
	}

	@Override
	public void update(Product productUpdated) {
		try {
            em.getTransaction().begin();
            em.merge(productUpdated);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(Product p) {
		try {
			em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	            return false;
	   }
	   return true;
	}

	@Override
	public Optional<Product> readById(int id) {
		return Optional.ofNullable(em.find(Product.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> encontrarPorIdCategoria(int idCategoria, int idHouse) {
		return em.createQuery("FROM " + Product.class.getName() + " WHERE categoria_id like :categoria_id")
			   .setParameter("categoria_id", idCategoria)
			   .setParameter("house_id", idHouse).getResultList();
		//return em.createQuery("FROM " + Product.class.getName() + "P WHERE P.categoria_id = " + idCategoria).getResultList();
	}
}
