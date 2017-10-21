package br.com.mastermenu.user.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mastermenu.solicitation.dao.SolicitationDAO;
import br.com.mastermenu.solicitation.model.Solicitation;
import br.com.mastermenu.user.model.User;

public class UserDAO implements IUserDAO{
	private static UserDAO instance;
    private EntityManager em;
    
    public static UserDAO getInstance(){
              if (instance == null){
                       instance = new UserDAO();
              }
              
              return instance;
    }

    public UserDAO() {
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
	public void create(User u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(User u) {
		try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
       } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<User> findById(int id) {
		return Optional.ofNullable(em.find(User.class, id));
	}

	@SuppressWarnings("static-access")
	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable((User)(em.createQuery("FROM user u WHERE u.email = :email")
				   .setParameter("email", email).getSingleResult()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByIdHouse(int idHouse) {
		return em.createQuery("FROM " + User.class.getName() + " WHERE idHouse = :idHouse")
				   .setParameter("idHouse", idHouse).getResultList();
	}

}
