package com.og.jrest.reflection;

import com.og.jrest.exceptions.ParameterBindingException;

public interface IActionParameter<T> {

	Class<T> getType();

	void setType(Class<T> type);

	String getName();

	Object getValue();

	boolean equals(Object obj);

	String toString();

	void setValue(String valueAsString) throws ParameterBindingException;

}