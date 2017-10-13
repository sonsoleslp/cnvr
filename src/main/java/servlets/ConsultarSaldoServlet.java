package servlets;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import clients.BankClient;
import model.ClientDAO;
import model.ClientImpl;

public class ConsultarSaldoServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
	        
	  Long account = Long.parseLong(request.getParameter("account"));
	  
	  // Acceder a db
	  // Buscar elemento con account number = account
	  // Crear objeto bc
	  // Mientras tanto se crea un objeto bc mock provisional
	  
//	  BankClient bc = new BankClient("SLP", (float) 3333.33); // provisional
//	  bc.setAccount(account); // provisional
	  ClientDAO dao = ClientImpl.getInstance();
      BankClient bc = dao.consultarSaldo(account);
//	  String message = "Su operaci�n se ha realizado con �xito";
	  String message = "";
	  request.setCharacterEncoding("UTF-8");
	  request.setAttribute("name", bc.getName());
	  request.setAttribute("balance", bc.getBalance());
	  request.setAttribute("account", bc.getAccount());
	  request.setAttribute("msg", message);
      request.getRequestDispatcher("/results.jsp").forward(request, response);       

  }
 
  @Override
  public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
	  ClientDAO dao = ClientImpl.getInstance();
	  List<BankClient> lista = dao.lista();
	  String res = "";
	  for(BankClient bc : lista) {
          res += bc.toString();
          res += "****************************************";
      }
		response.getWriter().append(res);

	  
  }
}