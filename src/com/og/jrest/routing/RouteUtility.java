package com.og.jrest.routing;

public class RouteUtility {

	public static String[] splitIntoSegments(String uri) {
		if (uri.startsWith("/"))
			uri = uri.substring(1);
		return uri.split("/");
	}

	public static String getControllerName(String name) {
		return name + "Controller";
	}

}
