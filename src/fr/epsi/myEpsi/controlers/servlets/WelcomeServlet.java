package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.beans.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to welcome user
 */
@WebServlet(name = "Welcome", urlPatterns = "/welcome")
public class WelcomeServlet extends GenericServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		if (request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		// retrieve ads of the user
		String userMail = request.getParameter("userMail");
		List<Ad> ads = ((IAdDao) request.getServletContext().getAttribute("adDao")).getUserAds(userMail);

		request.setAttribute("ads", ads);
		request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	}
}
