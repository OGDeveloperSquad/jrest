package com.og.jrest.routing;

public class RoutingFactory {

	public static IRouteTemplate newTemplate() {
		return new RouteTemplate();
	}

	public static IPathSegment newPathSegment(String segmentText) {
		return new PathSegment(segmentText);
	}

}
