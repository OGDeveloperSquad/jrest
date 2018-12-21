package com.og.jrest.routing;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

public class RouteResult {

	private Class<?> controller;
	private Method action;
	private List<ActionParameter> params;

	public RouteResult() {
		this.controller = null;
		this.action = null;
		this.params = new LinkedList<>();
	}

	public void setController(String controllerName) throws ClassNotFoundException {
		this.controller = Class.forName(controllerName + "Controller");
	}

	@SuppressWarnings("unchecked")
	public <T> void setAction(String actionName) throws NoSuchMethodException {
		Method[] methods = this.controller.getDeclaredMethods();

		// Look at all the methods for the controller
		for (Method method : methods) {
			/*
			 * If the method name matches the action name and the list of parameters is the
			 * same size, then check to see if the parameters of the method match the
			 * parameters for this route
			 */
			String methodName = method.getName();
			int paramCount = method.getParameterCount();
			if (method.getName().equals(actionName) && method.getParameterCount() == this.params.size()) {
				// Check to see all parameters of the current method match the route
				Parameter[] methodParams = method.getParameters();
				for (int i = 0; i < method.getParameterCount(); i++) {
					Parameter methodParam = methodParams[i];
					// If the parameters dont match, move on to the next method
					if (!this.params.get(i).getName().equals(methodParam.getName())) {
						break;
					} else {
						// If the parameters do match, set the type
						this.params.get(i).setType(methodParam.getClass());
					}
					// We've matched all the parameters successfully, return
					if (i == method.getParameterCount() - 1 && this.params.get(i).equals(methodParam)) {
						this.action = method;
						return;
					}
				}
			}
		}
		// No method was found, so throw exception up the stack
		throw new NoSuchMethodException();
	}

	public void setParams(List<ActionParameter> params) {
		this.params = params;
	}

	public <T> void addParam(String name, T value) {
		this.params.add(new ActionParameter<T>(name, value));
	}

	public List<ActionParameter> getParams() {
		return this.params;
	}

	public Class<?> getController() {
		return this.controller;
	}

	public Method getAction() {
		return this.action;
	}

}
