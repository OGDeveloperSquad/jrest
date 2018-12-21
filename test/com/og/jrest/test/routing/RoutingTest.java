package com.og.jrest.test.routing;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.og.jrest.routing.RouteCollection;
import com.og.jrest.routing.RouteResult;
import com.og.jrest.routing.RouteTemplate;

class RoutingTest {

	@Test
	void test() {
		RouteTemplate route = new RouteTemplate("api/{controller}/{action}/{id}");
		RouteCollection.addRoute(route);

		try {
			RouteResult result = RouteCollection.evaluateRoute("api/com.og.jrest.test.controllers.Test/stuff/things");
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
