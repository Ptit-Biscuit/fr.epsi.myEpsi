package fr.epsi.myEpsi.controlers.jmx;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Console monitoring MBean
 */
public class ConsoleMonitoring implements ConsoleMonitoringMBean {

	/**
	 * Constructor
	 */
	public ConsoleMonitoring() {
	}

	@Override
	public String getLogger() {
		return LogManager.getRootLogger().getLevel().name();
	}

	@Override
	public String setLogDebug() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);
		return "Level set to DEBUG";
	}

	@Override
	public String setLogInfo() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.INFO);
		return "Level set to INFO";
	}

	@Override
	public String setLogError() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
		return "Level set to ERROR";
	}
}
