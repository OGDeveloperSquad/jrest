package com.og.jrest.reflection;

import com.og.jrest.api.Controller;

public interface IControllerContext {

	String getName();

	Controller getControllerInstance();

	IActionMethod getAction(String name);

}