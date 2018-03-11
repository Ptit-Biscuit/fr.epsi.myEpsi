package fr.epsi.myEpsi.controlers.database.interfaces;

import fr.epsi.myEpsi.models.beans.Ad;

import java.util.List;

/**
 * Ad DAO
 */
public interface IAdDao extends IDao {

	/**
	 * Save an ad in database
	 *
	 * @param ad Ad to save
	 * @return <code>True</code> if save, <code>false</code> otherwise
	 */
	boolean save(Ad ad);

	/**
	 * Getter of all the ads in database
	 *
	 * @return A <code>List</code> of all the ads
	 */
	List<Ad> getAllAds();

	/**
	 * Getter of an ad by its id
	 *
	 * @param id Ad's id
	 * @return The ad found or <code>AdDefault</code> otherwise
	 * @see fr.epsi.myEpsi.models.beans.AdDefault
	 */
	Ad getAd(String id);

	/**
	 * Getter Ads
	 *
	 * @param userMail Mail of the user
	 * @return A <code>List</code> of the ads
	 */
	List<Ad> getUserAds(String userMail);
}
