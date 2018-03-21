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
public class GetAdServlet extends GenericServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdDao adDao = ((IAdDao) request.getSession().getAttribute("adDao"));
		List<Ad> ads;

		String userMail = request.getParameter("userMail");
		String id = request.getParameter("id");
		int adId = (id != null) ? Integer.valueOf(id) : 0;

		if (userMail != null) {
			ads = adDao.getUserAds(userMail);
		} else if (adId > 0) {
			ads = new ArrayList<>();
			ads.add(adDao.getAd(adId));
		} else {
			ads = adDao.getAllAds();//.stream().filter(a -> a.getStatus().equals(EStatus.VALIDE)).collect(Collectors.toList());
		}

		request.setAttribute("ads", ads);
		request.getRequestDispatcher("/getAd.jsp").forward(request, response);
	}
}
