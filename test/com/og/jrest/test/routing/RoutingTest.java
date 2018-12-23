package com.og.jrest.test.routing;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.og.jrest.api.Controller;
import com.og.jrest.routing.RouteResult;
import com.og.jrest.routing.RouteTable;
import com.og.jrest.routing.RouteTemplate;

class RoutingTest {

	@Test
	void test()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RouteTable.registerRoute(new RouteTemplate("api1/{controller}/{action}/{things}"));
		RouteTable.registerRoute(new RouteTemplate("api2/{controller}/{action}/{stuff}"));
		RouteTable.registerRoute(new RouteTemplate("api3/{controller}/{action}/{otherthings}"));
		RouteTable.registerRoute(new RouteTemplate("api/{controller}/{action}"));
		RouteTable.registerRoute(new RouteTemplate("api4/{controller}/{action}/{something}"));

		try {
			RouteResult result = RouteTable.evaluateRoute("api/com.og.jrest.test.controllers.Test/stuff");
			Controller controller = result.getController();
			controller.reflectionTest = "things";
			result.getAction().invoke(controller);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
