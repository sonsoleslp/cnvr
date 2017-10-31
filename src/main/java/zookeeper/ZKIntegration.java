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
 * ZKLeader + Counter
 *
 */
public class ZKIntegration implements Watcher {
	private static final Boolean DEBUG = true;
	private static int counter;
    static ZooKeeper zk = null;
    private final String PATH_ELECTION_ROOT = "/election";
    private final String PATH_OPERATION = "/operation";
    static Integer mutex;
    private String myId = "";
    public static final Operation initialOperation = new Operation(Operations.ESTADO, null);
	public static int listSize = 0;
    public static int getCounter() {
		return counter;
	}

	public static void setCounter(int count) {
 		counter = count;
	}

	public static ZooKeeper getZk() {
		return zk;
	}

	public static void setZk(ZooKeeper zk) {
		ZKIntegration.zk = zk;
	}

	public String getMyId() {
		return myId;
	}
	
	
	public void setMyId(String myId) {
		this.myId = myId;
	}
	/**
	 * Constructor de la clase
	 * @param address IP address de Zookeeper
	 */
	public ZKIntegration(String address) {
        
    }
    
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
 		
 		this.myId = zk.create(PATH_ELECTION_ROOT+"/m_", ini, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
 		zk.exists(this.myId, this);
 		p("Created member id: " +myId);

 		//En este nodo se escribirán las operaciones que todos leerán
 		Stat statmsg = zk.exists(PATH_OPERATION,this);
 		if (statmsg == null) {
 			String root = zk.create(PATH_OPERATION, ini, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
 			p("Created operations path:"+ root );
 		}
  		newLeaderElection();

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
 						// Si se ha a�adido un nodo nuevo y soy el leader, le mando el valor al nodo nuevo
						if (bool && myId.equals("/election/" + idLeader())) { 
							Operation op = new Operation(Operations.ESTADO, BankDBImpl.getInstance().lista());
  							String last = idlast();
 							Thread.sleep(5000);
							p("Sending the initial counter to new process:" + last +". Value= " + getCounter());
 							zk.setData("/election/"+last,Operation.serialize(op),-1);
 						}
						if (event.getPath().toLowerCase().contains("/election")) {
            				newLeaderElection();
						}
            			break;
            		case NodeCreated:
            			p("NodeCreated" + event.getPath());
            			break;   
            		case NodeDataChanged:
            			p("NodeDataChanged" + event.getPath());
						byte[] dataLeader =	zk.getData(event.getPath(),true, null);
						MsgHandler.receive(dataLeader, myId);

            			break;   
            		case NodeDeleted:
            			p("NodeDeleted" + event.getPath());
            			String path = event.getPath().toLowerCase();
            			if(path.contains("/election")) {
            				newLeaderElection();
            			}
            			break;
            		case None:
            			break;
				}
          }
		} catch (KeeperException | ClassNotFoundException | InterruptedException | IOException e1) {
			e1.printStackTrace();
		}
    }

    public void newLeaderElection() throws KeeperException, InterruptedException, IOException{
		String leader = idLeader();
		leader = PATH_ELECTION_ROOT + "/" + leader;
		zk.exists(leader, this); //true
		p("Leader is " + leader);

    }

    
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
     * Comprueba si hay m�s procesos en la nueva lista de miembros que en la anterior
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
     * Comprueba cual es el �ltimo proceso de la lista, el �ltimo que se ha creado
     * @return Identificador del �ltimo proceso
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
			System.out.print(string + ", ");				
		}
		p("");

	}
    
    
    /**
     * Suma un n�mero al contador. Escribe la operaci�n en el nodo /operations para que todos lo reciban, incluido �l mismo.
     * @param i N� a sumar
     * @throws KeeperException
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void sendOperation(Operation operation) throws KeeperException, InterruptedException, IOException, ClassNotFoundException {
    	operation.setIp(myId);
    	byte[] op = operation.serialize(operation);
    	String path = PATH_OPERATION;
    	Stat stat = zk.exists(path, this);
        while (true) {
        synchronized (mutex) {
    		if (stat!=null) {
    			zk.setData(path, op, -1);
     		}
   		return;
        }}
    	
    }
    

    /**
     * Atajo para hacer logs m�s facilmente
     * @param s
     */
    public static void p(Object s){
    	if (DEBUG) {
    		System.out.println(s);
		}
    }
    
}