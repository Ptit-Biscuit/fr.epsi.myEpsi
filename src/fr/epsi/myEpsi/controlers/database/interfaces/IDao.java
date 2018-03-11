package fr.epsi.myEpsi.controlers.database.interfaces;

/**
 * Base DAO
 */
public interface IDao {

	/**
	 * Check if connection with database is closed or not
	 *
	 * @return <code>true</code> if alive, <code>false</code> otherwise
	 */
	boolean connectionAlive();


	/**
	 * Close the connection with database
	 */
	void closeConnection();
}
