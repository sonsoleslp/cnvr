package beans;

import java.io.Serializable;
import java.text.DecimalFormat;


import java.io.Serializable;


import clients.BankClient;

public class BankClientBean implements  Serializable {
	
 	private Long account;
	private String name;
	private String balance;
	
	public BankClientBean(Long id, String name, float initialBalance) {
		this.account = id;
		this.name = name;
		DecimalFormat df = new DecimalFormat("#0.00"); 
		this.balance = df.format(initialBalance);

	}
	public BankClientBean(BankClient bc) {
		this.name = bc.getName();
		DecimalFormat df = new DecimalFormat("#0.00"); 
		this.balance = df.format(bc.getBalance());
		this.account = bc.getAccount();
	}
	
	public Long getAccount() {
		return this.account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		DecimalFormat df = new DecimalFormat("#0.00"); 
		this.balance = df.format(balance);
	}
	
	public String toString() {
		return "Cliente: " +  this.name + 
				". \n Saldo: " + this.balance + 
				". \n Nº de cuenta: " + this.account + ".\n";
	}
	

}
