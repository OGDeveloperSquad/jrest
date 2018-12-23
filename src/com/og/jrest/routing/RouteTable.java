package com.og.jrest.routing;

import java.util.LinkedList;
import java.util.List;

import com.og.jrest.logging.Log;

/**
 * Holds the routes for this application.
 * 
 * @author Matthew.Shoemaker
 *
 */
public class RouteTable {

	protected static List<IRouteTemplate> routes;

	static {
		RouteTable.routes = new LinkedList<>();
	}

	/**
	 * Given a uri from a request, will evaluate the route against the route table
	 * and if it finds a route, will find the controller and action specified by the
	 * request. Throws exception if no matching route could be found
	 * 
	 * @param uri reqeusted route
	 * @return The controller, method, and list of params requested by the route.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static RouteResult evaluateRoute(String uri)
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		IRouteTemplate route = findRoute(uri);
		RouteResult result = null;
		if (route != null) {
			result = generateRouteResult(route, RouteBuilder.splitSegments(uri));
		}

		return result;
	}

	private static IRouteTemplate findRoute(String uri) {
		IRouteTemplate result = null;
		String[] segments = RouteBuilder.splitSegments("/");
		for (int i = 0; i < routes.size(); i++) {
			IRouteTemplate route = RouteTable.routes.get(i);
			if (isMatchingRoute(route.getSegments(), segments)) {
				result = route;
				break;
			}
		}

		return result;
	}

	public static void registerRoute(String name, String template) {
		Log.info("Registering Route:  '" + template + "'");
		IRouteTemplate route = RouteBuilder.build(name, template);
		RouteTable.routes.add(route);
		Log.info("Route has been successfully registered\n");
	}

	private static RouteResult generateRouteResult(IRouteTemplate route, String[] segments)
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		RouteResult result = new RouteResult();
		String[] templateSegments = route.getSegments();
		String action = null;
		for (int i = 0; i < segments.length; i++) {
			String urlSegment = segments[i];
			String templateSegment = templateSegments[i];
			if (RouteBuilder.isControllerSegment(templateSegment)) {
				result.setController(urlSegment);
			} else if (RouteBuilder.isActionSegment(templateSegment)) {
				action = urlSegment;
			} else if (RouteBuilder.isParameterSegment(templateSegment)) {
				templateSegment = templateSegment.replace("{", "").replace("}", "").replace("?", "");
				result.addParam(templateSegment, urlSegment);
			}
		}
		if (action != null) {
			result.setAction(action);
		} else {
			result.setAction(route.getDefaultAction());
		}

		return result;

	}

	private static boolean isMatchingRoute(String[] templateSegments, String[] urlSegments) {
		boolean result = true;
		if (templateSegments.length == urlSegments.length) {
			for (int i = 0; i < templateSegments.length; i++) {
				String segment = templateSegments[i];
				if (!segment.startsWith("{") && !segment.equals(urlSegments[i])) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

}
