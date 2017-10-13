package servlets;
 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import zookeeper.ZKIntegration;
public class StartUpListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    @Override
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("App initialized");
        // Código al inicializar la aplicación
        // Ej.: Conectar con zookeeper
        System.out.println(e);
        ZKIntegration zki = new ZKIntegration("localhost:80");

    }
 
    // Release the EntityManagerFactory:
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("The End");
    }
}