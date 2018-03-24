package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.EStatus;
import fr.epsi.myEpsi.models.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyServlet", urlPatterns = "/buy")
public class BuyServlet extends GenericServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		IAdDao adDao = ((IAdDao) request.getSession().getAttribute("adDao"));
		User user = (User) request.getSession().getAttribute("user");
		int adId = Integer.valueOf(request.getParameter("id"));

		if (user == null || adDao.getAd(adId).getStatus().equals(EStatus.VENDUE)) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		adDao.buy(user, adId);

		request.getRequestDispatcher("/welcome").forward(request, response);
	}
}