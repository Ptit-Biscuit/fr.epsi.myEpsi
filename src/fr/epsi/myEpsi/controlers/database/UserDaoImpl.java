package fr.epsi.myEpsi.controlers.database;

import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.models.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User DAO implementation
 */
public class UserDaoImpl implements IUserDao {
	/**
	 * Connection to database
	 */
	private final Connection connection;

	/**
	 * Constructor
	 *
	 * @param connection Connection to database
	 */
	UserDaoImpl(final Connection connection) {
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
	public boolean create(User user) throws DaoException {
		String s = "INSERT INTO users (mail, pass) VALUES(?, ?);";
		boolean created = false;

		if (user == null)
			return created;

		if (user.getMail() == null || user.getPassword() == null)
			return created;

		if (!this.connectionAlive())
			return created;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, user.getMail());
			ps.setString(2, user.getPassword());
			ps.execute();

			created = true;
		} catch (SQLException e) {
			throw new DaoException("Impossible de créer l'utilisateur " + user.getMail(), e);
		}

		return created;
	}

	@Override
	public User find(String mail) throws DaoException {
		User user = null;
		String s = "SELECT * FROM users WHERE mail = ?;";

		if (mail == null || mail.trim().isEmpty())
			return null;

		if (!this.connectionAlive())
			return null;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("pass"));
				user.setSubsciption(rs.getTimestamp("subscription"));
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de chercher l'utilisateur " + mail, e);
		}

		return user;
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
