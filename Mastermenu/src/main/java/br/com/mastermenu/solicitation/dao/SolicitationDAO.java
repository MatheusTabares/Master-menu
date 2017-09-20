package br.com.mastermenu.solicitation.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.util.Connection;

public class SolicitationDAO implements ISolicitationDAO{
	private final EntityManager em;
	
	public SolicitationDAO() {
		this.em = Connection.connection();
	}
	@Override
	public void create(Solicitation s) {
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> read() {
		return em.createQuery("FROM solicitation").getResultList();
	}

	@Override
	public void update(Solicitation solicitationUpdated) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Solicitation solicitation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Solicitation> readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
