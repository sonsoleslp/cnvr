package servlets;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import zookeeper.ZKIntegration;
/**
 * Context Listener que se lanza al inicializar la aplicación. Inicializa la conexión con ZK. 
 *
 */
public class StartUpListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent e) {

		System.out.println("App initialized");

		String zkIP = System.getProperty("zkIP");
		if (zkIP == null) {
			zkIP = "172.28.11.2:2181";
			System.out.println("ZK port not provided. Using 2181 by default");
		} else {
			System.out.println("Specified ZK port: " + zkIP);
		}
		
		ZKIntegration.getInstance().init(zkIP);

		String url = System.getProperty("url");
		if (url != null) {
			ZKIntegration.setUrl(url+"/"+"crearcuenta");
			
		}
		
		System.out.println(zkIP + " " + url);
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("The End");
	}
}