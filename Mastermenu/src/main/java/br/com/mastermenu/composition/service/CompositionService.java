package br.com.mastermenu.composition.service;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.composition.dao.CompositionDAO;
import br.com.mastermenu.composition.dao.ICompositionDAO;
import br.com.mastermenu.composition.model.Composition;

public class CompositionService implements ICompositionService {

	private ICompositionDAO compositionDAO = new CompositionDAO();
	@Override
	public Composition create(Composition composition) {
		if(validationComposition(composition, Optional.empty())) {
			return compositionDAO.create(composition);
		}
		return null;
	}

	@Override
	public List<Composition> read() {
		List<Composition> list = compositionDAO.read();
		if(list.size() > 0) {
			return list;
		};
		return null;
	}

	@Override
	public Composition update(Composition compositionUpdated) {
		if(validationComposition(compositionUpdated, Optional.of("update"))) {
			return compositionDAO.update(compositionUpdated);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		Optional<Composition> compostionDeleted = compositionDAO.readById(id);
		if(compostionDeleted.isPresent()) {
			compositionDAO.delete(compostionDeleted.get());
			return true;
		}
		return false;
	}

	@Override
	public Optional<Composition> readById(int id) {
		return compositionDAO.readById(id);
	}
	
	private boolean validationComposition(Composition compositionUpdated, Optional<String> toUpdate) {
		if(toUpdate.isPresent() && toUpdate.get().equals("update")) {
			if(compositionUpdated.getId() == null) {
				return false;
			}
		}
		if(compositionUpdated.getName() != null && !compositionUpdated.getName().trim().equals("")) {
			return true;
		}
		return false;
	}
	
}
