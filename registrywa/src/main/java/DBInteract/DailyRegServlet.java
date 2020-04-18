package DBInteract;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DBInteract.DailyRegDao;
import DBInteract.DailyReg;


@WebServlet("/DBInteract/execute-registration")
public class DailyRegServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DailyRegServlet.class);

	
	
	 @Resource(name = "jdbc/me") 
	    private DataSource ds;
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException{

	 //getting the parameter
	 Date regDate = Date.valueOf(request.getParameter("regDate"));
	 Time mEntryTime = Time.valueOf(request.getParameter("mEntryTime"));
	 Time mExitTime = Time.valueOf(request.getParameter("mExitTime"));
	 Time aEntryTime = Time.valueOf(request.getParameter("aEntryTime"));
	 Time aExitTime = Time.valueOf(request.getParameter("aExitTime"));
	 String description = request.getParameter("description");
	 String signCand = request.getParameter("signCand");
	 
	//use session to access with USERID.
	 HttpSession session = request.getSession();
	 int userID = (int) session.getAttribute("userID");
	 
	 
	 DailyReg dailyReg = new DailyReg(regDate, mEntryTime, mExitTime, aEntryTime, aExitTime, description, signCand, userID);
	 logger.trace("DailyReg Object Created");
	 
	 DailyRegDao dao = new DailyRegDao(ds);
	 logger.trace("Attempting the insertDailyReg");
	 
	 
	 dao.insertDailyReg(dailyReg);  
	 
	 
	 request.setAttribute("registrations", dao.selectAll(userID));  
	 
	 RequestDispatcher rd = request.getRequestDispatcher("../landing/studlogged.jsp");
     rd.forward(request, response);
	 
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response); 
	    }
	 
	 
	 

}

