package com.og.jrest.routing;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.reflection.ActionParameter;
import com.og.jrest.reflection.ControllerLoader;

public class RouteResult {

	private Controller controller;
	private Method action;
	private List<ActionParameter> params;

	public RouteResult() {
		this.controller = null;
		this.action = null;
		this.params = new LinkedList<>();
	}

	public void setController(String controllerName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.controller = ControllerLoader.loadController(controllerName);
	}

	public <T> void setAction(String actionName) throws NoSuchMethodException {
		this.action = ControllerLoader.loadAction(this.controller, actionName, this.params);
		// Kind of a kluge but whatever
		this.syncParameterTypes(this.action.getParameters());

	}

	@SuppressWarnings("unchecked")
	private void syncParameterTypes(Parameter[] methodParams) {
		for (int i = 0; i < methodParams.length; i++) {
			ActionParameter param = this.params.get(i);
			param.setType(methodParams[i].getClass());
		}
	}

	public void setParams(List<ActionParameter> params) {
		this.params = params;
	}

	public <T> void addParam(String name, T value) {
		this.params.add(new ActionParameter<T>(name, value));
	}

	public Object[] getParams() {
		List<Object> params = new ArrayList<>();
		for (ActionParameter param : this.params) {
			params.add(param.getValue());
		}
		return params.toArray();
	}

	public Controller getController() {
		return this.controller;
	}

	public Method getAction() {
		return this.action;
	}

}
