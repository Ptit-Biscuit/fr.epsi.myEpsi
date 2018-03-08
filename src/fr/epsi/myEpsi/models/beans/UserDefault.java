package fr.epsi.myEpsi.models.beans;

import fr.epsi.myEpsi.misc.HashUtil;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Default user
 */
public class UserDefault extends User {

	/**
	 * Constructor
	 */
	public UserDefault() {
		super();
		this.setMail("default@mail");
		this.setPassword(HashUtil.hashPassword("defaultPass"));
		this.setSubsciption(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}
}
