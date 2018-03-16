package fr.epsi.myEpsi.controlers.database.implementations;

import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.models.EStatus;
import fr.epsi.myEpsi.models.beans.Ad;
import fr.epsi.myEpsi.models.beans.AdDefault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ad DAO implementation
 */
public class AdDaoImpl extends DaoImpl implements IAdDao {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(AdDaoImpl.class);

	/**
	 * Constructor
	 *
	 * @param connection Connection to database
	 */
	public AdDaoImpl(final Connection connection) {
		super(connection);
	}

	/**
	 * Populate a list of ads with a <code>ResultSet</code>
	 *
	 * @param list   The list of ads to populate
	 * @param result The <code>ResultSet</code> containing the ad
	 * @throws SQLException if error occurs
	 **/
	private void populate(List<Ad> list, ResultSet result) throws SQLException {
		Ad ad = new Ad();
		ad.setId(result.getInt("id"));
		ad.setTitle(result.getString("title"));
		ad.setDescription(result.getString("description"));
		ad.setStatus(EStatus.getStatus(result.getInt("status")));
		ad.setSeller(result.getString("seller"));
		ad.setPrice(result.getFloat("price"));
		ad.setSoldAt(result.getDate("soldAt"));
		ad.setViewNumber(result.getInt("viewNumber"));
		ad.setModificationAt(result.getDate("modificationAt"));

		list.add(ad);
	}

	@Override
	public boolean saveAd(Ad ad) {
		boolean saved = false;
		String s = "INSERT INTO ads (title, description, status, seller, price, viewNumber) VALUES (?, ?, ?, ?, ?, ?);";

		if (ad == null || ad instanceof AdDefault)
			return saved;

		if (!this.connectionAlive())
			return saved;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, ad.getTitle());
			ps.setString(2, ad.getDescription());
			ps.setInt(3, ad.getStatus().ordinal());
			ps.setString(4, ad.getSeller());
			ps.setFloat(5, ad.getPrice());
			ps.setInt(6, 0);
			saved = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.error("Impossible de sauvegarder l'annonce " + ad.getTitle(), e);
		}

		return saved;
	}

	@Override
	public boolean deleteAd(int adId) {
		boolean deleted = false;
		String s = "DELETE FROM ads WHERE id = ?";

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setInt(1, adId);
			deleted = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			logger.error("Impossible de supprimer l'annonce n°" + adId, e);
		}

		return deleted;
	}

	@Override
	public Ad getAd(String id) {
		Ad ad = new AdDefault();
		String s = "SELECT * FROM ads WHERE id = ?;";

		if (!this.connectionAlive())
			return ad;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ad.setId(rs.getInt("id"));
				ad.setTitle(rs.getString("title"));
				ad.setDescription(rs.getString("description"));
				ad.setStatus(EStatus.getStatus(rs.getInt("status")));
				ad.setSeller(rs.getString("seller"));
				ad.setPrice(rs.getFloat("price"));
				ad.setSoldAt(rs.getDate("soldAt"));
				ad.setViewNumber(rs.getInt("viewNumber"));
				ad.setModificationAt(rs.getDate("modificationAt"));
			}
		} catch (SQLException e) {
			logger.error("Impossible de récupérer l'annonce n°" + id, e);
		}

		return ad;
	}

	@Override
	public List<Ad> getAllAds() {
		List<Ad> ads = new ArrayList<>();
		String s = "SELECT * FROM ads;";

		if (!this.connectionAlive())
			return ads;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				populate(ads, rs);
			}
		} catch (SQLException e) {
			logger.error("Impossible de récupérer toutes les annonces", e);
		}

		return ads;
	}

	@Override
	public List<Ad> getUserAds(String userMail) {
		List<Ad> ads = new ArrayList<>();
		String s = "SELECT * FROM ads WHERE seller = ?;";

		if (userMail == null)
			return ads;

		if (!this.connectionAlive())
			return ads;

		try (PreparedStatement ps = this.connection.prepareStatement(s)) {
			ps.setString(1, userMail);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				populate(ads, rs);
			}
		} catch (SQLException e) {
			logger.error("Impossible de récupérer les annonces de " + userMail, e);
		}

		return ads;
	}
}
