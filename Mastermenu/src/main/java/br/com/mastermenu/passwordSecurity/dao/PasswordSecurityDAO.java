package br.com.mastermenu.passwordSecurity.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mastermenu.passwordSecurity.model.PasswordSecurity;
import br.com.mastermenu.solicitation.dao.SolicitationDAO;
import br.com.mastermenu.solicitation.model.Solicitation;

public class PasswordSecurityDAO implements IPasswordSecurityDAO {
	
	private static PasswordSecurityDAO instance;
    private EntityManager em;
    
    public static PasswordSecurityDAO getInstance(){
              if (instance == null){
                       instance = new PasswordSecurityDAO();
              }
              
              return instance;
    }

    public PasswordSecurityDAO() {
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
	public void create(PasswordSecurity ps) {
		try {
			em.getTransaction().begin();
			em.persist(ps);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(PasswordSecurity ps) {
		try {
            em.getTransaction().begin();
            em.merge(ps);
            em.getTransaction().commit();
       } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(PasswordSecurity ps) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<PasswordSecurity> readByIdUser(int id) {
		return Optional.ofNullable((PasswordSecurity) em.createQuery(
				"FROM passwordSecurity WHERE user_id = :user_id")
				   .setParameter("user_id", id).getSingleResult());
	}
	
}
