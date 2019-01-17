package com.og.jrest.server;

import java.lang.reflect.InvocationTargetException;

import com.og.jrest.api.Controller;
import com.og.jrest.exceptions.ActionMethodInvocationException;
import com.og.jrest.exceptions.JRestException;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.logging.Log;
import com.og.jrest.reflection.Reflection;
import com.og.jrest.reflection.controllers.IControllerContext;
import com.og.jrest.routing.IRouteTemplate;
import com.og.jrest.routing.ParsedRoute;
import com.og.jrest.routing.RouteParameter;
import com.og.jrest.routing.RouteParser;
import com.og.jrest.routing.RouteTable;

public class RouteEvaluator {

	public static IResponse evaluateRoute(Request request) throws JRestException {
		String uri = request.getUri();
		IRouteTemplate route = RouteTable.findRoute(uri);
		ParsedRoute parsedRoute = RouteParser.parse(route, uri);

		String controllerName = parsedRoute.getControllerName();
		String actionName = parsedRoute.getActionName();
		if (actionName == null) {
			String httpVerb = request.getVerb().toString().toLowerCase();
			actionName = httpVerb;
		}
		RouteParameter[] routeParams = parsedRoute.getParameters();

		IControllerContext controllerContext = Reflection.getControllerContext(controllerName);
		Controller controller = controllerContext.getControllerInstance();
		controller.setRequest(request);
		IResponse response = null;
		try {
			response = controllerContext.invoke(controller, actionName, routeParams);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Log.exception(e);
			throw new ActionMethodInvocationException("Something went wrong while invoking the action.");
		}

		return response;
	}

}
