package operations;

import java.io.*;
import java.util.List;

import clients.BankClient;

/**
 * Objeto operación
 */
public class Operation implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Tipo de operación
	 */
	private Operations operation;
	/**
	 * Nº de cuenta
	 */
	private Long id;
	/**
	 * Nombre del cliente
	 */
	private String name;
	/**
	 * Nombre del znode que origina la operación
	 */
	private String src = "";
	/**
	 * Saldo en la cuenta o cantidad de dinero a ingresar/retirar/transferir
	 */
	private float balance;
	/**
	 * Otro nº de cuenta (para transferencias)
	 */
	private Long other;
	/**
	 * Referencia de donde descargar la URL inicial
	 */
	private String dbRef;
	/**
	 * Lista de clientes
	 */
	private List<BankClient> list;
	
	/**
	 * Constructor
	 * @param operation Tipo de operación
	 * @param id Nº de cuenta
	 * @param name Nombre del cliente
	 * @param balance Saldo en la cuenta o cantidad de dinero a ingresar/retirar/transferir
	 * @param other Otro nº de cuenta (para transferencias)
	 */
	public Operation(Operations operation, Long id, String name, float balance, Long other) {
		super();
		this.operation = operation;
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.other = other;
		this.dbRef = "";
		this.list = null;
	}

	/**
	 * Constructor alternativo
	 * @param operation Tipo de operación
	 * @param dbRef URL de la DB
	 */
	public Operation(Operations operation, String dbRef) {
		this.operation = operation;
		this.id = 0L;
		this.name = "";
		this.balance = 0;
		this.other = 0L;
		this.dbRef = dbRef;
		this.list = null;
	}
	
	
	/**
	 * Constructor alternativo
	 * @param operation Tipo de operación
	 * @param list Lista de clientes
	 */
	public Operation(Operations operation, List<BankClient> list) {
		this.operation = operation;
		this.id = 0L;
		this.name = "";
		this.balance = 0;
		this.other = 0L;
		this.dbRef = "";
		this.list = list;
	}

	/**
	 * Getter del tipo de operación
	 * @return Tipo de operación
	 */
	public Operations getOperation() {
		return operation;
	}

	/**
	 * Setter del tipo de operación 
	 * @param operation Tipo de operación
	 */
	public void setOperation(Operations operation) {
		this.operation = operation;
	}

	/**
	 * Getter del nº de cuenta
	 * @return Nº de cuenta
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter del nº de cuenta
	 * @param id Nº de cuenta
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter del nombre del cliente
	 * @return Nombre del cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del nombre del cliente
	 * @param name Nombre del cliente
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de la cantidad monetaria
	 * @return Cantidad monetaria
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * Setter de la cantidad monetaria
	 * @param balance Cantidad monetaria
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * Getter de la cuenta bancaria adicional
	 * @return Nº de cuenta
	 */
	public Long getOther() {
		return other;
	}

	/**
	 * Setter de la cuenta bancaria adicional
	 * @param other Nº de cuenta
	 */
	public void setOther(Long other) {
		this.other = other;
	}

	/**
	 * Getter de la URL de la DB del lider
	 * @return URL
	 */
	public String getdbRef() {
		return dbRef;
	}

	/**
	 * Setter de la URL de la DB del lider
	 * @param dbRef URL
	 */
	public void setdbRef(String dbRef) {
		this.dbRef = dbRef;
	}

	/**
	 * Getter del znode de origen de la operación
	 * @return Id del znode
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Setter del znode de origen de la operación
	 * @param src Id del znode
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * Serializador del objeto
	 * @param obj Objeto a serializar
	 * @return Objeto convertido a array de bytes
	 * @throws IOException Error de conversión
	 */
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	/**
	 * Deserializador del objeto
	 * @param data Array de bytes a deserializar
	 * @return Objeto recompuesto
	 * @throws IOException Error de conversión
	 * @throws ClassNotFoundException Clase Operation no encontrada
	 */
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	public List<BankClient> getList() {
		return list;
	}

	public void setList(List<BankClient> list) {
		this.list = list;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Operation [operation=" + operation + ", id=" + id + ", name=" + name + ", ip=" + src + ", balance="
				+ balance + ", list=" + list +", other=" + other + ", dbRef=" + dbRef + "]";
	}







}
