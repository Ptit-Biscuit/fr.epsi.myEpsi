package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.misc.HashUtil;
import fr.epsi.myEpsi.models.beans.User;
import fr.epsi.myEpsi.models.beans.UserDefault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User DAO tests
 */
class UserDaoImplTest {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(UserDaoImplTest.class);

	/**
	 * User DAO
	 */
	private IUserDao userDao;

	/**
	 * Test user
	 */
	private User testUser;

	public UserDaoImplTest() {
		try {
			this.userDao = DaoFactory.getInstance().getUserDao();
		} catch (SQLException e) {
			logger.error("Impossible de se connecter à la BDD", e);
		}
	}

	@BeforeEach
	void createTestUser() {
		this.testUser = new User();
		this.testUser.setMail("test@test");
		this.testUser.setPseudo("test");
		this.testUser.setPassword(HashUtil.hashPassword("testPassword"));

		userDao.create(this.testUser);
	}

	@AfterEach
	void deleteTestUser() {
		if (!(userDao.find(this.testUser.getMail()) instanceof UserDefault))
			userDao.deleteUser(this.testUser);
	}

	@Test
	void create() {
		userDao.deleteUser(this.testUser);

		// first time user creation
		assertTrue(this.userDao.create(this.testUser));
		// second time creation
		assertFalse(this.userDao.create(this.testUser));
	}

	@Test
	void getAllUsers() {
		assertFalse(userDao.getAllUsers().isEmpty());
	}

	@Test
	void find() {
		User userFound = userDao.find(this.testUser.getMail());

		assertEquals(this.testUser.getMail(), userFound.getMail());
		assertEquals(this.testUser.getPseudo(), userFound.getPseudo());
		assertEquals(this.testUser.getPassword(), userFound.getPassword());
	}

	@Test
	void updateUser() {
		String oldMail = this.testUser.getMail();

		this.testUser.setMail("modify@test");
		this.testUser.setPseudo("modifyPseudo");
		this.testUser.setPassword(HashUtil.hashPassword("modifyPassword"));

		userDao.updateUser(oldMail, this.testUser);
		User modifyTestUser = userDao.find("modify@test");

		assertEquals("modify@test", modifyTestUser.getMail());
		assertEquals("modifyPseudo", modifyTestUser.getPseudo());
		assertEquals(HashUtil.hashPassword("modifyPassword"), modifyTestUser.getPassword());
	}

	@Test
	void deleteUser() {
		assertTrue(userDao.deleteUser(this.testUser));

		User userNotFound = userDao.find(this.testUser.getMail());

		assertEquals(new UserDefault().getMail(), userNotFound.getMail());
		assertEquals(new UserDefault().getPseudo(), userNotFound.getPseudo());
		assertEquals(new UserDefault().getPassword(), userNotFound.getPassword());
	}
}