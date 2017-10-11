package clients;

import java.util.Random;

public class BankClient implements BankClientI {
	private int account;
	private String name;
	private float balance;
	
	public BankClient(String name, float initialBalance) {
		this.name = name;
		this.balance = initialBalance;
		Random r = new Random();
		this.account = r.nextInt(100);
	}
	
	@Override 
	public int getAccount() {
		// TODO Auto-generated method stub
		return this.account;
	}

	@Override
	public void setAccount(int account) {
		// TODO Auto-generated method stub
		this.account = account;
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

}
