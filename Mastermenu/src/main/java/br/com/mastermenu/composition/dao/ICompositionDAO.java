package br.com.mastermenu.composition.dao;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.composition.model.Composition;

public interface ICompositionDAO {
	public Composition create(Composition composition);
	
	public List<Composition> read(Optional<String> filter);
	
	public Composition update(Composition compositionUpdated);
	
	public boolean delete(Composition composition);
	
	public Optional<Composition> readById(int id);
}
