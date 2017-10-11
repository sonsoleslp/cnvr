package clients;

public interface BankClientI {
	int getAccount();
	void setAccount(int account);
	String getName();
	void setName(String name);
	float getBalance();
	void setBalance(float balance);
	String toString();
}
