package DBInteract;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DBInteract/reg-student")
public class StudRegServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Resource(name="jdbc/me")
	DataSource ds;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("studPswd").equals(request.getParameter("studPswd2"))) {
			User newUser = new User(request.getParameter("studUsrn"),request.getParameter("studPswd"));
			
			StudUserDao dao = new StudUserDao(ds);			
			dao.insertStud(newUser);
			
			RequestDispatcher rd = request.getRequestDispatcher("../landing/studregistered.jsp");
			rd.forward(request, response);
			
		}else {
			
			RequestDispatcher rd = request.getRequestDispatcher("RegisterStud.html");
			rd.forward(request, response);
			
		}
		
		
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
