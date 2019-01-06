package com.og.jrest.routing;

import java.util.ArrayList;
import java.util.List;

import com.og.jrest.exceptions.InvalidRouteException;
import com.og.jrest.logging.Log;
import com.og.jrest.reflection.IControllerPackage;
import com.og.jrest.reflection.ReflectionFactory;

/**
 * Holds the routes for this application.
 * 
 * @author Matthew.Shoemaker
 *
 */
public class RouteTable {

	protected static List<IRouteTemplate> routes;
	protected static List<IControllerPackage> controllerPackages;

	static {
		RouteTable.routes = new ArrayList<>();
		RouteTable.controllerPackages = new ArrayList<>();
	}

	public static void RegisterControllers() throws InstantiationException, IllegalAccessException {
		// TODO read package names from api.config
		// String[] packageNames = ConfigurationManager.getControllerPackageNames();

		String packageName = "com.og.jrest.test.controllers";
		RouteTable.registerControllerPackage(packageName);
	}

	private static void registerControllerPackage(String packageName)
			throws InstantiationException, IllegalAccessException {
		IControllerPackage controllerPackage = ReflectionFactory.newControllerPackage(packageName);
		RouteTable.controllerPackages.add(controllerPackage);
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

	public static IRouteTemplate findCorrespondingRoute(String uri) {
		IRouteComparator uriComparator = RoutingFactory.newRouteComparator(uri);
		for (IRouteTemplate template : RouteTable.routes) {
			if (uriComparator.matches(template))
				return template;
		}

		return null;
	}

	// private static RouteResult generateRouteResult(IRouteTemplate route, String[]
	// segments)
	// throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
	// IllegalAccessException,
	// Exception {
	// RouteResult result = new RouteResult();
	// String[] templateSegments = route.getSegments();
	// String action = null;
	// for (int i = 0; i < segments.length; i++) {
	// String urlSegment = segments[i];
	// String templateSegment = templateSegments[i];
	// if (RouteBuilder.isControllerSegment(templateSegment)) {
	// result.setController(urlSegment);
	// } else if (RouteBuilder.isActionSegment(templateSegment)) {
	// action = urlSegment;
	// } else if (RouteBuilder.isParameterSegment(templateSegment)) {
	// templateSegment = templateSegment.replace("{", "").replace("}",
	// "").replace("?", "");
	// result.addParam(templateSegment, urlSegment);
	// }
	// }
	// if (action != null) {
	// result.setAction(action);
	// } else {
	// result.setAction(route.getDefaultAction());
	// }
	//
	// return result;
	//
	// }
	//
	// private static boolean isMatchingRoute(String[] templateSegments, String[]
	// urlSegments) {
	// boolean result = true;
	// if (templateSegments.length == urlSegments.length) {
	// for (int i = 0; i < templateSegments.length; i++) {
	// String segment = templateSegments[i];
	// if (!segment.startsWith("{") && !segment.equals(urlSegments[i])) {
	// result = false;
	// break;
	// }
	// }
	// }
	// return result;
	// }
	//
	// private static IRouteTemplate findRoute(String uri) {
	// IRouteTemplate result = null;
	// String[] segments = RouteBuilder.splitTemplateStringIntoSegments("/");
	// for (int i = 0; i < routes.size(); i++) {
	// IRouteTemplate route = RouteTable.routes.get(i);
	// // if (isMatchingRoute(route.getSegments(), segments)) {
	// // result = route;
	// // break;
	// // }
	// }
	//
	// return result;
	// }
	//
	// public static RouteResult evaluateRoute(String uri) throws
	// ClassNotFoundException, NoSuchMethodException,
	// InstantiationException, IllegalAccessException, Exception {
	// IRouteTemplate route = findRoute(uri);
	// RouteResult result = null;
	// if (route != null) {
	// result = generateRouteResult(route,
	// RouteBuilder.splitTemplateStringIntoSegments(uri));
	// }
	//
	// return result;
	// }

}
