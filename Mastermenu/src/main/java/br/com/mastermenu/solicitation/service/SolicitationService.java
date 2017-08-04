package br.com.mastermenu.solicitation.service;

import java.util.List;
import java.util.Optional;
import br.com.mastermenu.solicitation.model.Solicitation;

public interface SolicitationService {
	public List<Solicitation> list(Optional<String> filter);
	
	public Optional<Solicitation> findById(int id);
	
	public Solicitation insert(Solicitation solicitation);
	
	public Solicitation update(int indexSolicitation, Solicitation solicitation);
	
	public boolean delete(int id);
}
