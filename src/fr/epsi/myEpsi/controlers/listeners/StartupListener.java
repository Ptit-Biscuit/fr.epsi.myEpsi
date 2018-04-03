package fr.epsi.myEpsi.controlers.listeners;

import fr.epsi.myEpsi.ApplicationStartup;
import fr.epsi.myEpsi.controlers.database.interfaces.IUserDao;
import fr.epsi.myEpsi.controlers.jmx.Ad;
import fr.epsi.myEpsi.controlers.jmx.AdMBean;
import fr.epsi.myEpsi.controlers.jmx.Premier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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
        ObjectName name = null;
        try {
            name = new ObjectName("fr.epsi.myEpsi.controlers.jmx:type=AdMBean");
            Ad aMBean = new Ad();
            mbs.registerMBean(aMBean, name);

            name = new ObjectName("fr.epsi.myEpsi.controlers.jmx:type=PremierMBean");
            Premier mbean = new Premier();
            mbs.registerMBean(mbean, name);


            System.out.println("Lancement ...");

        } catch (MalformedObjectNameException | NotCompliantMBeanException | MBeanRegistrationException | InstanceAlreadyExistsException | NullPointerException e) {
            e.printStackTrace();
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

        ApplicationStartup startup = new ApplicationStartup();
        startup.initDao(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session is destroyed
        if (se.getSession().getAttribute("user") != null)
            se.getSession().removeAttribute("user");

        if (se.getSession().getAttribute("userDao") != null) {
            ((IUserDao) se.getSession().getAttribute("userDao")).closeConnection();
            se.getSession().removeAttribute("userDao");
        }

        if (se.getSession().getAttribute("adDao") != null) {
            ((IUserDao) se.getSession().getAttribute("adDao")).closeConnection();
            se.getSession().removeAttribute("adDao");
        }

        logger.info("Session terminée le " + DateFormat.getInstance().format(Calendar.getInstance().getTime()));
    }
}
