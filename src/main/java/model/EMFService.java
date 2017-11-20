package model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manejar transacciones con la DB
 */
public class EMFService {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("$objectdb/db/clients.odb");
	private EMFService() {
		com.objectdb.Enhancer.enhance("clients.*");

	}
	public static EntityManagerFactory get() {
		return emfInstance;
	}
}