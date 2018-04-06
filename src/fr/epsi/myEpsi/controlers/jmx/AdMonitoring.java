package fr.epsi.myEpsi.controlers.jmx;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

/**
 * Ad monitoring MBean
 */
public class AdMonitoring implements AdMonitoringMBean {
	/**
	 * The class constructor
	 */
	public AdMonitoring() {}

	/**
	 * @return The number of ad
	 */
	public int getNbAd() {
		IAdDao adDao = null;
		int nbAd = -1;

		try {
			adDao = DaoFactory.getInstance().getAdDao();
			nbAd = adDao.getAllAds().size();
		} catch (SQLException e) {
			LogManager.getLogger(AdMonitoring.class).error("Impossible de récupérer le nombre d'annonces", e);
		}

		return nbAd;
	}
}
