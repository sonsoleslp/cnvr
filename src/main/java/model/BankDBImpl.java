package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import clients.BankClient;
import bank.BankI;
public class BankDBImpl implements BankI {
	
	//Singleton pattern
	
	private static BankDBImpl instance;
	private BankDBImpl () {
	}
	public static BankDBImpl getInstance() {
		if (instance == null)
			instance = new BankDBImpl();
		return instance;
	}
	

	@Override
	public BankClient crearCliente(Long id, String name, float amount) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = new BankClient(id, name, amount);
		em.persist(bc);
		em.getTransaction().commit();
		em.close();
		return bc;
	}
	
	@Override
	public BankClient consultarSaldo(Long account) {
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = em.find(BankClient.class, account);
		em.close();
		return bc;
	}

	@Override
	public BankClient ingresar(Long account, float amount) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = em.find(BankClient.class, account);
		if (bc != null) {
			bc.setBalance(bc.getBalance()+amount);
			em.merge(bc);
			em.getTransaction().commit();
	
			em.close();
			return bc;
		}
		return null;
	}

	@Override
	public BankClient retirar(Long account, float amount) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();

		BankClient bc = em.find(BankClient.class, account);
		if (bc != null) {
			bc.setBalance(bc.getBalance()-amount);
			em.merge(bc);
			em.getTransaction().commit();
	
			em.close();
			return bc;
		}
		return null;
	}

	@Override
	public BankClient borrar(Long account) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = em.find(BankClient.class, account);
		if (bc != null) {
			em.remove(bc);
			em.getTransaction().commit();
			em.close();
			
			return bc;
		}
		return null;
	}

	@Override
	public List<BankClient> transferir(Long origin, Long target, float amount) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc1 = em.find(BankClient.class, origin);
		BankClient bc2 = em.find(BankClient.class, target);
		List<BankClient> lista = new ArrayList<BankClient>();

		if (bc1 != null && bc2 != null ) {
			bc1.setBalance(bc1.getBalance()-amount);
			bc2.setBalance(bc2.getBalance()+amount);
			em.merge(bc1);
			em.merge(bc2);
			em.getTransaction().commit();
			lista.add(bc1);
			lista.add(bc2);
 		}
		em.close();
		return lista;
	}

	public void populate(List<BankClient> all) {
		try {
		System.out.println("Populating DB");
		System.out.println(all);
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM BankClient").executeUpdate();
		
		for (int i = 0; i < all.size(); i++) {
			 BankClient bc = all.get(i);
			 em.persist(bc);
 		}
 
		
		System.out.println("DONE");
		em.getTransaction().commit();
		em.close(); 
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	@Override
	public void deleteAll() {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM BankClient").executeUpdate();
		em.getTransaction().commit();
		em.close(); 
	

	}
	
	@Override
	public List<BankClient> lista(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM BankClient m");
		@SuppressWarnings("unchecked")
		List<BankClient> lista = new ArrayList <BankClient>(q.getResultList());
		em.close();
		System.out.println(lista);
		return lista;
	}

}
