package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.beans.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for handling ads without connection
 */
@WebServlet(name = "Ad", urlPatterns = "/ads")
public class AdServlet extends GenericServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdDao adDao = ((IAdDao) request.getServletContext().getAttribute("adDao"));
		List<Ad> ads;

		String userMail = request.getParameter("userMail");
		String id = request.getParameter("id");

		if (userMail != null) {
			ads = adDao.getUserAds(userMail);
		} else if (id != null) {
			ads = new ArrayList<>();
			ads.add(adDao.getAd(id));
		} else {
			ads = adDao.getAllAds();
		}

		request.setAttribute("ads", ads);
		request.getRequestDispatcher("/ads.jsp").forward(request, response);
	}
}
