package servlets;
 


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import zookeeper.ZKIntegration;

public class StartUpListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    @Override
    public void contextInitialized(ServletContextEvent e) {
    	
        System.out.println("App initialized");
        
        String zkPort = System.getProperty("zk");
        if (zkPort == null) {
        	zkPort = "2181";
            System.out.println("ZK port not provided. Using 2181 by default");
        } else {
            System.out.println("Specified ZK port: " + zkPort);

        }

        ZKIntegration cluster = new ZKIntegration("");
        cluster.init("172.28.11.2:" + zkPort);

    }
 
    // Release the EntityManagerFactory:
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("The End");
    }
}