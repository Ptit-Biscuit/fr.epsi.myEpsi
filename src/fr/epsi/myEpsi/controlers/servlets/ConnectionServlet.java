package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for connection
 */
@WebServlet(name = "Connection", urlPatterns = "/connection")
public class ConnectionServlet extends GenericServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		// try log in user
		User user = LoginForm.loginUser(request);

		if (user != null) {
			// store user in context
			request.getServletContext().setAttribute("userLogin", user.getMail());

			// retrieve ads of the user
			List<Ad> ads = ((IAdDao) request.getServletContext().getAttribute("adDao")).getUserAds(user.getMail());

			request.setAttribute("ads", ads);
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
