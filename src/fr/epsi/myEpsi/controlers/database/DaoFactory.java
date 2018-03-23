package fr.epsi.myEpsi.controlers.database;

import fr.epsi.myEpsi.controlers.database.implementations.AdDaoImpl;
import fr.epsi.myEpsi.controlers.database.implementations.UserDaoImpl;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database factory pattern
 */
public class DaoFactory {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);

	/**
	 * Database's URL
	 */
	private String url;

	/**
	 * Database's user
	 */
	private String user;

	/**
	 * Database's password
	 */
	private String password;

	/**
	 * Constructor
	 *
	 * @param url      Database's URL
	 * @param user     Database's user
	 * @param password Database's password
	 */
	private DaoFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * Retrieve connection's informations and load driver
	 *
	 * @return DAO instance
	 */
	public static DaoFactory getInstance() {
		String driver = System.getenv("driver");
		String databaseName = System.getenv("databaseName");
		String url = System.getenv("url") + ":" + System.getenv("port") + "/" + (!databaseName.isEmpty() ? databaseName : "");
		String user = System.getenv("user");
		String password = System.getenv("password");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Le driver est introuvable", e);
		}

		return new DaoFactory(url, user, password);
	}

	/**
	 * Getter of a connection to database
	 *
	 * @return A connection to database
	 * @throws SQLException if error occurs
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, this.user, this.password);
	}

	/**
	 * Getter of user DAO
	 *
	 * @return User DAO
	 * @throws SQLException if error occurs
	 */
	public IUserDao getUserDao() throws SQLException {
		return new UserDaoImpl(this.getConnection());
	}

	/**
	 * Getter of ad DAO
	 *
	 * @return Ad DAO
	 * @throws SQLException if error occurs
	 */
	public IAdDao getAdDao() throws SQLException {
		return new AdDaoImpl(this.getConnection());
	}
}