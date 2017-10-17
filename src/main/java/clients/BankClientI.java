package clients;

public interface BankClientI {
	Long getAccount();
	void setAccount(Long account);
	String getName();
	void setName(String name);
	float getBalance();
	void setBalance(float balance);
	String toString();
}
