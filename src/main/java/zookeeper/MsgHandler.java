package zookeeper;

import java.io.IOException;

import bank.Bank;
import model.BankDBImpl;
import operations.Operation;
public class MsgHandler {

	public static void send(Operation op) {
		sendToPath(op, "");
	}
	
	public static void sendToPath(Operation op, String path) {
		try {
			byte[] msg = Operation.serialize(op);
			System.out.println("ORIGINAL OPERATION" + op.toString());
			CounterLeader cl = new CounterLeader("");
			cl.sendOperation(msg);
 			//send ? zookeeper
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void receive(byte[] msg) throws ClassNotFoundException, IOException {
		Operation op = (Operation) Operation.deserialize(msg);
		System.out.println(op.toString());
		Bank.getBank().externalOperation(op);
		
	}
	
	
}
