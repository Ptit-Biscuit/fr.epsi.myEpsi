package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.EStatus;
import fr.epsi.myEpsi.models.beans.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servlet to welcome user
 */
@WebServlet(name = "Welcome", urlPatterns = "/welcome")
public class WelcomeServlet extends GenericServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/index.jsp");
			return;
		}

		// retrieve all ads with VALID status
		List<Ad> ads = ((IAdDao) request.getSession().getAttribute("adDao")).getAllAds()
				.stream().filter(ad -> EStatus.VALID.ordinal() == ad.getStatus()).collect(Collectors.toList());

		request.setAttribute("ads", ads);
		request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	}
}
