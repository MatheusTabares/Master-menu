package br.com.mastermenu.solicitation.dao;

import java.util.ArrayList;
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
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
			em.persist(s);
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
	public List<Solicitation> readByIdHouse(int idHouse) {
		List<Solicitation> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + Solicitation.class.getName() + " WHERE house_id = :house_id")
					   .setParameter("house_id", idHouse).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> readByIdHouseAndIdCategory(int idHouse, String idCategory) {
		List<Solicitation> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + Solicitation.class.getName() + " WHERE typeCategory = :typeCategory AND house_id = :house_id AND status is null")
					   .setParameter("typeCategory", idCategory)
					   .setParameter("house_id", idHouse).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}

	@Override
	public void update(Solicitation solicitationUpdated) {
		try {
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
            em.merge(solicitationUpdated);
            em.getTransaction().commit();
       } catch (Exception ex) {
	            ex.printStackTrace();
	            em.getTransaction().rollback();
	   } finally {
		   em = null;
	   }
	}

	@Override
	public boolean delete(Solicitation solicitation) {
		try {
			if(em == null) {
				em = getEntityManager();
			}
			em.getTransaction().begin();
			em.remove(solicitation);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return true;
	}

	@Override
	public Optional<Solicitation> readById(int id) {
		Optional<Solicitation> s = Optional.empty();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			s = Optional.ofNullable(em.find(Solicitation.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return s;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> readByIdHouseAndStatus(int idHouse, String status) {
		List<Solicitation> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + Solicitation.class.getName() + " WHERE house_id = :house_id AND status = :status")
					   .setParameter("house_id", idHouse)
					   .setParameter("status", status).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitation> readByIdHouseAndIdUser(int idHouse, int idUser) {
		List<Solicitation> list = new ArrayList<>();
		try {
			if(em == null) {
				em = getEntityManager();
			}
			list = em.createQuery("FROM " + Solicitation.class.getName() + " WHERE house_id = :house_id AND idClient = :idUser AND status is null")
					   .setParameter("house_id", idHouse)
					   .setParameter("idUser", idUser).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em = null;
		}
		return list;
	}
	
}
