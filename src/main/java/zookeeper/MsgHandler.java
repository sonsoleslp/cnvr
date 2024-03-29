package zookeeper;

import java.io.IOException;

import bank.Bank;
import operations.Operation;

/**
 * Manejador de mensajes entre el banco y ZK 
 */
public class MsgHandler {

	/**
	 * Escribir una operación en Zookeeper
	 * @param op 
	 */
	public static void send(Operation op) {
		try {
			System.out.println("ORIGINAL OPERATION" + op.toString());
			ZKIntegration.sendOperation(op);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void receive(byte[] msg, String myId) throws ClassNotFoundException, IOException {

		Operation op = (Operation) Operation.deserialize(msg);
		String id = op.getSrc();

		System.out.println("Received operation: " + op.toString());
		System.out.println("From " + myId + " to " + id);
		if (!id.equals(myId)) {
			Bank.getBank().externalOperation(op);
		}
	}


}
