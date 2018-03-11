package fr.epsi.myEpsi.misc;

import javax.servlet.http.HttpServletRequest;

/**
 * Miscellaneous class for servlet
 */
public class ServletUtil {

	/**
	 * Retrieve parameter from <code>request</code>
	 *
	 * @param request       Request
	 * @param parameterName Name of parameter
	 * @return The parameter value or <code>null</code>
	 */
	public static String retrieveValue(HttpServletRequest request, String parameterName) {
		if (parameterName == null || parameterName.isEmpty())
			return "";

		String value = request.getParameter(parameterName);

		if (value == null || value.trim().length() == 0) {
			return "";
		} else {
			return value.trim();
		}
	}
}
