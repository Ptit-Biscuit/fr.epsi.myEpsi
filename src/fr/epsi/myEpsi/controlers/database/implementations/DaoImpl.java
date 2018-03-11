package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.interfaces.IDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base DAO implementation
 */
public class DaoImpl implements IDao {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(DaoImpl.class);

	/**
	 * Connection
	 */
	Connection connection;

	/**
	 * Constructor
	 *
	 * @param connection Connection to database
	 */
	DaoImpl(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean connectionAlive() {
		boolean alive = false;

		try {
			if (!this.connection.isClosed())
				alive = true;
		} catch (SQLException e) {
			logger.error("Connexion avec la BDD fermée", e);
		}

		return alive;
	}

	@Override
	public void closeConnection() {
		try {
			if (!this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			logger.error("Impossible de fermer la connexion", e);
		}
	}
}
