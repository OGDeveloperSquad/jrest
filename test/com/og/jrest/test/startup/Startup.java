package com.og.jrest.test.startup;

import java.io.IOException;

import com.og.jrest.api.JRest;
import com.og.jrest.routing.RouteTable;

/**
 * This is an example of what a startup file for an API application would look
 * like.
 * 
 * @author matthew.shoemaker
 *
 */
public class Startup {

	public static void main(String[] args) throws IOException {

		// Register a route with the routing engine
		RouteTable.registerRoute("Default", "api/{controller=test}/{action=get}/{id?}");

		// Fire the server and start the jREST application
		JRest.start();
	}

}
