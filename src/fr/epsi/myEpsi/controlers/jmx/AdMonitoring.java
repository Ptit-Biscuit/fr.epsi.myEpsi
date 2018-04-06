package fr.epsi.myEpsi.controlers.jmx;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

/**
 * Ad monitoring MBean
 */
public class AdMonitoring implements AdMonitoringMBean {
	/**
	 * Number of ads
	 */
	private int ads = -1;

	/**
	 * Constructor
	 */
	public AdMonitoring() {
		this.updateAds();
	}

	private void updateAds() {
		try {
			this.ads = DaoFactory.getInstance().getAdDao().getAllAds().size();
		} catch (SQLException e) {
			ads = -1;
			LogManager.getLogger(AdMonitoring.class).error("Impossible de récupérer le nombre d'annonces", e);
		}
	}

	@Override
	public int getNbAd() {
		this.updateAds();
		return this.ads;
	}
}
