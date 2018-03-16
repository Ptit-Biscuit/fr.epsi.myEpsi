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
	 * @param ad Ad to saveAd
	 * @return <code>True</code> if ad has been saved, <code>false</code> otherwise
	 */
	boolean saveAd(Ad ad);

	/**
	 * Delete an ad in database
	 *
	 * @param adId Id of the ad to delete
	 * @return <code>True</code> if ad has been deleted, <code>false</code> otherwise
	 */
	boolean deleteAd(int adId);

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
