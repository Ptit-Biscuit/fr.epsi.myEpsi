package fr.epsi.myEpsi.controlers.database.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Database configurations' exception handler
 */
public class DaoConfigurationException extends RuntimeException {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(DaoConfigurationException.class);

	/**
	 * Constructor
	 *
	 * @param message Message
	 */
	public DaoConfigurationException(String message) {
		super(message);
		logger.error(message);
	}

	/**
	 * Constructor
	 *
	 * @param cause Cause
	 */
	public DaoConfigurationException(Throwable cause) {
		super(cause);
		logger.error(cause);
	}

	/**
	 * Constructor
	 *
	 * @param message Message
	 * @param cause   Cause
	 */
	public DaoConfigurationException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}
}
