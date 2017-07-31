package br.com.mastermenu.solicitation.dao;

import java.util.List;

import br.com.mastermenu.solicitation.model.Solicitation;

public interface SolicitationDAO {
	public List<Solicitation> list();
	
	public Solicitation findById(int id);
	
	public Solicitation insert(Solicitation solicitation);
}