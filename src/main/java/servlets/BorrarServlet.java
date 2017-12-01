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
 * Servlet implementation class BorrarServlet
 */
public class BorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrarServlet() {
		super();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long account = (long) 0;
		int ok = 1;
		try {
			account = Long.parseLong(request.getParameter("account"));
		} catch(Exception e) {
			ok = 0;
		}
		BankClient bc = null;
		if (ok == 1) {
			bc = Bank.getBank().borrar(account);
		}	  
		String message = "Se ha borrado su cuenta";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (bc != null && ok == 1) {
			session.setAttribute("clientBean", bc);
			request.setAttribute("msg", message);
			request.setAttribute("linethrough", true);
			request.setAttribute("icon", "ok");
		} else {
			session.setAttribute("clientBean", null);
			message= "La cuenta solicitada no existe";
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
		request.getRequestDispatcher("/results.jsp").forward(request, response);      
	}

}
