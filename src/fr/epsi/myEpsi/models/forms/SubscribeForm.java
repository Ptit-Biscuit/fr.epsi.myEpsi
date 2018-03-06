package fr.epsi.myEpsi.models.forms;

import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.misc.HashUtil;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Subscribe form to register user in database
 */
public class SubscribeForm {

	/**
	 * Register a user in database
	 *
	 * @param request The request from subscribe servlet
	 * @return The user that has been registered, <code>null</code> otherwise
	 */
	public static User registerUser(HttpServletRequest request) {
		// retrieve data from subscribe form
		String mail = ServletUtil.retrieveValue(request, "mail");
		String password = ServletUtil.retrieveValue(request, "password");
		String confirm = ServletUtil.retrieveValue(request, "confirm");

		if (mail == null || password == null || confirm == null)
			return null;

		if (!password.equals(confirm))
			return null;

		User user = new User();
		user.setMail(mail);
		user.setPassword(HashUtil.hashPassword(password));


		try {
			if (((IUserDao) request.getServletContext().getAttribute("userDao")).create(user))
				return user;
			else {
				return null;
			}
		} catch (DaoException e) {
			return null;
		}
	}
}
