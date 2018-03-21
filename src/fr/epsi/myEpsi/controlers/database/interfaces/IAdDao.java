package fr.epsi.myEpsi.controlers.database.interfaces;

import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.User;

import java.util.List;

/**
 * Ad DAO
 */
public interface IAdDao extends IDao {

	/**
	 * Save an ad in database
	 *
	 * @param ad Ad to save
	 * @return <code>True</code> if ad has been saved, <code>false</code> otherwise
	 */
	boolean save(Ad ad);

	/**
	 * Delete an ad in database
	 *
	 * @param adId Id of the ad to delete
	 * @return <code>True</code> if ad has been deleted, <code>false</code> otherwise
	 */
	boolean delete(int adId);

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
	Ad getAd(int id);

	/**
	 * Getter Ads of a user
	 *
	 * @param userMail Mail of the user
	 * @return A <code>List</code> of the ads
	 */
	List<Ad> getUserAds(String userMail);

	/**
	 * Validate an ad
	 *
	 * @param id ad's id
	 * @return <code>True</code> if ad has been validate, <code>false</code> otherwise
	 */
	boolean validate(int id);

	/**
	 * Update an ad
	 *
	 * @param ad Ad to update
	 * @return <code>True</code> if ad has been updated, <code>false</code> otherwise
	 */
	boolean update(Ad ad);

	/**
	 * Modify an ad that has been bought
	 *
	 * @param user Buyer
	 * @param id   Id of the ad bought
	 * @return <code>True</code> if correctly modify, <code>false</code> otherwise
	 */
	boolean buy(User user, int id);
}
