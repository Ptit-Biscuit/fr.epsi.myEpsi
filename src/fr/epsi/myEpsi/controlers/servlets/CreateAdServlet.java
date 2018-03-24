package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.AdDefault;
import fr.epsi.myEpsi.models.forms.CreateAdForm;

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
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		request.getRequestDispatcher("/createAd.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		Ad adCreated = CreateAdForm.createAd(request);

		if (adCreated instanceof AdDefault)
			request.setAttribute("error", "L'annonce n'a pas été enregistrée");

		response.sendRedirect(request.getContextPath() + "/welcome");
	}
}
