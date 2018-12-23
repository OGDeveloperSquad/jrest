package com.og.jrest.reflection;

import java.lang.reflect.Parameter;

public class ActionParameter<T> {

	private Class<T> type;

	private String name;

	private T value;

	public ActionParameter(String key, T value) {
		this.type = null;
		this.name = key;
		this.value = value;
	}

	public Class<T> getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public T getValue() {
		return this.value;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Parameter) {
			Parameter param = (Parameter) obj;
			if (param.getClass().equals(this.type) && param.getName().equals(this.name)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("Type: '%s', Value: '%s'", this.type.toString(), this.value.toString());
	}

}
