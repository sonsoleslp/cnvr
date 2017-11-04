package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.Bank;

/**
 * Servlet implementation class BorrarServlet
 */
public class BorrarTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarTodoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bank.getBank().deleteAll();
		request.setCharacterEncoding("UTF-8");
		 String hostname ="";
		 InetAddress ip = null;
	     try {
	        ip = InetAddress.getLocalHost();
	        hostname = ip.getHostName();
	     } catch (UnknownHostException e) {}
	     request.setAttribute("ip", hostname );
		request.getRequestDispatcher("/index.jsp").forward(request, response);      		
	}

}
