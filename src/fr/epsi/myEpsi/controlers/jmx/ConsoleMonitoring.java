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
	public void getLogger() {
		LogManager.getRootLogger().getLevel();
	}

	@Override
	public void setLogDebug() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);
	}

	@Override
	public void setLogInfo() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.INFO);
	}

	@Override
	public void setLogError() {
		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
	}
}
