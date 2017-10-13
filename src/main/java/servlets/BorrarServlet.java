package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.BankClient;
import model.ClientDAO;
import model.ClientImpl;

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
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Long account = Long.parseLong(request.getParameter("account"));
		  
		  ClientDAO dao = ClientImpl.getInstance();
		  BankClient bc = dao.borrar(account);
//		  String message = "Su operación se ha realizado con éxito";
		  String message = "Se ha borrado su cuenta";
		  request.setCharacterEncoding("UTF-8");
		  request.setAttribute("name", bc.getName());
		  DecimalFormat df = new DecimalFormat("#0.00"); 
		  request.setAttribute("balance", df.format(bc.getBalance()));
		  request.setAttribute("account", bc.getAccount());
		  request.setAttribute("msg", message);
		  request.setAttribute("icon", "ok");
	      request.getRequestDispatcher("/results.jsp").forward(request, response);      
	}

}
