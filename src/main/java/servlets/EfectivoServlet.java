package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ClientBean;
import clients.BankClient;
import model.ClientDAO;
import model.ClientImpl;

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
		try {
			account = Long.parseLong(request.getParameter("account"));
		} catch(Exception e) {
			response.getWriter().append("Cuenta errónea");
			return;
		}
		try {
			amount =  Float.parseFloat(request.getParameter("amount"));
		} catch (Exception e) {
			response.getWriter().append("Cantidad errónea");
		}
		ClientDAO dao = ClientImpl.getInstance();
		String operation = request.getParameter("operation");
		 
		BankClient bc = null;
		if (operation.equals("ingresar")) {
			bc = dao.ingresar(account, amount);
		} else if (operation.equals("retirar")) {
			bc = dao.retirar(account, amount);;
		}
		
		DecimalFormat df = new DecimalFormat("#0.00"); 

		String message = "Su operación se ha realizado con éxito";
//		String message = "";
		request.setCharacterEncoding("UTF-8");
		ClientBean cb = new ClientBean(bc);
		HttpSession session = request.getSession();
		session.setAttribute("clientBean", cb);
		request.setAttribute("msg", message);
		request.setAttribute("icon", "ok");
	    request.getRequestDispatcher("/results.jsp").forward(request, response);      		
	}

}
