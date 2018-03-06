package fr.epsi.myEpsi.controlers.database.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Database exception handler
 */
public class DaoException extends RuntimeException {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(DaoException.class);

	/**
	 * Constructor
	 *
	 * @param message Message
	 */
	public DaoException(String message) {
		super(message);
		logger.error(message);
	}

	/**
	 * Constructor
	 *
	 * @param cause Cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
		logger.error(cause);
	}

	/**
	 * Constructor
	 *
	 * @param message Message
	 * @param cause   Cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}
}