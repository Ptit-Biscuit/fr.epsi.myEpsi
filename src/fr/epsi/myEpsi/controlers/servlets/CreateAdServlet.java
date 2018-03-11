package fr.epsi.myEpsi.controlers.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAd", urlPatterns = "/createAd")
public class CreateAdServlet extends GenericServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/index.jsp");
			return;
		}

		request.getRequestDispatcher("/create.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);


	}
}
