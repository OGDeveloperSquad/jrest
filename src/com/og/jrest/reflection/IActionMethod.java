package com.og.jrest.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import com.og.jrest.api.Controller;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.routing.RouteParameter;

public interface IActionMethod {

	String getName();

	void setname(String name);

	IActionParameter<?> getParam(String name);

	IActionParameter<?>[] getParams();

	void addParam(Parameter param);

	void setParams(Parameter[] params);

	IResponse invoke(Controller controller, RouteParameter[] params)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}