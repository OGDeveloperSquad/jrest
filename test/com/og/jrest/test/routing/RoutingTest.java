package com.og.jrest.test.routing;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.og.jrest.api.Controller;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.Response;
import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteResult;
import com.og.jrest.routing.RouteTable;

class RoutingTest {

	@Test
	void test()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RouteTable.registerRoute("Route1", "api1/{controller}/{action}/{id}");
		RouteTable.registerRoute("Route2", "api2/{controller}/{action}/{stuff}");
		RouteTable.registerRoute("Route3", "api3/{controller}/{action}/{otherthings}");
		RouteTable.registerRoute("Route4", "api/{controller}/{action}");
		RouteTable.registerRoute("Route5", "api4/{controller}/{action}/{something}");

		try {
			RouteResult routeResult = RouteTable.evaluateRoute("api/test/actionTestGet/foobar");
			Controller controller = routeResult.getController();
			controller.request = new Request("GET / HTTP/1.1");
			Response response;
			if (routeResult.getParameters().length > 0) {
				Object[] params = routeResult.getParameters();
				response = (Response) routeResult.getAction().invoke(controller, params);
			} else {
				response = (Response) routeResult.getAction().invoke(controller);
			}
			Log.debug(response.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
