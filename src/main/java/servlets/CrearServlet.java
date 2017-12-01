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
 * Servlet implementation class CrearServlet
 */
public class CrearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearServlet() {
		super();
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");        
		String username = request.getParameter("username");
		float amount =  0;
		String initialAmount = request.getParameter("amount");
		try {
			amount = Long.parseLong(initialAmount);
			if (amount < 0 ){ amount = 0; }
		} catch (Exception e) {
			amount = 0;
		}

		if (username == null) {
			username = "Anónimo";
		}
		Long id =  (long) (Math.random() * 100000000000000L);
		BankClient bc = Bank.getBank().crearCliente(id, username, amount);
		String message = "Su operación se ha realizado con éxito";
		request.setAttribute("name", bc.getName());
		BankClient cb = bc;
		HttpSession session = request.getSession();
		session.setAttribute("clientBean", cb);
		request.setAttribute("msg", message);
		request.setAttribute("icon", "ok");

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
