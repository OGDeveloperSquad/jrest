package com.og.jrest.reflection;

import com.og.jrest.exceptions.ControllerNotFoundException;

public interface IControllerPackage {

	String getPackageName();

	void setPackageName(String packageName);

	boolean hasController(String name);

	IControllerContext getControllerContext(String name) throws ControllerNotFoundException;

	ControllerContext[] getAllControllerContexts();

}