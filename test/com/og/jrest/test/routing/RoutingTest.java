package com.og.jrest.test.routing;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.og.jrest.routing.RouteCollection;
import com.og.jrest.routing.RouteResult;
import com.og.jrest.routing.RouteTemplate;
import com.og.jrest.test.controllers.TestController;

class RoutingTest {

	@Test
	void test()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RouteTemplate route = new RouteTemplate("api1/{controller}/{action}/{things}");
		RouteTemplate route1 = new RouteTemplate("api2/{controller}/{action}/{stuff}");
		RouteTemplate route2 = new RouteTemplate("api3/{controller}/{action}/{otherthings}");
		RouteTemplate route3 = new RouteTemplate("api/{controller}/{action}");
		RouteTemplate route4 = new RouteTemplate("api4/{controller}/{action}/{something}");
		RouteCollection.addRoute(route);
		RouteCollection.addRoute(route1);
		RouteCollection.addRoute(route2);
		RouteCollection.addRoute(route3);
		RouteCollection.addRoute(route4);

		try {
			RouteResult result = RouteCollection.evaluateRoute("api/com.og.jrest.test.controllers.Test/stuff");
			TestController controller = (TestController) result.getController().newInstance();
			controller.variable = "things";
			result.getAction().invoke(controller);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
