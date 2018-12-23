package com.og.jrest.routing;

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
	 * @param routeText valid route template string
	 * @return RouteTemplate corresponding to the given route template string
	 */
	public static IRouteTemplate build(String name, String routeText) {
		Log.info("Route '" + routeText + "' has begun its build");
		IRouteTemplate route = RoutingFactory.newTemplate(name);
		// Split the given ruote template text into its constituent segments
		String[] segments = RouteBuilder.splitSegments(routeText);
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
				boolean isOptional = specifiedAsOptional(segment);
				pathSegment.setOptional(isOptional);
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

	/**
	 * Given a '/' delimited template or uri, will split the uri or template into
	 * its constituent segments.
	 * 
	 * @param template uri or template to be split
	 * @return array of strings with the segments of template
	 */
	public static String[] splitSegments(String template) {
		if (template.startsWith("/"))
			template = template.substring(1);
		return template.split("/");
	}

	/**
	 * Reports whether the given segment contains the action template specifier.
	 * 
	 * @param segment text to be checked
	 * @return Whether the given segment is the action specifier
	 */
	protected static boolean isActionSegment(String segment) {
		boolean result = segment.startsWith("{") && segment.endsWith("}");
		result = result && segment.toLowerCase().contains("action");
		return result;
	}

	/**
	 * Reports whether the given segment contains a parameter template specifier.
	 * 
	 * @param segment text to be checked
	 * @return Whether the given segment is a parameter specifier
	 */
	protected static boolean isParameterSegment(String segment) {
		return segment.startsWith("{") && segment.endsWith("}") && !isControllerSegment(segment)
				&& !isActionSegment(segment);
	}

	/**
	 * Reports whether the given parameter segment is specified as optional in the
	 * template. Template creators can specify optional by appending a parameter
	 * name with a '?' before the closing brace.
	 * 
	 * @param segment text to be checked
	 * @return whether the segment specifies the parameter as optional
	 */
	protected static boolean specifiedAsOptional(String segment) {
		return segment.endsWith("?");
	}

	/**
	 * Reports whether the given segment contains the controller template specifier.
	 * 
	 * @param segment text to be checked
	 * @return Whether the given segment is the controller specifier
	 */
	protected static boolean isControllerSegment(String segment) {
		boolean result = segment.startsWith("{") && segment.endsWith("}");
		result = result && segment.toLowerCase().contains("controller");
		return result;
	}

	/**
	 * Given a segment, will return the value specified as the default.
	 * 
	 * @param segment text to be checked
	 * @return the name of the default
	 * 
	 * @requires segment contains a default
	 */
	protected static String getDefault(String segment) {
		int start = segment.indexOf("=") + 1;
		int end = segment.indexOf("}");
		return segment.substring(start, end);
	}

	/**
	 * Determins whether the given segment specifies a default value.
	 * 
	 * @param segment text to be checked
	 * @return whether the segment contains a default specifier.
	 */
	protected static boolean specifiesDefault(String segment) {
		return segment.contains("=");
	}

}
