package fr.epsi.myEpsi.controlers.jmx;

import fr.epsi.myEpsi.models.beans.Ad;

import java.util.List;

/**
 * Interface used to get all ads
 */
public interface IAdMBean {
    /**
     *
     * @return The name of the Bean
     */
    public String getName();

    /**
     *
     * @return All the ads
     */
    public List<Ad> getAllAds();

}
