package operations;

import java.io.*;
import java.util.List;

import clients.BankClient;

public class Operation implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Operations operation;
	private Long id;
	private String name;
	private float balance;
	private Long other;
	private List<BankClient> list;
	
	public Operation(Operations operation, Long id, String name, float balance, Long other) {
		super();
		this.operation = operation;
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.other = other;
		this.list = null;
	}
	
	public Operation(Operations operation, List<BankClient> list) {
		this.operation = operation;
		this.id = 0L;
		this.name = "";
		this.balance = 0;
		this.other = 0L;
		this.list = list;
	}
	
	
	public Operations getOperation() {
		return operation;
	}




	public void setOperation(Operations operation) {
		this.operation = operation;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public float getBalance() {
		return balance;
	}




	public void setBalance(float balance) {
		this.balance = balance;
	}




	public Long getOther() {
		return other;
	}




	public void setOther(Long other) {
		this.other = other;
	}

	public List<BankClient> getList() {
		return list;
	}

	public void setList(List<BankClient> list) {
		this.list = list;
	}

	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}

	@Override
	public String toString() {
		return "Operation [operation=" + operation + ", id=" + id + ", name=" + name + ", balance=" + balance
				+ ", other=" + other + "]";
	}

	
	
}
