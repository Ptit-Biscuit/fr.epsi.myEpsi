package fr.epsi.myEpsi.models.forms;

import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.misc.HashUtil;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;

import javax.servlet.http.HttpServletRequest;

/**
 * Update user form
 */
public class UpdateUserForm {

	/**
	 * Update a user
	 *
	 * @param request Request
	 * @return <code>True</code> if user has been updated, <code>false</code> otherwise
	 */
	public static boolean updateUser(HttpServletRequest request) {
		// retrieve data from update user form
		String oldMail = ServletUtil.retrieveValue(request, "oldMail");
		String mail = ServletUtil.retrieveValue(request, "mail");
		String pseudo = ServletUtil.retrieveValue(request, "pseudo");
		String password = ServletUtil.retrieveValue(request, "password");

		if (oldMail.isEmpty() || mail.isEmpty() || password.isEmpty())
			return false;

		User user = ((IUserDao) request.getSession().getAttribute("userDao")).find(oldMail);

		if (user instanceof UserDefault)
			return false;

		user.setMail(mail);
		user.setPseudo(pseudo);

		if (!password.equals(user.getPassword()))
			user.setPseudo(HashUtil.hashPassword(password));

		return ((IUserDao) request.getSession().getAttribute("userDao")).updateUser(oldMail, user);
	}
}
