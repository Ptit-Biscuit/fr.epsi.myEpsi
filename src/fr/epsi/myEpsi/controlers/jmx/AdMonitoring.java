package fr.epsi.myEpsi.controlers.jmx;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;

/**
 * Ad monitoring MBean
 */
public class AdMonitoring implements AdMBean {
	/**
	 * the number of ad
	 */
	private int nbAd;

	/**
	 * The class constructor
	 */
	public AdMonitoring() {
		IAdDao adDao = DaoFactory.getInstance().getAdDao();
		this.nbAd = adDao.getAllAds().size();
	}

	/**
	 * @return The number of ad
	 */
	public int getNbAd() {
		return nbAd;
	}
}
