package com.og.jrest.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.routing.RouteParameter;

class ActionMethod implements IActionMethod {

	private String name;

	private Method method;

	private List<IActionParameter<?>> params;

	public ActionMethod(Method method) {
		this.name = method.getName();
		this.method = method;
		this.setParams(method.getParameters());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setname(String name) {
		this.name = name;
	}

	@Override
	public IActionParameter<?> getParam(String name) {
		for (IActionParameter<?> param : this.params) {
			if (param.getName().equals(name))
				return param;
		}

		return null;
	}

	@Override
	public IActionParameter<?>[] getParams() {
		return (IActionParameter<?>[]) this.params.toArray();
	}

	@Override
	public void addParam(Parameter param) {
		Class<?> paramType = param.getClass();
		IActionParameter<?> actionParam = ReflectionFactory.newActionParameter(param.getName(), paramType);
		this.params.add(actionParam);
	}

	@Override
	public void setParams(Parameter[] params) {
		for (Parameter param : params) {
			this.addParam(param);
		}
	}

	@Override
	public IResponse invoke(Controller controller, RouteParameter[] params)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		return null;
	}
}
