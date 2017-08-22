package br.com.mastermenu.composition.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.mastermenu.composition.model.Composition;

public class CompositionDAO implements ICompositionDAO{
	private final Session session;
	
	public CompositionDAO() {
		this.session = null;
	}
	
	@Override
	public Composition create(Composition composition) {
		Transaction t = session.beginTransaction();
		session.save(composition);
		t.commit();
		return composition;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Composition> read(Optional<String> filter) {
		if(filter.isPresent()) {
			return session.createCriteria(Composition.class)
			.add(Restrictions.eq("name", filter)).list();
		}
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
