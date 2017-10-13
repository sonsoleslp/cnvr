package servlets;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ClientBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
	  
	  ClientDAO dao = ClientImpl.getInstance();
      BankClient bc = dao.consultarSaldo(account);
//	  String message = "Su operación se ha realizado con éxito";
	  String message = "";
	  request.setCharacterEncoding("UTF-8");
	  ClientBean cb = new ClientBean(bc);
	  HttpSession session = request.getSession();
	  session.setAttribute("clientBean", cb);
	  
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
          res += "****************************************\n";
      }
		response.getWriter().append(res);

	  
  }
}