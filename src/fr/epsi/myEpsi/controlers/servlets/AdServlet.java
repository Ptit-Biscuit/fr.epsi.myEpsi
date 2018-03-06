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
 * Servlet for
 */
@WebServlet(name = "AdServlet", urlPatterns = "/ads")
public class AdServlet extends GenericServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdDao adDao = ((IAdDao) request.getServletContext().getAttribute("adDao"));
		List<Ad> ads;

		if (request.getParameter("userMail") != null) {
			ads = adDao.getUserAds(request.getParameter("userMail"));
		} else if (request.getParameter("id") != null) {
			ads = (List<Ad>) adDao.getAd(request.getParameter("id"));
		} else {
			ads = adDao.getAllAds();
		}

		request.setAttribute("ads", ads);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}
}
