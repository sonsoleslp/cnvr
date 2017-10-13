package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import clients.BankClient;

public class ClientImpl implements ClientDAO {
	
	//Singleton pattern
	
	private static ClientImpl instance;
	private ClientImpl () {
	}
	public static ClientImpl getInstance() {
		if (instance == null)
			instance = new ClientImpl();
		return instance;
	}
	

	@Override
	public BankClient crearCliente(String name, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = new BankClient(name, amount);
		em.persist(bc);
		em.close();
		System.out.println(bc.toString());
		return bc;
	}
	
	@Override
	public BankClient consultarSaldo(Long primaryKey) {
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = em.find(BankClient.class, primaryKey);
		em.close();
		return bc;
	}

	@Override
	public BankClient ingresar(Long primaryKey, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = em.find(BankClient.class, primaryKey);
		bc.setBalance(bc.getBalance()+amount);
		em.merge(bc);
		em.close();
		return bc;
	}

	@Override
	public BankClient retirar(Long primaryKey, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = em.find(BankClient.class, primaryKey);
		bc.setBalance(bc.getBalance()-amount);
		em.merge(bc);
		em.close();
		return bc;
	}

	@Override
	public BankClient borrar(Long primaryKey) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc = em.find(BankClient.class, primaryKey);
		em.remove(bc);
		em.close();
		return bc;
	}

	@Override
	public List<BankClient> transferir(Long origin, Long target, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		BankClient bc1 = em.find(BankClient.class, origin);
		bc1.setBalance(bc1.getBalance()-amount);
		em.merge(bc1);
		BankClient bc2 = em.find(BankClient.class, target);
		bc2.setBalance(bc2.getBalance()+amount);
		em.merge(bc2);
		em.close();
		List<BankClient> lista = new ArrayList();
		lista.add(bc1);
		lista.add(bc2);
		return lista;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.createQuery("DELETE FROM BankClient e").executeUpdate();
		em.close();
	

	}
	
	@Override
	public List<BankClient> lista(){
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM BankClient m");
		List<BankClient> lista = new ArrayList <BankClient>(q.getResultList());
		em.close();
		System.out.println(lista);
		return lista;
	}

}
