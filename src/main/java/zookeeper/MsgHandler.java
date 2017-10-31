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
 			System.out.println("ORIGINAL OPERATION" + op.toString());
			ZKIntegration cl = new ZKIntegration("");
			cl.sendOperation(op);
 			//send ? zookeeper
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void receive(byte[] msg, String myId) throws ClassNotFoundException, IOException {
		
		Operation op = (Operation) Operation.deserialize(msg);
		String id = op.getIp();
		
		System.out.println(op.toString());
		if (!id.equals(myId)) {
			Bank.getBank().externalOperation(op);
		}
	}
	
	
}
