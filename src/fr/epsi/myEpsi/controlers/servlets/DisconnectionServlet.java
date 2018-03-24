package fr.epsi.myEpsi.controlers.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for disconnection
 */
@WebServlet(name = "Logout", urlPatterns = "/logout")
public class DisconnectionServlet extends GenericServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		// Retrieve and destroy actual session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();

		// Return to index page
		response.sendRedirect(request.getContextPath() + "/index.html");
	}
}
