package com.og.jrest.routing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RouteTemplate {

	private List<String> segments;
	private String defaultController = "Default";
	private String defaultAction = "get";

	public RouteTemplate() {
		this.segments = new LinkedList<>();
	}

	public RouteTemplate(String template) {
		String[] segments = template.split("/");
		this.segments = new LinkedList<>(Arrays.asList(segments));
	}

	public void setDefaultController(String controller) {
		this.defaultController = controller;
	}

	public void setDefaultAction(String action) {
		this.defaultAction = action;
	}

	public String[] getSegments() {
		String[] array = new String[this.segments.size()];
		return this.segments.toArray(array);
	}

	public String getDefaultController() {
		return this.defaultController;
	}

	public String getDefaultAction() {
		return this.defaultAction;
	}

}
