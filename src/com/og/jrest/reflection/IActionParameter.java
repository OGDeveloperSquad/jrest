package com.og.jrest.reflection;

public interface IActionParameter<T> {

	Class<T> getType();

	void setType(Class<T> type);

	String getName();

	T getValue();

	void setValue(T value);

	boolean equals(Object obj);

	String toString();

}