package fr.epsi.myEpsi.controlers.database.interfaces;

import fr.epsi.myEpsi.models.beans.User;

import java.util.List;

/**
 * User DAO
 */
public interface IUserDao extends IDao {

	/**
	 * Create a user in database
	 *
	 * @param user User to create
	 * @return <code>true</code> if user has been created, <code>false</code> otherwise
	 */
	boolean create(User user);

	/**
	 * Getter of all users in database
	 *
	 * @return A <code>List</code> of all users
	 */
	List<User> getAllUsers();

	/**
	 * Find a user in database by his mail address
	 *
	 * @param mail User's mail address
	 * @return The user found, <code>null</code> otherwise
	 */
	User find(String mail);

	/**
	 * Update a user in database
	 *
	 * @param oldMail Old mail of user (if changed)
	 * @param user    User to update
	 * @return <code>True</code> if user has been updated, <code>false</code> otherwise
	 */
	boolean updateUser(String oldMail, User user);

	/**
	 * Delete a user in database
	 *
	 * @param user User to deleteUser
	 * @return <code>True</code> if user has been deleted, <code>false</code> otherwise
	 */
	boolean deleteUser(User user);
}
