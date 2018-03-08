package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User DAO implementation
 */
public class UserDaoImpl extends DaoImpl implements IUserDao {

	/**
	 * Constructor
	 *
	 * @param connection Connection to database
	 */
	public UserDaoImpl(final Connection connection) {
		super(connection);
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
		User user = new UserDefault();
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
}
