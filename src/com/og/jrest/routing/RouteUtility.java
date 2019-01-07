package com.og.jrest.routing;

public class RouteUtility {

	public static String[] splitIntoSegments(String uri) {
		if (uri.startsWith("/"))
			uri = uri.substring(1);
		return uri.split("/");
	}

}
