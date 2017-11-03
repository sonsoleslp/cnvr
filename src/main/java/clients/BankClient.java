package clients;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankClient implements  Serializable, BankClientI {
	
	// Persistent Fields:
    private static final long serialVersionUID = 1L;
	
	@Id
 	private Long id;
	private String name;
	private float balance;
	
	public BankClient(Long id, String name, float initialBalance) {
		this.id = id;
		this.name = name;
		this.balance = initialBalance;

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override 
	public Long getAccount() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setAccount(Long account) {
		// TODO Auto-generated method stub
		this.id = account;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;

	}

	@Override
	public float getBalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}

	@Override
	public void setBalance(float balance) {
		// TODO Auto-generated method stub
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Cliente: " +  this.name + 
				". \n Saldo: " + this.balance + 
				". \n Nº de cuenta: " + this.id + ".";
	}
	

}
