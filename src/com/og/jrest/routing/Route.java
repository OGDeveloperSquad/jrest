package com.og.jrest.routing;

import java.util.Arrays;
import java.util.LinkedList;

public class Route {

	LinkedList<String> segments;
	String defaultController = "Default";
	String defaultAction = "get";

	public Route() {
		this.segments = new LinkedList<>();
	}

	public Route(String template) {
		String[] segments = template.split("/");
		this.segments = new LinkedList<>(Arrays.asList(segments));
	}

	public void setDefaultController(String controller) {
		this.defaultController = controller;
	}

	public void setDefaultAction(String action) {
		this.defaultAction = action;
	}

}
