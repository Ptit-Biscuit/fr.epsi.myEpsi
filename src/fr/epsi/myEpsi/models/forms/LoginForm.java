package fr.epsi.myEpsi.models.forms;

import fr.epsi.myEpsi.controlers.database.implementations.UserDaoImpl;
import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.misc.HashUtil;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Login form to connect user
 */
public class LoginForm {

	/**
	 * Log in a user
	 *
	 * @param request Request from connection servlet
	 * @return The user, <code>null</code> otherwise
	 */
	public static User loginUser(HttpServletRequest request) {
		// retrieve data from login form
		String mail = ServletUtil.retrieveValue(request, "mail");
		String password = ServletUtil.retrieveValue(request, "password");

		if (mail == null || password == null)
			return null;

		User user;

		try {
			// retrieve user from database if exists
			user = ((UserDaoImpl) request.getServletContext().getAttribute("userDao")).find(mail);
		} catch (DaoException e) {
			user = null;
		}

		if (user != null && user.getPassword().equals(HashUtil.hashPassword(password)))
			return user;
		else
			return null;
	}
}
