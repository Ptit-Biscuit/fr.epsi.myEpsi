package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;
import fr.epsi.myEpsi.models.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

		if (!(user instanceof UserDefault)) {
			// redirect to welcome servlet
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/welcome?userMail=" + user.getMail());
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
