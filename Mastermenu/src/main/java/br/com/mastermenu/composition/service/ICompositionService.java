package br.com.mastermenu.composition.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.composition.model.Composition;

public interface ICompositionService {
	public void create(Composition composition);
	
	public List<Composition> read();
	
	public void update(Composition compositionUpdated);
	
	public boolean delete(Composition composition);
	
	public Optional<Composition> readById(int id);
}
