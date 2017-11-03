package bank;

import java.util.List;

import clients.BankClient;
import model.BankDBImpl;
import operations.*;
import zookeeper.MsgHandler;
 
public class Bank implements BankI {
	private static Bank bank;
	
	public Bank () {}
	
	public static Bank getBank() {
		if (bank == null) {
			bank = new Bank();
		} 
		return bank;
	}
	
	@Override
	public BankClient crearCliente(Long id, String name, float amount) {
		Operation op = new Operation(Operations.CREAR, id , name, amount, 0L);
		MsgHandler.send(op);
		BankDBImpl dao = BankDBImpl.getInstance();
	    return dao.crearCliente(id, name, amount);
	}

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

	@Override
	public BankClient consultarSaldo(Long account) {
		BankDBImpl dao = BankDBImpl.getInstance();
	    return dao.consultarSaldo(account);
	}

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

	public void newSucursal() {
		BankDBImpl dao = BankDBImpl.getInstance();
	    List<BankClient> list =  dao.lista();
		Operation op = new Operation(Operations.ESTADO, list);
		MsgHandler.send(op);
	}
	
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
	
	@Override
	public void deleteAll() {
		BankDBImpl dao = BankDBImpl.getInstance();
		dao.deleteAll();
	}

	@Override
	public List<BankClient> lista() {
		return null;
	}

}