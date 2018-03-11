package fr.epsi.myEpsi.controlers.database;

import fr.epsi.myEpsi.controlers.database.implementations.AdDaoImpl;
import fr.epsi.myEpsi.controlers.database.implementations.UserDaoImpl;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database factory pattern
 */
public class DaoFactory {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);

	/**
	 * Driver property field
	 */
	private static final String PROPERTY_DRIVER = "driver";

	/**
	 * URL property field
	 */
	private static final String PROPERTY_URL = "url";

	/**
	 * Port property field
	 */
	private static final String PROPERTY_PORT = "port";

	/**
	 * Database's name property field
	 */
	private static final String PROPERTY_DATABASE_NAME = "databaseName";

	/**
	 * User property field
	 */
	private static final String PROPERTY_USER = "user";

	/**
	 * Password property field
	 */
	private static final String PROPERTY_PASSWORD = "password";

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
		Properties properties = new Properties();
		String driver = "";
		String databaseName = "";
		String url = "";
		String user = "";
		String password = "";

		// Retrieve DAO's properties file
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream daoPropertiesFile = classLoader.getResourceAsStream("dao.properties");

		if (daoPropertiesFile == null)
			LOGGER.error("Le fichier \"dao.properties\" est introuvable.");

		try {
			properties.load(daoPropertiesFile);
			driver = properties.getProperty(PROPERTY_DRIVER);
			databaseName = properties.getProperty(PROPERTY_DATABASE_NAME);
			url = properties.getProperty(PROPERTY_URL) + ":" + properties.getProperty(PROPERTY_PORT) + "/" + (!databaseName.isEmpty() ? databaseName : "");
			user = properties.getProperty(PROPERTY_USER);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (IOException e) {
			LOGGER.error("Impossible de charger le fichier \"dao.properties\"", e);
		}

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