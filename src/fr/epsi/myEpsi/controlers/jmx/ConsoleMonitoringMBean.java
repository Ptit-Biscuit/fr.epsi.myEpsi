package fr.epsi.myEpsi.controlers.jmx;

/**
 * ConsoleMonitoring MBean interface
 */
public interface ConsoleMonitoringMBean {

	/**
	 * Getter logger
	 */
	void getLogger();

	/**
	 * Set the debug level for all loggers
	 */
	void setLogDebug();

	/**
	 * Set the info level for all loggers
	 */
	void setLogInfo();

	/**
	 * Set the error level for all loggers
	 */
	void setLogError();
}
