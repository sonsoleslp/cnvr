package clients;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Objeto cliente bean, que se almacena en la DB y se puede renderizar directamente en una plantilla JSP
 */
@Entity
public class BankClient implements  Serializable, BankClientI {

    private static final long serialVersionUID = 1L;
	/**
	 * Nº de cuenta
	 */
	@Id
 	private Long id;
	/**
	 * Nombre del cliente
	 */
	private String name;
	/**
	 * Saldo de la cuenta
	 */
	private float balance;
	
	/**
	 * Constructor
	 * @param id Nº de cuenta
	 * @param name Nombre del cliente
	 * @param initialBalance Saldo inicial
	 */
	public BankClient(Long id, String name, float initialBalance) {
		this.id = id;
		this.name = name;
		this.balance = initialBalance;
	}
	
	/**
	 * Getter del nº de cuenta
	 * @return Nº de cuenta
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter del nº de cuenta
	 * @param id Nº de cuenta
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override 
	public Long getAccount() {
		// TODO Auto-generated method stub
		return this.id;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAccount(Long account) {
		// TODO Auto-generated method stub
		this.id = account;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getBalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBalance(float balance) {
		// TODO Auto-generated method stub
		this.balance = balance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Cliente: " +  this.name + 
				". \n Saldo: " + this.balance + 
				". \n Nº de cuenta: " + this.id + ".";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BankClient) {
		BankClient comp = (BankClient) obj;
		return this.getId().equals(comp.getId()) && this.getBalance() == (comp.getBalance()) && this.getName().equals(comp.getName());
		} 
		return false;
	}
	

}
