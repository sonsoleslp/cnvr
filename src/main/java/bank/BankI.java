package bank;

import java.util.List;

import clients.BankClient;

/**
 * Interfaz para interactuar con la DB
 * 
 * @author Sonsoles
 *
 */
public interface BankI {
	
	public BankClient crearCliente(String name, float amount);
 
	public BankClient ingresar(Long account, float amount);
	
	public BankClient retirar(Long account, float amount);
	
	public BankClient borrar(Long account);
	
	public BankClient consultarSaldo(Long account);

	public List<BankClient> transferir(Long origin, Long target, float amount);
	
	public void deleteAll();
	
	public List<BankClient> lista();

	
}