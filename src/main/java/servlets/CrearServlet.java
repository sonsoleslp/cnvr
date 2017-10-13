package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BankClientBean;
import clients.BankClient;
import model.*;

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
		request.setCharacterEncoding("UTF-8");
		
		BankClientDAO dao = BankClientImpl.getInstance();
//		try {
		        
		  String username = request.getParameter("username");
		  float amount =  0;
		  String initialAmount = request.getParameter("amount");
		  try {
			  amount = Long.parseLong(initialAmount);
		  } catch (Exception e) {}

		  if (username == null) {
			  username = "Anónimo";
		  }

          BankClient bc = dao.crearCliente(username, amount);
     
		  String message = "Su operación se ha realizado con éxito";
		  request.setAttribute("name", bc.getName());
		  BankClientBean cb = new BankClientBean(bc);
		  HttpSession session = request.getSession();
		  session.setAttribute("clientBean", cb);
		  request.setAttribute("msg", message);
		  request.setAttribute("icon", "ok");
	      request.getRequestDispatcher("/results.jsp").forward(request, response);       
	      
//	      } catch (Exception e) {
//	  		response.getWriter().append("Ha habido un error con su petici�n").append(e.toString());
//
//	      } 
	}

}
