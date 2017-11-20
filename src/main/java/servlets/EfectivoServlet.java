package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.Bank;
import clients.BankClient;

/**
 * Servlet implementation class EfectivoServlet
 */
public class EfectivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfectivoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long account = (long) 0;
		float amount = 0;
		int ok = 1;
		try {
			account = Long.parseLong(request.getParameter("account"));
		} catch(Exception e) {
			ok = 0;
		}
		try {
			amount =  Float.parseFloat(request.getParameter("amount"));
			if (amount < 0 ) {
				ok = 0;
			}
		} catch (Exception e) {
			ok = 0;
		}
		String operation = request.getParameter("operation");
		request.setCharacterEncoding("UTF-8");
		String message = "Su operación se ha realizado con éxito";
		HttpSession session = request.getSession();
		 
		BankClient bc = null;
		if (ok == 1) {
			if (operation.equals("ingresar")) {
				bc = Bank.getBank().ingresar(account, amount);
			} else if (operation.equals("retirar")) {
				bc = Bank.getBank().retirar(account, amount);
			}
		}
		if (bc != null ) {
			session.setAttribute("clientBean", bc);
			request.setAttribute("msg", message);
			request.setAttribute("icon", "ok");
		} else {		
			session.setAttribute("clientBean", null);
			message= "Ha habido un error con su petición";
			request.setAttribute("msg", message);
			request.setAttribute("icon", "remove");
		}
		
		
		 String hostname ="";
		 InetAddress ip = null;
	     try {
	        ip = InetAddress.getLocalHost();
	        hostname = ip.getHostName();
	     } catch (UnknownHostException e) {}
	     request.setAttribute("ip", hostname );
	     
	     
		request.setAttribute("msg", message);
	    request.getRequestDispatcher("/results.jsp").forward(request, response);      		
	}

}
