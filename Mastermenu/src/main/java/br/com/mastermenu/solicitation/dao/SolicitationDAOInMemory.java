package br.com.mastermenu.solicitation.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mastermenu.solicitation.model.Solicitation;

public class SolicitationDAOInMemory implements SolicitationDAO {

	@Override
	public List<Solicitation> list() {
		Solicitation s1 = new Solicitation(1, "Pedido 1", Optional.of("Descrição 1"));
		Solicitation s2 = new Solicitation(2, "Pedido 2", Optional.of("Descrição 2"));
		Solicitation s3 = new Solicitation(3, "Pedido 3", Optional.of("Descrição 3"));
		List<Solicitation> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3); 
		
		return list;
	}
	
}
