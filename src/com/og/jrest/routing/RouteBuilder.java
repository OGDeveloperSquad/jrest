package com.og.jrest.routing;

import com.og.jrest.logging.Log;

class RouteBuilder {

	public static IRouteTemplate build(String routeText) {
		Log.info("Route '" + routeText + "' has begun its build");
		IRouteTemplate route = RoutingFactory.newTemplate();
		// Split the given ruote template text into its constituent segments
		String[] segments = routeText.split("/");
		/*
		 * Add every segment to the route, setting the necessary fields along the way
		 */
		for (String segment : segments) {
			IPathSegment pathSegment = RoutingFactory.newPathSegment(segment);
			/*
			 * If this segment is the {controller} segment and it specifies a default
			 * controller, then set the default controller for the route. For example, the
			 * segment {controller=example} would set the default controller for the route
			 * to 'example'. Check for default action using the same logic.
			 */
			if (isControllerSegment(segment) && specifiesDefault(segment)) {
				String defaultController = getDefault(segment);
				route.setDefaultController(defaultController);
				Log.info("A default controller has been specified: " + defaultController);
			} else if (isActionSegment(segment) && specifiesDefault(segment)) {
				String defaultAction = getDefault(segment);
				route.setDefaultAction(defaultAction);
				Log.info("A default action has been specified: " + defaultAction);
			} else if (isParameterSegment(segment)) {
				Log.info("Parmeter segment detected: " + segment);
			} else {
				Log.info("Static segment detected: " + segment);
			}

			// The segment is configured and the route has been updated if necessary, so add
			// the segment to the route
			route.addSegment(pathSegment);
		}

		return route;
	}

	private static boolean isActionSegment(String segment) {
		boolean result = segment.startsWith("{") && segment.endsWith("}");
		result = result && segment.toLowerCase().contains("action");
		return result;
	}

	private static boolean isParameterSegment(String segment) {
		return segment.startsWith("{") && segment.endsWith("}") && !isControllerSegment(segment)
				&& !isActionSegment(segment);
	}

	private static boolean isControllerSegment(String segment) {
		boolean result = segment.startsWith("{") && segment.endsWith("}");
		result = result && segment.toLowerCase().contains("controller");
		return result;
	}

	private static String getDefault(String segment) {
		int start = segment.indexOf("=") + 1;
		int end = segment.indexOf("}");
		return segment.substring(start, end);
	}

	private static boolean specifiesDefault(String segment) {
		return segment.contains("=");
	}

}
