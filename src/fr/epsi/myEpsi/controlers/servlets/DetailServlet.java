package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.AdDefault;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@WebServlet(name = "DetailServlet", urlPatterns = "/detail")
public class DetailServlet extends GenericServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		IAdDao adDao = (IAdDao) request.getSession().getAttribute("adDao");
		String id = ServletUtil.retrieveValue(request, "id");
		Ad ad = id.isEmpty() ? new AdDefault() : adDao.getAd(Integer.valueOf(id));

		if (request.getSession().getAttribute("user") == null || ad instanceof AdDefault) {
			response.sendRedirect("/index.jsp");
			return;
		}

		ad.setViewNumber(ad.getViewNumber() + 1);
		adDao.update(ad);

		request.setAttribute("ad", ad);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		IAdDao adDao = (IAdDao) request.getSession().getAttribute("adDao");
		String id = ServletUtil.retrieveValue(request, "adId");
		Ad ad = id.isEmpty() ? new AdDefault() : adDao.getAd(Integer.valueOf(id));

		String adTitle = ServletUtil.retrieveValue(request, "adTitle");
		String adDesc = ServletUtil.retrieveValue(request, "adDescription");
		float adPrice = Float.valueOf(ServletUtil.retrieveValue(request, "adPrice"));

		if (ad instanceof AdDefault) {
			response.sendRedirect("/welcome");
			return;
		}

		ad.setTitle(adTitle);
		ad.setDescription(adDesc);
		ad.setPrice(adPrice);
		ad.setModificationAt(new Date(Calendar.getInstance().getTimeInMillis()));

		adDao.update(ad);
		response.sendRedirect("/welcome");
	}
}
