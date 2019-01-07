package com.og.jrest.routing;

import java.util.ArrayList;
import java.util.List;

public class ParsedRoute {

	private String controllerName;
	private String actionName;
	private List<RouteParameter> params;

	public ParsedRoute() {
		this.controllerName = null;
		this.actionName = null;
		this.params = new ArrayList<>();
	}

	public void setControllerName(String name) {
		this.controllerName = name;
	}

	public String getControllerName() {
		return this.controllerName;
	}

	public void setActionName(String name) {
		this.actionName = name;
	}

	public String getActionName() {
		return this.actionName;
	}

	public RouteParameter[] getParameters() {
		return (RouteParameter[]) this.params.toArray();
	}

	public void addParameter(String name, String value) {
		RouteParameter param = new RouteParameter(name, value);
		this.params.add(param);
	}

	public String[] getParameterNames() {
		List<String> names = new ArrayList<>();
		for (RouteParameter param : this.params)
			names.add(param.getName());

		return (String[]) names.toArray();
	}

}
