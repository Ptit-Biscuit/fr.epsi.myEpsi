package fr.epsi.myEpsi.misc;

import fr.epsi.myEpsi.models.forms.LoginForm;
import org.apache.logging.log4j.LogManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Miscellaneous class for hash
 */
public class HashUtil {
	/**
	 * Hash a password (SHA-256)
	 *
	 * @param password Password to hash
	 * @return Hash of password
	 */
	public static String hashPassword(String password) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] bytes = md.digest();

			for (byte b : bytes) {
				hash.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LogManager.getLogger(LoginForm.class).error("Password impossible to hash", e);
		}

		return hash.toString();
	}
}
