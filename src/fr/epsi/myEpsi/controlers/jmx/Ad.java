package fr.epsi.myEpsi.controlers.jmx;

import fr.epsi.myEpsi.controlers.database.DaoFactory;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;

public class Ad implements AdMBean {
    /**
     * The name of the bean
     */
    private static String nom = "Ad";

    /**
     * the number of ad
     */
    private int nbAd;

    /**
     * @return The name of the bean
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return The number of ad
     */
    public int getNbAd() {
        return nbAd;
    }

    /**
     * The class constructor
     */
    public Ad() {
        IAdDao adDao = DaoFactory.getInstance().getAdDao();
        this.nbAd = adDao.getAllAds().size();
    }
}
