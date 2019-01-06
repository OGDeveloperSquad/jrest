package com.og.jrest.reflection;

import java.lang.reflect.Parameter;

public interface IActionMethod {

	String getName();

	void setname(String name);

	IActionParameter<?> getParam(String name);

	IActionParameter<?>[] getParams();

	void addParam(Parameter param);

	void setParams(Parameter[] params);

}