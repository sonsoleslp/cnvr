package model;

import java.util.List;

import clients.BankClient;

/**
 * Interfaz para interactuar con la DB
 * 
 * @author Sonsoles
 *
 */
public interface ClientDAO {
	
	public BankClient crearCliente(String name, float amount);
 
	public BankClient ingresar(Long primaryKey, float amount);
	
	public BankClient retirar(Long primaryKey, float amount);
	
	public BankClient borrar(Long primaryKey);
	
	public BankClient consultarSaldo(Long primaryKey);

	public List<BankClient> transferir(Long origin, Long target, float amount);
	
	public void deleteAll();
	
	public List<BankClient> lista();

	
}