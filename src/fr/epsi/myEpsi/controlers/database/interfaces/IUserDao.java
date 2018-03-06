package fr.epsi.myEpsi.controlers.database.interfaces;

import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.models.beans.User;

/**
 * User DAO
 */
public interface IUserDao extends IDao {

	/**
	 * Create a user in database
	 *
	 * @param user User to create
	 * @return <code>true</code> if user has been created, <code>false</code> otherwise
	 * @throws DaoException if error occurs
	 */
	boolean create(User user) throws DaoException;

	/**
	 * Find a user in database by his mail address
	 *
	 * @param mail User's mail address
	 * @return The user found, <code>null</code> otherwise
	 * @throws DaoException if error occurs
	 */
	User find(String mail) throws DaoException;
}
