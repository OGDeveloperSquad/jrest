package com.og.jrest.routing;

import java.util.LinkedList;
import java.util.List;

public class RouteTemplate implements IRouteTemplate {

	private List<IPathSegment> pathSegments;
	private String defaultController = "Default";
	private String defaultAction = "get";

	public RouteTemplate() {
		this.pathSegments = new LinkedList<>();
	}

	@Override
	public void setDefaultController(String controller) {
		this.defaultController = controller;
	}

	@Override
	public void setDefaultAction(String action) {
		this.defaultAction = action;
	}

	@Override
	public String[] getSegments() {
		String[] array = new String[this.pathSegments.size()];
		return this.pathSegments.toArray(array);
	}

	@Override
	public String getDefaultController() {
		return this.defaultController;
	}

	@Override
	public String getDefaultAction() {
		return this.defaultAction;
	}

	@Override
	public void addSegment(IPathSegment segment) {
		this.pathSegments.add(segment);

	}

}
