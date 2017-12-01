package bank;

import java.util.List;

import clients.BankClient;
import model.BankDBImpl;
import operations.*;
import zookeeper.MsgHandler;

/**
 * Recibe operaciones del manejador de mensajes de ZK o de los servlets y las ejecuta en la DB local
 *
 */
public class Bank implements BankI {

	/**
	 * Singleton instance
	 */
	private static Bank bank;

	/**
	 * Constructor
	 */
	public Bank () {}

	/**
	 * Getter de la instancia Singleton 
	 * @return Bank instance
	 */
	public static Bank getBank() {
		if (bank == null) {
			bank = new Bank();
		} 
		return bank;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BankClient crearCliente(Long id, String name, float amount) {
		Operation op = new Operation(Operations.CREAR, id , name, amount, 0L);
		MsgHandler.send(op);
		BankDBImpl dao = BankDBImpl.getInstance();
		return dao.crearCliente(id, name, amount);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BankClient ingresar(Long account, float amount) {
		BankDBImpl dao = BankDBImpl.getInstance();
		if (dao.consultarSaldo(account) != null) {
			Operation op = new Operation(Operations.INGRESAR, account , "", amount, 0L);
			MsgHandler.send(op);
			return dao.ingresar(account, amount);
		}
		return null;

	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BankClient retirar(Long account, float amount) {
		BankDBImpl dao = BankDBImpl.getInstance();
		if (dao.consultarSaldo(account) != null) {
			Operation op = new Operation(Operations.RETIRAR, account , "", amount, 0L);
			MsgHandler.send(op);
			return dao.retirar(account, amount);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BankClient borrar(Long account) {
		BankDBImpl dao = BankDBImpl.getInstance();
		if (dao.consultarSaldo(account) != null) {
			Operation op = new Operation(Operations.BORRAR, account , "", 0, 0L);
			MsgHandler.send(op);
			return dao.borrar(account);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BankClient consultarSaldo(Long account) {
		BankDBImpl dao = BankDBImpl.getInstance();
		return dao.consultarSaldo(account);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BankClient> transferir(Long origin, Long target, float amount) {

		BankDBImpl dao = BankDBImpl.getInstance();
		if (dao.consultarSaldo(origin) != null && dao.consultarSaldo(target) != null) {
			Operation op = new Operation(Operations.TRANSFERIR, origin , "", amount, target);
			MsgHandler.send(op);
			return dao.transferir(origin, target, amount);
		}	
		return null;
	}

	/**
	 * Envía la lista de clientes actual a una nueva sucursal bancaria 
	 */
	public void newSucursal() {
		BankDBImpl dao = BankDBImpl.getInstance();
		List<BankClient> list =  dao.lista();
		Operation op = new Operation(Operations.ESTADO, list);
		MsgHandler.send(op);
	}

	/**
	 * Recibe una operación de Zookeeper y la convierte en una operación sobre la DB local
	 * @param op Operación a realizar
	 */
	public void externalOperation(Operation op) {
		BankDBImpl dao = BankDBImpl.getInstance();
		switch(op.getOperation()) {
		case ESTADO:
			dao.populate(op.getList());
			break;
		case CREAR:
			dao.crearCliente(op.getId(), op.getName(), op.getBalance());
			break;	
		case BORRAR:
			dao.borrar(op.getId());
			break;
		case INGRESAR:
			dao.ingresar(op.getId(), op.getBalance());
			break;
		case RETIRAR:
			dao.retirar(op.getId(), op.getBalance());
			break;
		case TRANSFERIR:
			dao.transferir(op.getId(), op.getOther(), op.getBalance());
			break;
		default:
			break;
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		BankDBImpl dao = BankDBImpl.getInstance();
		dao.deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BankClient> lista() {
		BankDBImpl dao = BankDBImpl.getInstance();
		return dao.lista();
	}

}