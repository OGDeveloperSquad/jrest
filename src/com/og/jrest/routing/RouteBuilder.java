package com.og.jrest.routing;

import com.og.jrest.exceptions.InvalidRouteException;
import com.og.jrest.logging.Log;

/**
 * Handles the logic necessary to contruct a route template based on a given
 * template string.
 * 
 * @author Matthew.Shoemaker
 *
 */
class RouteBuilder {

	/**
	 * Given a string that specifies a route template, parse through the string and
	 * construct the corresponding route template.
	 * 
	 * @param routeText
	 *            valid route template string
	 * @return RouteTemplate corresponding to the given route template string
	 * @throws InvalidRouteException
	 */
	public static IRouteTemplate build(String routeName, String routeText) throws InvalidRouteException {
		Log.info("Route '" + routeText + "' has begun its build");

		IRouteTemplate route = RoutingFactory.newTemplate(routeName);
		String[] segments = RouteUtility.splitIntoSegments(routeText);
		Log.info("Route segments detected as:");
		Log.info("\t" + segments.toString());
		for (String segment : segments) {
			IPathSegment pathSegment = RoutingFactory.newPathSegment(segment);
			route.addSegment(pathSegment);
		}

		return route;
	}

}
