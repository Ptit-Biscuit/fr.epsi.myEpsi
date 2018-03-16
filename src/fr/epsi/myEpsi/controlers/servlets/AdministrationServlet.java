package fr.epsi.myEpsi.controlers.servlets;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.misc.HashUtil;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.forms.CreateAdForm;
import fr.epsi.myEpsi.models.forms.SubscribeForm;
import fr.epsi.myEpsi.models.forms.UpdateUserForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdministrationServlet", urlPatterns = "/administration")
public class AdministrationServlet extends GenericServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/index.jsp");
			return;
		}

		request.getRequestDispatcher("/administration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String userCreation = ServletUtil.retrieveValue(request, "userCreation");
		String userModification = ServletUtil.retrieveValue(request, "userModification");
		String userDelete = ServletUtil.retrieveValue(request, "userDelete");
		String adCreation = ServletUtil.retrieveValue(request, "adCreation");
		String adDelete = ServletUtil.retrieveValue(request, "adDelete");

		String mail = ServletUtil.retrieveValue(request, "mail");
		String password = ServletUtil.retrieveValue(request, "password");

		String adId = ServletUtil.retrieveValue(request, "adId");

		// Create a user
		if (!userCreation.isEmpty() && Boolean.valueOf(userCreation)) {
			// try register user
			User user = SubscribeForm.registerUser(request);
		}

		// Modify a user
		if (!userModification.isEmpty() && Boolean.valueOf(userModification)) {
			// try update user
			UpdateUserForm.updateUser(request);
		}

		// Delete a user
		if (!userDelete.isEmpty() && Boolean.valueOf(userDelete)) {
			if (mail.isEmpty() || password.isEmpty()) {
				response.sendRedirect("/administration.jsp");
				return;
			}

			User user = new User();
			user.setMail(mail);
			user.setPassword(HashUtil.hashPassword(password));

			((IUserDao) request.getSession().getAttribute("userDao")).deleteUser(user);
		}

		// Create an ad
		if (!adCreation.isEmpty() && Boolean.valueOf(adCreation)) {
			// try register an ad
			Ad ad = CreateAdForm.createAd(request);
		}

		// Delete an ad
		if (!adDelete.isEmpty() && Boolean.valueOf(adDelete)) {
			if (adId.isEmpty()) {
				response.sendRedirect("/administration.jsp");
				return;
			}

			((IAdDao) request.getSession().getAttribute("adDao")).deleteAd(Integer.valueOf(adId));
		}

		response.sendRedirect("/administration.jsp");
	}
}
