package com.og.jrest.reflection;

import java.lang.reflect.InvocationTargetException;

import com.og.jrest.api.Controller;
import com.og.jrest.exceptions.ActionMethodNotFoundException;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.routing.RouteParameter;

public interface IControllerContext {

	String getName();

	Controller getControllerInstance();

	IActionMethod getAction(String name) throws ActionMethodNotFoundException;

	IActionMethod getAction(String name, String[] parameterNames) throws ActionMethodNotFoundException;

	IResponse invoke(String actionName, RouteParameter[] params) throws ActionMethodNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}