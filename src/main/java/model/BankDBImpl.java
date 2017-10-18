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
	public BankClient crearCliente(String name, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = new BankClient(name, amount);
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
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = em.find(BankClient.class, account);
		bc.setBalance(bc.getBalance()+amount);
		em.merge(bc);
		em.getTransaction().commit();

		em.close();
		return bc;
	}

	@Override
	public BankClient retirar(Long account, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();

		BankClient bc = em.find(BankClient.class, account);
		bc.setBalance(bc.getBalance()-amount);
		em.merge(bc);
		em.getTransaction().commit();

		em.close();
		return bc;
	}

	@Override
	public BankClient borrar(Long account) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc = em.find(BankClient.class, account);
		em.remove(bc);
		em.getTransaction().commit();
		em.close();
		return bc;
	}

	@Override
	public List<BankClient> transferir(Long origin, Long target, float amount) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		BankClient bc1 = em.find(BankClient.class, origin);
		bc1.setBalance(bc1.getBalance()-amount);
		em.merge(bc1);
		BankClient bc2 = em.find(BankClient.class, target);
		bc2.setBalance(bc2.getBalance()+amount);
		em.merge(bc2);
		em.getTransaction().commit();
		em.close();
		List<BankClient> lista = new ArrayList();
		lista.add(bc1);
		lista.add(bc2);
		return lista;
	}

	public void populate(List<BankClient> all) {
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM BankClient e").executeUpdate();
		for (all.iterator(); all.iterator().hasNext();) {
            BankClient bc = all.iterator().next();
    		em.persist(bc);
		}
		em.getTransaction().commit();
		em.close();
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
