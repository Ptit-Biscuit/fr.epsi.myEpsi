package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.EStatus;
import fr.epsi.myEpsi.models.beans.Ad;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Ad DAO tests
 */
class AdDaoImplTest {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(UserDaoImplTest.class);

	/**
	 * Ad DAO
	 */
	private IAdDao adDao;

	/**
	 * Test user
	 */
	private Ad testAd;

	public AdDaoImplTest() {
		try {
			this.adDao = DaoFactory.getInstance().getAdDao();
		} catch (SQLException e) {
			logger.error("Impossible de se connecter à la BDD", e);
		}
	}

	@BeforeEach
	void createTestUser() {
		this.testAd = new Ad();
		this.testAd.setTitle("ad test");
		this.testAd.setDescription("ad test");
		this.testAd.setStatus(EStatus.TEMPORAIRE);
		this.testAd.setPrice(1f);
		this.testAd.setSeller("Administrateur@root");
		this.testAd.setViewNumber(0);

		this.adDao.save(this.testAd);
	}

	@Test
	void delete() {
		// TODO: retrieve id fom an ad
		//assertTrue(this.adDao.delete(this.testAd.getId()));
	}
}