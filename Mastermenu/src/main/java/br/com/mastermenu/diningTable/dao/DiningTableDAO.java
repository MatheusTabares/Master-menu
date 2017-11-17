package br.com.mastermenu.diningTable.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.mastermenu.diningTable.model.DiningTable;

public class DiningTableDAO implements IDiningTableDAO{
	private static DiningTableDAO instance;
    private EntityManager em;
    
    public static DiningTableDAO getInstance(){
              if (instance == null){
                       instance = new DiningTableDAO();
              }
              
              return instance;
    }

    public DiningTableDAO() {
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
	public void create(DiningTable dt) {
		try {
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
			em.persist(dt);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em = null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiningTable> readByIdHouse(int idHouse) {
		List<DiningTable> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + DiningTable.class.getName() + " WHERE idHouse = :idHouse")
					   .setParameter("idHouse", idHouse).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiningTable> readByIdHouseAndIdUser(int idHouse, int idUser) {
		List<DiningTable> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + DiningTable.class.getName() + " WHERE idHouse = :idHouse AND idClient = :idUser")
					   .setParameter("idHouse", idHouse)
					   .setParameter("idUser", idUser).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiningTable> readAllNotReservedByIdHouse(int idHouse) {
		List<DiningTable> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + DiningTable.class.getName() + " WHERE idHouse = :idHouse AND reserved = false")
					   .setParameter("idHouse", idHouse).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}

	@Override
	public void update(DiningTable dt) {
		try {
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
            em.merge(dt);
            em.getTransaction().commit();
       } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   } finally {
		   em = null;
	   }
	}

	@Override
	public boolean delete(DiningTable dt) {
		try {
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
			em.remove(dt);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return true;
	}

	@Override
	public Optional<DiningTable> readById(int id) {
		Optional<DiningTable> dt = Optional.empty();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			dt = Optional.ofNullable(em.find(DiningTable.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return dt;
	}
	
}
