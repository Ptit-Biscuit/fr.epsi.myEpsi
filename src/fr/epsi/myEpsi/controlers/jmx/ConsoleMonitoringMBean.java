package fr.epsi.myEpsi.controlers.jmx;

/**
 * ConsoleMonitoring MBean interface
 */
public interface ConsoleMonitoringMBean {

	/**
	 * Getter logger
	 */
	String getLogger();

	/**
	 * Set the debug level for all loggers
	 */
	String setLogDebug();

	/**
	 * Set the info level for all loggers
	 */
	String setLogInfo();

	/**
	 * Set the error level for all loggers
	 */
	String setLogError();
}
