package bank;

import java.util.List;

import clients.BankClient;

/**
 * Interfaz de operaciones bancarias
 * 
 */

public interface BankI {
	/**
	 * Operación crear cliente nuevo
	 * @param id Nº de cuenta
	 * @param name Nombre de cliente
	 * @param amount Balance inicial
	 * @return Objeto BankClient del cliente creado
	 */
	public BankClient crearCliente(Long id, String name, float amount);

	/**
	 * Operación ingresar dinero en cuenta
	 * @param account Nº de cuenta
	 * @param amount Cantidad a ingresar
	 * @return Objeto BankClient del cliente actualizado
	 */
	public BankClient ingresar(Long account, float amount);

	/**
	 * Operación retirar dinero en cuenta
	 * @param account Nº de cuenta
	 * @param amount Cantidad a extraer
	 * @return Objeto BankClient del cliente actualizado
	 */
	public BankClient retirar(Long account, float amount);

	/**
	 * Operación borrar cuenta
	 * @param account Nº de cuenta
	 * @return Objeto BankClient del cliente borrado
	 */
	public BankClient borrar(Long account);

	/**
	 * Operación consultar saldo de un cliente
	 * @param account Nº de cuenta
	 * @return Objeto BankClient del cliente
	 */
	public BankClient consultarSaldo(Long account);

	/**
	 * Operación transferir dinero de una cuenta a otra
	 * @param origin Cuenta de origen
	 * @param target Cuenta de destino
	 * @param amount Cantidad a transferir
	 * @return Lista en la que el primer elemento es el objeto BankClient con la cuenta origen y el segundo, el de la cuenta destino. 
	 */
	public List<BankClient> transferir(Long origin, Long target, float amount);

	/**
	 * Operación borrar toda la base de datos
	 */
	public void deleteAll();

	/**
	 * Operación consultar la lista de clientes completa
	 * @return Lista de clientes
	 */
	public List<BankClient> lista();


}