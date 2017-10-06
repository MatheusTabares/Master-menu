package br.com.mastermenu.house.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.mastermenu.model.House;
import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.util.Connection;

public class HouseDAO implements IHouseDAO{

private final EntityManager em;
	
	public HouseDAO() {
		this.em = Connection.connection();
	}
	
	@Override
	public void create(House h) {
		try {
			em.getTransaction().begin();
			em.persist(h);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<House> read() {
		return em.createQuery("FROM " + House.class.getName()).getResultList();
	}

	@Override
	public void update(House houseUpdated) {
		try {
            em.getTransaction().begin();
            em.merge(houseUpdated);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(House h) {
		try {
			em.getTransaction().begin();
            em.remove(h);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	            return false;
	   }
	   return true;
	}

	@Override
	public Optional<House> readById(int id) {
		return Optional.ofNullable(em.find(House.class, id));
	}
	
}
