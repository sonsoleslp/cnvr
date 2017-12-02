package zookeeper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import model.BankDBImpl;
import operations.*;
/**
 * Clase de integración entre la aplicación y el cluster de Zookeeper
 *
 */
public class ZKIntegration implements Watcher {

	/**
	 * Constante que indica si se está en modo DEBUG, haciendo que se impriman por consola los logs de las operaciones
	 */
	private static final Boolean DEBUG = false;
	/**
	 * Instancia de la clase Singleton ZKIntegration
	 */
	private static ZKIntegration zki;

	/**
	 * Instancia de Zookeeper
	 */
	static ZooKeeper zk = null;
	/**
	 * Path del znode de elección de leader
	 */
	private final static String PATH_ELECTION_ROOT = "/election";
	/**
	 * Path del znode de operaciones
	 */
	private final static String PATH_OPERATION = "/operation";
	/**
	 * Mutex
	 */
	static Integer mutex;
	/**
	 * Id del znode
	 */
	private static String myId = "";
	/**
	 * Operación vacía inicial con la que se inicializa el znode de Operaciones
	 */
	public static final Operation initialOperation = new Operation(Operations.ESTADO, "");

	/**
	 * Tamaño inicial de la lista de miembros de la vista
	 */
	public static int listSize = 0;

	private static String url = "";

	/**
	 * Getter de la instancia de Zookeeper
	 * @return Instancia de Zookeeper
	 */
	public static ZooKeeper getZk() {
		return zk;
	}

	/**
	 * Setter de la instancia de Zookeeper
	 * @param zk Instancia de Zookeeper
	 */
	public static void setZk(ZooKeeper zk) {
		ZKIntegration.zk = zk;
	}

	/**
	 * Getter del id de miembro del servidor
	 * @return Identificador
	 */
	public static String getMyId() {
		return myId;
	}

	/**
	 * Setter del id de miembro del servidor
	 * @param myId Identificador
	 */
	public void setMyId(String myId) {
		ZKIntegration.myId = myId;
	}
	/**
	 * Constructor de la clase
	 */
	private ZKIntegration() {

	}

	/**
	 * Devuelve la instancia única de la clase
	 * @return Instancia de ZKIntegration
	 */
	public static ZKIntegration getInstance() {
		if (zki == null) {
			zki = new ZKIntegration();
		} 
		return zki;
	}

	/**
	 * Inicializa la conexión con Zookeeper y crea los znodes necesarios
	 * @param address IP de Zookeeper
	 */
	public void init (String address) {
		if(zk == null){
			try {
				p("Starting ZK:");
				zk = new ZooKeeper(address, 3000, this);
				createNodes();
				mutex = new Integer(-1);
				p("Finished starting ZK: " + zk);
			} catch (KeeperException | InterruptedException | IOException e) {
				p(e.toString());
				zk = null;
			}
		}
	}
	/**
	 * Crea todos los znodes necesarios. Se llama al crear cada proceso
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void createNodes() throws  KeeperException, InterruptedException, IOException{
		Stat stat = zk.exists(PATH_ELECTION_ROOT, this);
		byte[] ini = Operation.serialize(initialOperation);
		if (stat == null) {
			String root = zk.create(PATH_ELECTION_ROOT, ini, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			p("Created election path:"+ root );
		}

		myId = zk.create(PATH_ELECTION_ROOT+"/m_", ini, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		zk.exists(myId, this);
		p("Created member id: " + myId);

		//En este nodo se escribirán las operaciones que todos leerán
		Stat statmsg = zk.exists(PATH_OPERATION,this);
		if (statmsg == null) {
			String root = zk.create(PATH_OPERATION, ini, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			p("Created operations path:"+ root );
		}
		newLeaderElection();

	}

	
	/**
	 * Devuelve la URL del servidor en la red privada más interna
	 * @return URL
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * Especifica la URL del servidor en la red privada más interna
	 * @param url URL
	 */
	public static void setUrl(String url) {
		ZKIntegration.url = url;
	}

