package servlets;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BankClientBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import clients.BankClient;
import bank.Bank;
import bank.BankI;
import model.BankDBImpl;

public class ConsultarSaldoServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
	        
	  Long account = (long) 0;
	  int ok = 1;
		try {
			account = Long.parseLong(request.getParameter("account"));
		} catch(Exception e) {
			ok = 0;
		}
	  
      BankClient bc = Bank.getBank().consultarSaldo(account);
	  String message = "Su operación se ha realizado con éxito";
	  request.setCharacterEncoding("UTF-8");
	  HttpSession session = request.getSession();
	  if (bc != null  && ok == 1) {
		  BankClientBean cb = new BankClientBean(bc);
		  session.setAttribute("clientBean", cb);
		  request.setAttribute("msg", message);
		  request.setAttribute("icon", "ok");
	  } else {
		  session.setAttribute("clientBean", null);
		  message= "La cuenta solicitada no existe";
		  request.setAttribute("msg", message);
		  request.setAttribute("icon", "remove");
	  }
	  
	  
	  
	  request.setAttribute("msg", message);
      request.getRequestDispatcher("/results.jsp").forward(request, response);       

  }
 
  @Override
  public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
	  BankI dao = BankDBImpl.getInstance();
	  List<BankClient> lista = dao.lista();
	  String res = "";
	  for(BankClient bc : lista) {
          res += bc.toString();
          res += "\n****************************************\n";
      }
		response.getWriter().append(res);

	  
  }
}