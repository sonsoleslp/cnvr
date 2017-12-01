package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.Bank;
import clients.BankClient;

/**
 * Servlet implementation class TransferServlet
 */
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferServlet() {
		super();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ok = 1;
		Long origin = (long) 0;
		try {
			origin = Long.parseLong(request.getParameter("origin"));
		} catch(Exception e) {
			ok = 0;
		}
		Long target = (long) 0;
		try {
			target = Long.parseLong(request.getParameter("target"));
		} catch(Exception e) {
			ok = 0;
		}
		float amount =  0;
		try {
			amount = Float.parseFloat(request.getParameter("amount"));
			if (amount < 0) {
				ok = 0;
			}
		} catch(Exception e) {
			ok = 0;
		}

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String message = "Su transferencia se ha realizado con éxito";
		List<BankClient> lista = null;
		if (ok == 1) {
			lista = Bank.getBank().transferir(origin, target, amount);
		}
		if (lista == null || lista.isEmpty() || ok == 0) {
			request.setAttribute("icon", "remove");
			message = "Ha habido un error con su petición";
		} else {
			BankClient bc_origin = lista.get(0);
			BankClient bc_target= lista.get(1);
			session.setAttribute("clientBean1", bc_origin);
			session.setAttribute("clientBean2", bc_target);

			DecimalFormat df = new DecimalFormat("#0.00"); 
			request.setAttribute("amount", df.format(amount));
			request.setAttribute("icon", "ok");
		}
		String hostname ="";
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
		} catch (UnknownHostException e) {}
		request.setAttribute("ip", hostname );

		request.setAttribute("msg", message);
		request.getRequestDispatcher("/transfer.jsp").forward(request, response);      		
	}

}
