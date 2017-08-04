package br.com.mastermenu.solicitation.dao;

import java.util.List;
import java.util.Optional;

import br.com.mastermenu.solicitation.model.Solicitation;

public interface SolicitationDAO {
	public List<Solicitation> list();
	
	public Optional<Solicitation> findById(int id);
	
	public Solicitation insert(Solicitation solicitation);
	
	public Solicitation update(int indexSolicitation, Solicitation solicitation);
}
