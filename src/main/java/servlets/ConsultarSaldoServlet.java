package servlets;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import clients.BankClient; 
public class ConsultarSaldoServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
	  int account = Integer.parseInt(request.getParameter("account"));
	  // Acceder a db
	  // Buscar elemento con account number = account
	  // Crear objeto bc
	  // Mientras tanto se crea un objeto bc mock provisional
	  BankClient bc = new BankClient("SLP", (float) 3333.33); // provisional
	  bc.setAccount(account); // provisional
//	  String message = "Su operación se ha realizado con éxito";
	  String message = "";
	  request.setCharacterEncoding("UTF-8");
	  request.setAttribute("name", bc.getName());
	  request.setAttribute("balance", bc.getBalance());
	  request.setAttribute("account", bc.getAccount());
	  request.setAttribute("msg", message);
      request.getRequestDispatcher("/results.jsp").forward(request, response);       

  }
 
  
}