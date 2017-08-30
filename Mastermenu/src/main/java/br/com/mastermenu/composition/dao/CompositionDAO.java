package br.com.mastermenu.composition.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.mastermenu.composition.model.Composition;
import br.com.mastermenu.util.Connection;

public class CompositionDAO implements ICompositionDAO{
	private final Session session = null;
	private final EntityManager em;
	
	public CompositionDAO() {
		this.em = Connection.connection();
	}
	
	@Override
	public Composition create(Composition c) {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return c;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Composition> read() {
		return session.createCriteria(Composition.class).list();
	}

	@Override
	public Composition update(Composition compositionUpdated) {
		Transaction t = session.beginTransaction();
		session.update(compositionUpdated);
		t.commit();
		return compositionUpdated;
	}

	@Override
	public boolean delete(Composition composition) {
		Transaction t = session.beginTransaction();
		session.delete(composition);
		t.commit();
		return true;
	}

	@Override
	public Optional<Composition> readById(int id) {
		return Optional.ofNullable(session.get(Composition.class, id));
	}

}
