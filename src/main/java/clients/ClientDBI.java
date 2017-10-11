package clients;

public interface ClientDBI <BankClientI> {
	public ServiceStatus create (BankClientI client);
	public ServiceStatus update (BankClientI client);
	public BankClientI read(String name);
	public BankClientI read(int account);
	public ServiceStatus delete(int account);
}
