package br.com.mastermenu.composition.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.util.Connection;

public class CompositionDAO implements ICompositionDAO{
	private final EntityManager em;
	
	public CompositionDAO() {
		this.em = Connection.connection();
	}
	
	@Override
	public void create(Composition c) {
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
	public List<Composition> read() {
		return em.createQuery("FROM " + Composition.class.getName()).getResultList();
	}

	@Override
	public void update(Composition compositionUpdated) {
		try {
            em.getTransaction().begin();
            em.merge(compositionUpdated);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(Composition composition) {
		try {
			em.getTransaction().begin();
            em.remove(composition);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	            return false;
	   }
	   return true;
	}

	@Override
	public Optional<Composition> readById(int id) {
		return Optional.ofNullable(em.find(Composition.class, id));
	}

}
