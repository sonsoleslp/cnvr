package zookeeper;
import java.io.IOException;
import org.apache.zookeeper.*;

import model.BankDBImpl;
import operations.*;

public class ZKIntegration implements Watcher {
	
	static ZooKeeper zk = null;
    static Integer mutex;
	private static String myId;
    String root;

	public ZKIntegration(String address) {
		if(zk == null){
            try {
                System.out.println("Starting ZK:");
                zk = new ZooKeeper(address, 3000, this);
                mutex = new Integer(-1);
                System.out.println("Finished starting ZK: " + zk);
            } catch (IOException e) {
                System.out.println(e.toString());
                zk = null;
            }
        }
	}
	
	
	
	@Override
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
