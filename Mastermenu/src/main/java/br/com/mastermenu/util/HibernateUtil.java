package br.com.mastermenu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sf;

    static {
        try {
        	sf = new Configuration().buildSessionFactory();
        } catch(Throwable ex) {
        	System.err.println("Falha para iniciar a sessão!" + ex);
        	throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
    	return sf;
    }
}
