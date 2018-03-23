package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User DAO implementation
 */
public class UserDaoImpl extends DaoImpl implements IUserDao {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	/**
	 * Constructor
	 *
	 * @param connection Connection to database
	 */
	public UserDaoImpl(final Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(User user) {
		String s = "INSERT INTO users (mail, pseudo, pass) VALUES(?, ?, ?);";
		boolean created = false;

		if (user == null)
			return created;

		if (user.getMail() == null || user.getPassword() == null)
			return created;

		if (!this.connectionAlive())
			return created;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, user.getMail());
			ps.setString(2, user.getPseudo());
			ps.setString(3, user.getPassword());
			created = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.error("Impossible de créer l'utilisateur " + user.getMail(), e);
		}

		return created;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String s = "SELECT * FROM users;";

		if (!this.connectionAlive())
			return users;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setMail(rs.getString("mail"));
				user.setPseudo(rs.getString("pseudo"));
				user.setPassword(rs.getString("pass"));
				user.setSubsciption(rs.getTimestamp("subscription"));

				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Impossible de récupérer tous les utilisateurs", e);
		}

		return users;
	}

	@Override
	public User find(String mail) {
		User user = new UserDefault();
		String s = "SELECT * FROM users WHERE mail = ?;";

		if (mail == null || mail.trim().isEmpty())
			return user;

		if (!this.connectionAlive())
			return user;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setMail(rs.getString("mail"));
				user.setPseudo(rs.getString("pseudo"));
				user.setPassword(rs.getString("pass"));
				user.setSubsciption(rs.getTimestamp("subscription"));
			}
		} catch (SQLException e) {
			logger.error("Impossible de trouver l'utilisateur " + mail, e);
		}

		return user;
	}

	@Override
	public boolean updateUser(String oldMail, User user) {
		boolean updated = false;
		String s = "UPDATE users SET mail = ?, pseudo = ?, pass = ? WHERE mail = ?";

		if (!this.connectionAlive())
			return updated;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, user.getMail());
			ps.setString(2, user.getPseudo());
			ps.setString(3, user.getPassword());
			ps.setString(4, oldMail);
			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.error("Impossible de modifier l'utilisateur " + user.getMail(), e);
		}

		return updated;
	}

	@Override
	public boolean deleteUser(User user) {
		boolean deleted = false;
		String s = "DELETE FROM users WHERE mail = ?;";

		if (user == null || user instanceof UserDefault)
			return deleted;

		if (user.getMail().endsWith("@root"))
			return deleted;

		if (!this.connectionAlive())
			return deleted;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, user.getMail());
			deleted = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.error("Impossible de supprimer l'utilisateur " + user.getMail(), e);
		}

		return deleted;
	}
}
