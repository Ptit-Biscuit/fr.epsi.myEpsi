package fr.epsi.myEpsi;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.exceptions.DaoException;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Start up of the application
 */
public class ApplicationStartup {
	/**
	 * Application initialized
	 */
	private static boolean isInitialized = false;
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(ApplicationStartup.class);
	/**
	 * Timer for DAO initialization
	 */
	private Timer daoInitTimer = new Timer("DAO init timer");

	/**
	 * Getter application initialized
	 *
	 * @return <code>true</code> if application is initialized, <code>false</code> otherwise
	 */
	public static boolean isInitialized() {
		return isInitialized;
	}

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

					if (userDao.connectionAlive()) {
						// set user DAO in session context
						sessionEvent.getSession().getServletContext().setAttribute("userDao", userDao);

						isInitialized = true;

						// cancel this timer : dao initialisation is done
						daoInitTimer.cancel();
						daoInitTimer = null;

						logger.info("DAO initialisée");
					}
				} catch (DaoException e) {
					logger.error("Impossible de se connecter à la BDD");
				}
			}
		}, 0, 1500);
	}
}
