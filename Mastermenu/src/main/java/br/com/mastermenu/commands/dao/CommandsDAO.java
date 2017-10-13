package br.com.mastermenu.commands.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.mastermenu.commands.model.Commands;
import br.com.mastermenu.product.model.Product;

public class CommandsDAO implements ICommandsDAO {
	private static CommandsDAO instance;
    private EntityManager em;
    
    public static CommandsDAO getInstance(){
              if (instance == null){
                       instance = new CommandsDAO();
              }
              
              return instance;
    }

    public CommandsDAO() {
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
	public void create(Commands c) {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commands> read(int idHouse) {
		return em.createQuery("FROM " + Commands.class.getName() + " WHERE house_id = :house_id")
				.setParameter("house_id", idHouse).getResultList();
	
	}

	@Override
	public void update(Commands c) {
		try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   }
	}

	@Override
	public boolean delete(Commands c) {
		try {
			em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
	   } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	            return false;
	   }
	   return true;
	}

	@Override
	public Optional<Commands> readById(int id) {
		return Optional.ofNullable(em.find(Commands.class, id));
	}

}
