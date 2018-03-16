package fr.epsi.myEpsi.models.beans;

import java.sql.Timestamp;

/**
 * A user
 */
public class User {
	/**
	 * Mail address
	 */
	private String mail;

	/**
	 * Pseudo
	 */
	private String pseudo;

	/**
	 * Password hash
	 */
	private String password;

	/**
	 * Subscription timestamp
	 */
	private Timestamp subsciption;

	/**
	 * Getter mail address
	 *
	 * @return Mail address
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter mail address
	 *
	 * @param mail New mail address
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter pseudo
	 *
	 * @return Pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}

	/**
	 * Setter pseudo
	 *
	 * @param pseudo New pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Getter password hash
	 *
	 * @return Password hash
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter password hash
	 *
	 * @param password New password hash
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter subscription timestamp
	 *
	 * @return Subscription timestamp
	 */
	public Timestamp getSubsciption() {
		return subsciption;
	}

	/**
	 * Setter subscription timestamp
	 *
	 * @param subsciption New subscription timestamp
	 */
	public void setSubsciption(Timestamp subsciption) {
		this.subsciption = subsciption;
	}
}