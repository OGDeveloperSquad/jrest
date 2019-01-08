package com.og.jrest.routing;

import com.og.jrest.exceptions.InvalidRouteException;

public class RoutingFactory {

	public static IRouteTemplate newTemplate(String name) {
		return new RouteTemplate(name);
	}

	public static IPathSegment newPathSegment(String segmentText) throws InvalidRouteException {
		return new PathSegment(segmentText);
	}

	public static IRouteComparator newRouteComparator(String requestUri) {
		return new RouteComparator(requestUri);
	}

}
