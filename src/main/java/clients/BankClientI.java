package clients;

/**
 * Interfaz cliente
 *
 */
public interface BankClientI {
	/**
	 * Getter del nº de cuenta
	 * @return Nº de cuenta
	 */
	Long getAccount();
	/**
	 * Setter del nº de cuenta
	 * @param account Nº de cuenta
	 */
	void setAccount(Long account);
	/**
	 * Getter del nombre del cliente
	 * @return Nombre del cliente
	 */
	String getName();
	/**
	 * Setter del nombre del cliente
	 * @param name Nombre del cliente
	 */
	void setName(String name);
	/**
	 * Getter del saldo de la cuenta
	 * @return Saldo de la cuenta
	 */
	float getBalance();
	/**
	 * Setter del saldo de la cuenta
	 * @param balance Saldo de la cuenta
	 */
	void setBalance(float balance);
	/**
	 * Conversor a string
	 * @return Objeto cliente convertido a cadena de texto
	 */
	String toString();
}
