package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;
import fr.epsi.myEpsi.models.forms.SubscribeForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for subscription
 */
@WebServlet(name = "Subscribe", urlPatterns = "/subscribe")
public class SubscribeServlet extends GenericServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		request.setAttribute("userAlreadyExists", false);
		request.getRequestDispatcher("/subscribe.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		// try register user
		User user = SubscribeForm.registerUser(request);

		if (!(user instanceof UserDefault)) {
			// register user in session and redirect to welcome servlet
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/welcome");
		} else {
			// refresh page and show error
			request.setAttribute("userAlreadyExists", true);
			request.getRequestDispatcher("/subscribe.jsp").forward(request, response);
		}
	}
}
