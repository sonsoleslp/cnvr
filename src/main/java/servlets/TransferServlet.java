package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.Bank;
import beans.BankClientBean;
import clients.BankClient;
import model.BankDBImpl;

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
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long origin = Long.parseLong(request.getParameter("origin"));
		Long target = Long.parseLong(request.getParameter("target"));
		float amount =  Float.parseFloat(request.getParameter("amount"));
	  
		List<BankClient> lista = Bank.getBank().transferir(origin, target, amount);
		BankClient bc_origin = lista.get(0);
		BankClient bc_target= lista.get(1);
		
		String message = "Su transferencia se ha realizado con éxito";
//		String message = "";
		request.setCharacterEncoding("UTF-8");
		BankClientBean cb1 = new BankClientBean(bc_origin);
		BankClientBean cb2 = new BankClientBean(bc_target);
		HttpSession session = request.getSession();
		session.setAttribute("clientBean1", cb1);
		session.setAttribute("clientBean2", cb2);
		  
		DecimalFormat df = new DecimalFormat("#0.00"); 
		request.setAttribute("amount", df.format(amount));

		
		request.setAttribute("msg", message);
		request.setAttribute("icon", "ok");
	    request.getRequestDispatcher("/transfer.jsp").forward(request, response);      		
	}

}
