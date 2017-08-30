package br.com.mastermenu.composition.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.composition.model.Composition;

public interface ICompositionService {
	public Composition create(Composition composition);
	
	public List<Composition> read();
	
	public Composition update(Composition compositionUpdated);
	
	public boolean delete(int id);
	
	public Optional<Composition> readById(int id);
}
