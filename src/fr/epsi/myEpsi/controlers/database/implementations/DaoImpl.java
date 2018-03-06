package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.controlers.database.interfaces.IDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base DAO implementation
 */
public class DaoImpl implements IDao {
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
			throw new DaoException("Connexion avec la BDD fermée", e);
		}

		return alive;
	}

	@Override
	public void closeConnection() throws DaoException {
		try {
			if (!this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de fermer la connexion", e);
		}
	}
}
