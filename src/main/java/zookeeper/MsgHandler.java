package zookeeper;

import java.io.IOException;

import model.BankDBImpl;
import operations.Operation;

public class MsgHandler {

	public static void send(Operation op) {
		try {
			byte[] msg = Operation.serialize(op);
			System.out.println("ORIGINAL OPERATION" + op.toString());
			Operation oo = (Operation) Operation.deserialize(msg);
			System.out.println("DESERIALIZED OPERATION" + oo.toString());
			//send ? zookeeper
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void receive(byte[] msg) throws ClassNotFoundException, IOException {
		Operation op = (Operation) Operation.deserialize(msg);
		
		switch(op.getOperation()) {
			case ESTADO:
				BankDBImpl.getInstance().populate(op.getList());
				break;
			case CREAR:
				BankDBImpl.getInstance().crearCliente(op.getName(), op.getBalance());
				break;	
			case BORRAR:
				BankDBImpl.getInstance().borrar(op.getId());
				break;
			case INGRESAR:
				BankDBImpl.getInstance().ingresar(op.getId(), op.getBalance());
				break;
			case RETIRAR:
				BankDBImpl.getInstance().retirar(op.getId(), op.getBalance());
				break;
			case TRANSFERIR:
				BankDBImpl.getInstance().transferir(op.getId(), op.getOther(), op.getBalance());
				break;
			default:
				break;
					
				
		}
		
	}
	
	
}
