package DBInteract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/DBInteract/login-student")
public class StudLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/me")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studUsrn = request.getParameter("studUsrn");
		String studPswd = request.getParameter("studPswd");
		
		StudUserDao dao = new StudUserDao(ds);
		List<User> matchingUsers = dao.getUser(studUsrn);

		if (matchingUsers.isEmpty()) {
			RequestDispatcher rdwrong = request.getRequestDispatcher("../landing/WrongLogin.html");
			rdwrong.forward(request, response);

		} else {

			DailyRegDao regDao = new DailyRegDao(ds);
			
			for (User user : matchingUsers) {
				if (user.getPassword().equals(studPswd)) {
					
					List<DailyReg> userReg = regDao.selectAll(user.getUsrID());

					request.setAttribute("username", studUsrn);
					request.setAttribute("registrations", userReg);
					
					HttpSession session = request.getSession();
					session.setAttribute("userID", user.getUsrID());

					RequestDispatcher rdright = request.getRequestDispatcher("../landing/studlogged.jsp");
					rdright.forward(request, response);

				} else {
					RequestDispatcher rdwrong = request.getRequestDispatcher("../landing/WrongLogin.html");
					rdwrong.forward(request, response);
				}
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