	/**
	 * Process del Watcher. Maneja todos los eventos de los znodes.
	 */
	synchronized public void process(WatchedEvent event) {
		try {
			zk.exists(event.getPath(), true);
			synchronized (mutex) {
				p("Process: " + event.getType());
				mutex.notifyAll();
				switch(event.getType()) {
				case NodeChildrenChanged:
					p("NodeChildrenChanged " + event.getPath());
					boolean bool = newProcesses(); // Indica que se ha a�adido un nodo nuevo
					// Si se ha añadido un nodo nuevo y soy el leader, le mando el valor al nodo nuevo
					if (bool && myId.equals(PATH_ELECTION_ROOT + "/"  + idLeader())) { 
						p("Sending new state url" + ZKIntegration.getUrl());
						Operation op = new Operation(Operations.ESTADO, ZKIntegration.getUrl());
						String last = idlast();
						Thread.sleep(5000);
						zk.setData(PATH_ELECTION_ROOT + "/" + last,Operation.serialize(op),-1);
					}
					if (event.getPath().toLowerCase().contains(PATH_ELECTION_ROOT)) {
						newLeaderElection();
					}
					break;
				case NodeCreated:
					p("NodeCreated" + event.getPath());
					break;   
				case NodeDataChanged:
					p("NodeDataChanged" + event.getPath());
					byte[] dataLeader =	zk.getData(event.getPath(), true, null);
					MsgHandler.receive(dataLeader, myId);
					break;   
				case NodeDeleted:
					p("NodeDeleted" + event.getPath());
					String path = event.getPath().toLowerCase();
					if(path.contains(PATH_ELECTION_ROOT)) {
						newLeaderElection();
					}
					break;
				case None:
					break;
				}
			}
		} catch (KeeperException | ClassNotFoundException | InterruptedException | IOException e1) {
			p(e1);
			e1.printStackTrace();
		}
	}

	/**
	 * Elección de leader
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void newLeaderElection() throws KeeperException, InterruptedException, IOException{
		String leader = idLeader();
		leader = PATH_ELECTION_ROOT + "/" + leader;
		zk.exists(leader, this); //true
		p("Leader is " + leader);

	}

	/**
	 * Calcula el id del leader
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String idLeader() throws KeeperException, InterruptedException {
		List<String> list = zk.getChildren(PATH_ELECTION_ROOT, this);
		listSize = list.size();
		String leader=list.get(0);
		for(String i: list) {
			if(leader.compareTo(i)>0) leader=i;
		}
		return leader;
	}

	/**
	 * Comprueba si hay más procesos en la nueva lista de miembros que en la anterior. 
	 * Es decir, comprueba si hay nuevos procesos
	 * @return true si hay nuevos procesos, false si hay igual o menos
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public boolean newProcesses() throws KeeperException, InterruptedException {
		List<String> list = zk.getChildren(PATH_ELECTION_ROOT, this);
		return list.size() > listSize;
	}

	/**
	 * Comprueba cual es el último proceso de la lista, el último que se ha creado
	 * @return Identificador del último proceso
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String idlast() throws KeeperException, InterruptedException {
		List<String> list = zk.getChildren(PATH_ELECTION_ROOT, this);
		String last=list.get(list.size()-1);
		for(String i: list) {
			if(last.compareTo(i)<0) last=i;
		}
		return last;
	}

	/**
	 * Imprime una lista (de procesos)
	 * @param list Lista de procesos
	 */
	@SuppressWarnings("unused")
	private void printListMembers (List<String> list) {
		p("Members:" + list.size());
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			p(string + ", ");				
		}
		p("");
	}

	/**
	 * Escribe la operación en el nodo /operations para que todos lo reciban.
	 * @param i Nº a sumar
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void sendOperation(Operation operation) throws KeeperException, InterruptedException, IOException, ClassNotFoundException {
		operation.setSrc(getMyId());
		byte[] op = Operation.serialize(operation);
		String path = PATH_OPERATION;
		Stat stat = zk.exists(path, zki);
		while (true) {
			synchronized (mutex) {
				if (stat!=null) {
					zk.setData(path, op, -1);
				}
				return;
			}}

	}


	/**
	 * Atajo para hacer logs más facilmente
	 * @param s
	 */
	public static void p(Object s){
		if (DEBUG) {
			System.out.println(s);
		}
	}

}