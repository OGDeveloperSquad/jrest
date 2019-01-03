package com.og.jrest.test.startup;

import java.io.IOException;

import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteTable;
import com.og.jrest.server.WebApi;

/**
 * This is an example of what a startup file for an API application would look like.
 * 
 * @author matthew.shoemaker
 *
 */
public class Startup {

	public static void main(String[] args) throws IOException {

		Log.info("Building Routes:\n");

		// Register a route with the routing engine
		RouteTable.registerRoute("Default", "api/{controller=Test}/{action=testMethod}/{id?}");

		// Fire the server and start the jREST application
		WebApi.start();
	}

}
