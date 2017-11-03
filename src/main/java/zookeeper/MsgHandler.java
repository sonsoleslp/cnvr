package zookeeper;

import java.io.IOException;

import bank.Bank;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void receive(byte[] msg, String myId) throws ClassNotFoundException, IOException {
		
		Operation op = (Operation) Operation.deserialize(msg);
		String id = op.getIp();
		
		System.out.println("Received operation: " + op.toString());
		System.out.println("From " + myId + " to " + id);
		if (!id.equals(myId)) {
			Bank.getBank().externalOperation(op);
		}
	}
	
	
}
