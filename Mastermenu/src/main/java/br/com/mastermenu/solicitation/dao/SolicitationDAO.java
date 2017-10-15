package br.com.mastermenu.solicitation.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mastermenu.product.model.Product;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.util.Connection;

public class SolicitationDAO implements ISolicitationDAO{
	private static SolicitationDAO instance;
    private EntityManager em;
    
    public static SolicitationDAO getInstance(){
              if (instance == null){
                       instance = new SolicitationDAO();
              }
              
              return instance;
    }

    public SolicitationDAO() {
    	em = getEntityManager();
    }

    private EntityManager getEntityManager() {
		  EntityManagerFactory factory = Persistence.createEntityManagerFactory("db_mastermenu");
		  if (em == null) {
		        em = factory.createEntityManager();
		  }
		
          return em;
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
	public List<Solicitation> readByIdHouse(int idHouse) {
		return em.createQuery("FROM " + Solicitation.class.getName() + " WHERE house_id = :house_id")
				   .setParameter("house_id", idHouse).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> readByIdHouseAndIdCategory(int idHouse, String idCategory) {
		return em.createQuery("FROM " + Solicitation.class.getName() + " WHERE typeCategory = :typeCategory AND house_id = :house_id AND status is null")
				   .setParameter("typeCategory", idCategory)
				   .setParameter("house_id", idHouse).getResultList();
	}

	@Override
	public void update(Solicitation solicitationUpdated) {
		try {
            em.getTransaction().begin();
            em.merge(solicitationUpdated);
            em.getTransaction().commit();
       } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(Solicitation solicitation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Solicitation> readById(int id) {
		return Optional.ofNullable(em.find(Solicitation.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> readByIdHouseAndStatus(int idHouse, String status) {
		return em.createQuery("FROM " + Solicitation.class.getName() + " WHERE house_id = :house_id AND status = :status")
				   .setParameter("house_id", idHouse)
				   .setParameter("status", status).getResultList();
	}
	
}
