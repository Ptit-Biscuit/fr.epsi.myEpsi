package fr.epsi.myEpsi.controlers.servlets;

import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Generic servlet used to log methods (TRACE level log)
 */
public class GenericServlet extends HttpServlet {
	/**
	 * Public constructor is required by servlet spec
	 */
	public GenericServlet() {
		LogManager.getLogger(this.getClass()).info("Arrivée dans " + this.getClass().getSimpleName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogManager.getLogger(this.getClass()).debug("Exécution doGet() " + this.getClass().getSimpleName());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogManager.getLogger(this.getClass()).debug("Exécution doPost() " + this.getClass().getSimpleName());
	}
}
