package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		float amount =  Float.parseFloat(request.getParameter("amount"));

		  // Acceder a db
		  // Buscar elemento con account number = account
		  // Crear objeto bc
		  // Mientras tanto se crea un objeto bc mock provisional
		  BankClient bc = new BankClient(username, (float) amount); // provisional
		  String message = "Su operación se ha realizado con éxito";
//		  String message = "";
		  request.setCharacterEncoding("UTF-8");
		  request.setAttribute("name", username);
		  DecimalFormat df = new DecimalFormat("#.00"); 
		  request.setAttribute("balance", df.format(amount));
		  request.setAttribute("account", bc.getAccount());
		  request.setAttribute("msg", message);
		  request.setAttribute("icon", "ok");
	      request.getRequestDispatcher("/results.jsp").forward(request, response);      
	}

}
