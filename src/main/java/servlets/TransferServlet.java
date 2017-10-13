package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clients.BankClient;
import model.ClientDAO;
import model.ClientImpl;

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
		// Acceder a db
		// Buscar elemento con account number = account
		// Crear objeto bc
		// Mientras tanto se crea un objeto bc mock provisional
//		BankClient bc_origin = new BankClient("SLP", (float) 3333.33); // provisional
//		bc_origin.setAccount(origin);
//		bc_origin.setBalance(bc_origin.getBalance()-amount);
		
//		BankClient bc_target = new BankClient("CNVR", (float) 1111.33); // provisional
//		bc_target.setAccount(target);
//		bc_target.setBalance(bc_target.getBalance()+amount);
		ClientDAO dao = ClientImpl.getInstance();
		List<BankClient> lista = dao.transferir(origin, target, amount);
		BankClient bc_origin = lista.get(0);
		BankClient bc_target= lista.get(1);
		
		String message = "Su transferencia se ha realizado con éxito";
//		String message = "";
		request.setCharacterEncoding("UTF-8");
		DecimalFormat df = new DecimalFormat("#.00"); 
		request.setAttribute("amount", df.format(amount));
		request.setAttribute("name_o", bc_origin.getName());
		request.setAttribute("balance_o", df.format(bc_origin.getBalance()));
		request.setAttribute("account_o", bc_origin.getAccount());
		request.setAttribute("name_t", bc_target.getName());
		request.setAttribute("balance_t", df.format(bc_target.getBalance()));
		request.setAttribute("account_t", bc_target.getAccount());
		request.setAttribute("msg", message);
		request.setAttribute("icon", "ok");
	    request.getRequestDispatcher("/transfer.jsp").forward(request, response);      		
	}

}
