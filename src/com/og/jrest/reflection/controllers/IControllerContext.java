package com.og.jrest.reflection.controllers;

import com.og.jrest.api.Controller;
import com.og.jrest.exceptions.JRestServerException;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.reflection.IActionMethod;
import com.og.jrest.routing.RouteParameter;

public interface IControllerContext {

	String getName();

	Controller getControllerInstance();

	IActionMethod getAction(String name) throws JRestServerException;

	IActionMethod getAction(String name, String[] parameterNames) throws JRestServerException;

	IResponse invoke(Controller controller, String actionName, RouteParameter[] params) throws JRestServerException;

}