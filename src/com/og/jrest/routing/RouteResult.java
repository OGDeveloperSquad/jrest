package com.og.jrest.routing;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.reflection.ActionParameter;
import com.og.jrest.reflection.ControllerLoader;

/**
 * Stores the data resulting from the evaluation of a oute according to a
 * request from a web client. Contains the controller that was requested, the
 * action to be invoked, and a list of the parameters required to invoke the
 * action.
 * 
 * @author Matthew.Shoemaker
 *
 */
public class RouteResult implements IRouteResult {

	private Controller controller;
	private Method action;
	private List<ActionParameter> params;

	public RouteResult() {
		this.controller = null;
		this.action = null;
		this.params = new LinkedList<>();
	}

	@Override
	public void setController(String controllerName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.controller = ControllerLoader.loadController(controllerName);
	}

	@Override
	public void setAction(String actionName) throws NoSuchMethodException {
		this.action = ControllerLoader.loadAction(this.controller, actionName, this.params);
		// Kind of a kluge but whatever
		this.syncParameterTypes(this.action.getParameters());

	}

	/**
	 * Called after the method is loaded. Simply assigns the proper type to each
	 * parameter based on the names of the parameters in the action method. Bit of a
	 * kluge but whatever.
	 * 
	 * @param methodParams the parameters of the action method
	 */
	@SuppressWarnings("unchecked")
	private void syncParameterTypes(Parameter[] methodParams) {
		for (int i = 0; i < methodParams.length; i++) {
			ActionParameter param = this.params.get(i);
			param.setType(methodParams[i].getType());
		}
	}

	@Override
	public <T> void addParam(String name, T value) {
		this.params.add(new ActionParameter<T>(name, value));
	}

	@Override
	public Object[] getParameters() {
		List<Object> params = new ArrayList<>();
		for (ActionParameter param : this.params) {
			params.add(param.getValue());
		}
		return params.toArray();
	}

	@Override
	public Controller getController() {
		return this.controller;
	}

	@Override
	public Method getAction() {
		return this.action;
	}

}
