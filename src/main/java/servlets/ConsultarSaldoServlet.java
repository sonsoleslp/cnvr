package servlets;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 
public class ConsultarSaldoServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
 
  @Override
  public void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    out.println("SimpleServlet Executed");
    out.flush();
    out.close();
  }
  @Override
  public void doPut(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    out.println("SimpleServlet Executed");
    out.flush();
    out.close();
  }
  
}