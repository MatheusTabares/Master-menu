package br.com.mastermenu.composition.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.composition.dao.CompositionDAO;
import br.com.mastermenu.composition.dao.ICompositionDAO;
import br.com.mastermenu.composition.model.Composition;

public class CompositionService implements ICompositionService {

	private ICompositionDAO compositionDAO = new CompositionDAO();
	@Override
	public void create(Composition composition) {
		compositionDAO.create(composition);
	}

	@Override
	public List<Composition> read() {
		return compositionDAO.read();
	}

	@Override
	public void update(Composition compositionUpdated) {
		compositionDAO.update(compositionUpdated);
	}

	@Override
	public boolean delete(Composition compostionDeleted) {
		return compositionDAO.delete(compostionDeleted);
	}
	
	@Override
	public Optional<Composition> readById(int id) {
		return compositionDAO.readById(id);
	}
		
}
