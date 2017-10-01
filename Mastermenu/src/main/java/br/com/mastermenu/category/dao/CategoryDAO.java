package br.com.mastermenu.category.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.mastermenu.categoria.model.Categoria;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.util.Connection;

public class CategoryDAO implements ICategoryDAO{

private final EntityManager em;
	
	public CategoryDAO() {
		this.em = Connection.connection();
	}
	
	@Override
	public void create(Categoria c) {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Categoria> read() {
		return em.createQuery("FROM " + Categoria.class.getName()).getResultList();
	}

	@Override
	public void update(Categoria categoryUpdated) {
		try {
            em.getTransaction().begin();
            em.merge(categoryUpdated);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(Categoria c) {
		try {
			em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	            return false;
	   }
	   return true;
	}

	@Override
	public Optional<Categoria> readById(int id) {
		return Optional.ofNullable(em.find(Categoria.class, id));
	}

}
