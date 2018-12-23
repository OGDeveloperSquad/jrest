package com.og.jrest.routing;

public class RoutingFactory {

	public static IRouteTemplate newTemplate(String name) {
		return new RouteTemplate(name);
	}

	public static IPathSegment newPathSegment(String segmentText) {
		return new PathSegment(segmentText);
	}

}
