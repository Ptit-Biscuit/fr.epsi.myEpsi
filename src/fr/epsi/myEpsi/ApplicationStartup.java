package fr.epsi.myEpsi;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Start up of the application
 */
public class ApplicationStartup {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(ApplicationStartup.class);

	/**
	 * Timer for DAO initialization
	 */
	private Timer daoInitTimer = new Timer("DAO init timer");

	/**
	 * Initialize DAO
	 *
	 * @param sessionEvent Startup session event
	 */
	public void initDao(HttpSessionEvent sessionEvent) {
		logger.info("Initialisation DAO");

		this.daoInitTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					IUserDao userDao = DaoFactory.getInstance().getUserDao();
					IAdDao adDao = DaoFactory.getInstance().getAdDao();

					if (userDao.connectionAlive() && adDao.connectionAlive()) {
						// set user DAO in context
						logger.error(userDao.getAllUsers().size() + " utilisateurs");
						sessionEvent.getSession().setAttribute("userDao", userDao);

						// set ad DAO in context
						logger.error(adDao.getAllAds().size() + " annonces");
						sessionEvent.getSession().setAttribute("adDao", adDao);

						// session is initialized
						sessionEvent.getSession().setAttribute("isInitialized", true);

						// cancel this timer : dao initialisation is done
						daoInitTimer.cancel();
						daoInitTimer = null;

						logger.info("DAO initialisée");
					}
				} catch (SQLException e) {
					logger.error("Impossible de se connecter à la BDD", e);
				}
			}
		}, 0, 1500);
	}
}
