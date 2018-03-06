package fr.epsi.myEpsi.controlers.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to welcome user
 */
@WebServlet(name = "Welcome", urlPatterns = "/welcome")
public class WelcomeServlet extends GenericServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	}
}
