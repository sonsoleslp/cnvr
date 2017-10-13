package model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manejar transacciones con la DB
 * 
 *
 */
public class EMFService {
	
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("$objectdb/db/clients.odb");
	private EMFService() {
	}
	public static EntityManagerFactory get() {
		com.objectdb.Enhancer.enhance("clients.*");
		return emfInstance;
	}
}