package com.og.jrest.routing;

import java.util.ArrayList;
import java.util.List;

import com.og.jrest.exceptions.InvalidRouteException;
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
		RouteTable.routes = new ArrayList<>();
	}

	public static void registerRoute(String name, String template) {
		Log.info("Registering Route:  '" + template + "'");
		IRouteTemplate route = null;
		try {
			route = RouteBuilder.build(name, template);
		} catch (InvalidRouteException e) {
			Log.exception(e);
		}
		if (route != null) {
			RouteTable.routes.add(route);
			Log.info("Route has been successfully registered\n");
		}
	}

	public static IRouteTemplate findRoute(String uri) {
		IRouteComparator uriComparator = RoutingFactory.newRouteComparator(uri);
		for (IRouteTemplate template : RouteTable.routes) {
			if (uriComparator.matches(template))
				return template;
		}

		return null;
	}

}
