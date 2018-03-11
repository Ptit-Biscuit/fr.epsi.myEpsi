package fr.epsi.myEpsi.models.forms;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.misc.ServletUtil;
import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.AdDefault;
import fr.epsi.myEpsi.models.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Form to save an ad in database
 */
public class CreateAdForm {

	/**
	 * Create an ad in database
	 *
	 * @param request Request
	 * @return The <code>Ad</code> that have been saved, <code>AdDefault</code> otherwise
	 * @see AdDefault
	 */
	public static Ad createAd(HttpServletRequest request) {
		// retrieve data from create ad form
		String title = ServletUtil.retrieveValue(request, "adTitle");
		String desc = ServletUtil.retrieveValue(request, "adDescription");
		float price = Float.valueOf(ServletUtil.retrieveValue(request, "adPrice"));

		if (title.isEmpty() || desc.isEmpty())
			return new AdDefault();

		Ad ad = new Ad();
		ad.setTitle(title);
		ad.setDescription(desc);
		ad.setPrice(price);
		ad.setSeller(((User) request.getSession().getAttribute("user")).getMail());

		if (((IAdDao) request.getSession().getAttribute("adDao")).save(ad))
			return ad;
		else
			return new AdDefault();
	}
}
