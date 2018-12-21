package com.og.jrest.routing;

import java.util.LinkedList;
import java.util.List;

public class RouteCollection {

	protected static List<RouteTemplate> routes;

	static {
		RouteCollection.routes = new LinkedList<>();
	}

	public static RouteResult evaluateRoute(String uri) throws ClassNotFoundException, NoSuchMethodException {
		RouteResult result = new RouteResult();
		String[] segments = uri.split("/");
		for (int i = 0; i < routes.size(); i++) {
			RouteTemplate route = RouteCollection.routes.get(i);
			if (isMatchingRoute(route.getSegments(), segments)) {
				result = generateRouteResult(routes.get(i), segments);
				break;
			}
		}

		return result;
	}

	public static void addRoute(RouteTemplate template) {
		RouteCollection.routes.add(template);
	}

	private static RouteResult generateRouteResult(RouteTemplate route, String[] segments)
			throws ClassNotFoundException, NoSuchMethodException {
		RouteResult result = new RouteResult();
		String[] templateSegments = route.getSegments();
		String action = null;
		for (int i = 0; i < segments.length; i++) {
			String urlSegment = segments[i];
			String templateSegment = templateSegments[i];
			if (templateSegment.toLowerCase().equals("{controller}")) {
				result.setController(urlSegment);
			} else if (templateSegment.toLowerCase().equals("{action}")) {
				action = urlSegment;
			} else if (templateSegment.startsWith("{")) {
				templateSegment = templateSegment.replace("{", "").replace("}", "");
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
