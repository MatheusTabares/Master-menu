package br.com.mastermenu.solicitation.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mastermenu.solicitation.model.Solicitation;

public class SolicitationDAOInMemory implements SolicitationDAO {
	private Solicitation s1;
	private Solicitation s2;
	private Solicitation s3;
	private List<Solicitation> list = new ArrayList<>();
	
	public SolicitationDAOInMemory() {
		Solicitation s1 = new Solicitation(1, "Pedido 1", Optional.of("Descri��o 1"));
		Solicitation s2 = new Solicitation(2, "Pedido 2", Optional.of("Descri��o 2"));
		Solicitation s3 = new Solicitation(3, "Pedido 3", Optional.of("Descri��o 3"));
		list.add(s1);
		list.add(s2);
		list.add(s3); 
	}

	@Override
	public List<Solicitation> list() {
		return list;
	}

	@Override
	public Solicitation findById(int id) {
		for(Solicitation solicitation : list) {
			if(solicitation.getId().equals(id)) {
				return solicitation;
			}
		}
		return null;
	}

	@Override
	public Solicitation insert(Solicitation solicitation) {
		list.add(solicitation);
		return solicitation;
	}
	
}