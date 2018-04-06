package fr.epsi.myEpsi.controlers.listeners;

import fr.epsi.myEpsi.ApplicationStartup;
import fr.epsi.myEpsi.controlers.database.interfaces.IAdDao;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.controlers.jmx.AdMonitoring;
import fr.epsi.myEpsi.controlers.jmx.ConsoleMonitoring;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Listener launched when application start up
 */
@WebListener()
public class StartupListener implements ServletContextListener, HttpSessionListener {
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(StartupListener.class);

	/**
	 * Public constructor is required by servlet spec
	 */
	public StartupListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* This method is called when the servlet context is
			initialized(when the Web application is deployed).
			You can initialize servlet context related data here. */
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name;

		try {
			name = new ObjectName("fr.epsi.myEpsi.controlers.jmx:type=AdMonitoringMBean");
			AdMonitoring adMBean = new AdMonitoring();
			mbs.registerMBean(adMBean, name);

			name = new ObjectName("fr.epsi.myEpsi.controlers.jmx:type=ConsoleMonitoringMBean");
			ConsoleMonitoring consoleMBean = new ConsoleMonitoring();
			mbs.registerMBean(consoleMBean, name);

			logger.info("Lancement monitoring (MBeans) ...");

		} catch (MalformedObjectNameException | NotCompliantMBeanException | MBeanRegistrationException | InstanceAlreadyExistsException e) {
			logger.error("Impossible d'initialiser les MBeans", e);
		}

		logger.debug(DateFormat.getInstance().format(Calendar.getInstance().getTime()) + " -> Contexte initialisé");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/* This method is invoked when the Servlet Context
			(the Web application) is undeployed or
			Application Server shuts down */
		logger.debug(DateFormat.getInstance().format(Calendar.getInstance().getTime()) + " -> Contexte détruit");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// Session is created
		logger.info("Session créée le " + DateFormat.getInstance().format(Calendar.getInstance().getTime()));
		logger.debug("Session id : " + se.getSession().getId());

		se.getSession().setAttribute("isInitialized", false);

		ApplicationStartup startup = new ApplicationStartup();
		startup.initDao(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// Session is destroyed
		HttpSession session = se.getSession();

		if (session.getAttribute("user") != null)
			session.removeAttribute("user");

		if (session.getAttribute("userDao") != null) {
			((IUserDao) session.getAttribute("userDao")).closeConnection();
			session.removeAttribute("userDao");
		}

		if (session.getAttribute("adDao") != null) {
			((IAdDao) session.getAttribute("adDao")).closeConnection();
			session.removeAttribute("adDao");
		}

		logger.debug("Session id : " + session.getId());
		logger.info("Session terminée le " + DateFormat.getInstance().format(Calendar.getInstance().getTime()));
	}
}
