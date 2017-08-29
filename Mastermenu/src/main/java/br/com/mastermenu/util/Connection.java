package br.com.mastermenu.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	
	public static EntityManager connection() {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("db_mastermenu");
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
}
