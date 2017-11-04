package servlets;
 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import zookeeper.ZKIntegration;

public class StartUpListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    @Override
    public void contextInitialized(ServletContextEvent e) {
    	
        System.out.println("App initialized");
        System.out.println(e);
        System.out.println(e.getServletContext().getServerInfo().toString());
        System.out.println(e.getSource());
        
        // Código al inicializar la aplicación
        // Ej.: Conectar con zookeeper
//        ZKIntegration zki = new ZKIntegration("localhost:80");
        ZKIntegration cl = new ZKIntegration("");
        cl.init("172.28.11.2:2181");

    }
 
    // Release the EntityManagerFactory:
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("The End");
    }
}