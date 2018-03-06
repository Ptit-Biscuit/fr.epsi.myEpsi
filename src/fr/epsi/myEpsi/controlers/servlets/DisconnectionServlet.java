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
@WebServlet(name = "Disconnection", urlPatterns = "/disconnect")
public class DisconnectionServlet extends GenericServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		// Retrieve and destroy actual session
		HttpSession session = request.getSession();
		session.invalidate();

		// Return to index page
		this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}
}
