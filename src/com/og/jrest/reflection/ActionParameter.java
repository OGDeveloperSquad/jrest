package com.og.jrest.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import com.og.jrest.exceptions.ParameterBindingException;

class ActionParameter<T> implements IActionParameter<T> {

	private Class<T> type;

	private String name;

	private T value;

	ActionParameter(String name, Class<T> type) {
		this.type = type;
		this.name = name;
		this.value = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#getType()
	 */
	@Override
	public Class<T> getType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#setType(java.lang.Class)
	 */
	@Override
	public void setType(Class<T> type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#getValue()
	 */
	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public void setValue(String valueAsString) throws ParameterBindingException {
		T value;
		try {
			// Constructor<?>[] constructors = this.type.getDeclaredConstructors();
			Constructor<T> constructor = this.type.getDeclaredConstructor(String.class);
			value = constructor.newInstance(valueAsString);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new ParameterBindingException("Unable to invoke String constructor for class '"
					+ this.getClass().getName() + "' to instantiate parameter '" + this.getName() + "'");
		}
		this.value = value;
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
		if (obj instanceof ActionParameter) {
			ActionParameter<?> param = (ActionParameter<?>) obj;
			if (param.getClass().equals(this.type) && param.getName().equalsIgnoreCase(this.name)
					&& param.value.equals(this.value))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		String name = this.name;
		String value = this.value.toString();
		return "('" + name + "', " + value + ")";
	}

}
