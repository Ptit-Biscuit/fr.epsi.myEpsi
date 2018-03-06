package fr.epsi.myEpsi.models.beans;

import fr.epsi.myEpsi.models.EStatus;

import java.sql.Date;

/**
 * Represent an ad
 */
public class Ad {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * Title
	 */
	private String title;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Status
	 */
	private Integer status;

	/**
	 * Seller
	 */
	private User seller;

	/**
	 * Price
	 */
	private float price;

	/**
	 * Sold at
	 */
	private Date soldAt;

	/**
	 * Number of views
	 */
	private Integer viewNumber;

	/**
	 * Modification at
	 */
	private Date modificationAt;

	/**
	 * Getter id
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter id
	 *
	 * @param id New id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter title
	 *
	 * @return Title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter title
	 *
	 * @param title New title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter description
	 *
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter description
	 *
	 * @param description New description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter status
	 *
	 * @return Status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Setter status
	 *
	 * @param status New Status
	 */
	public void setStatus(EStatus status) {
		this.status = status.ordinal();
	}

	/**
	 * Getter seller
	 *
	 * @return Seller
	 */
	public User getSeller() {
		return seller;
	}

	/**
	 * Setter seller
	 *
	 * @param seller New Seller
	 */
	public void setSeller(User seller) {
		this.seller = seller;
	}

	/**
	 * Getter price
	 *
	 * @return Price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Setter price
	 *
	 * @param price New price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Getter sold at
	 *
	 * @return Sold at
	 */
	public Date getSoldAt() {
		return soldAt;
	}

	/**
	 * Setter sold at
	 *
	 * @param soldAt New sold at
	 */
	public void setSoldAt(Date soldAt) {
		this.soldAt = soldAt;
	}

	/**
	 * Getter number of views
	 *
	 * @return Number of view
	 */
	public Integer getViewNumber() {
		return viewNumber;
	}

	/**
	 * Setter number of views
	 *
	 * @param viewNumber New number
	 */
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

	/**
	 * Getter modification at
	 *
	 * @return Modification at
	 */
	public Date getModificationAt() {
		return modificationAt;
	}

	/**
	 * Setter modification at
	 *
	 * @param modificationAt New modification at
	 */
	public void setModificationAt(Date modificationAt) {
		this.modificationAt = modificationAt;
	}
}
