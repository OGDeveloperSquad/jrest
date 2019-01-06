package com.og.jrest.reflection;

import java.lang.reflect.Parameter;

class ActionParameter<T> implements IActionParameter<T> {

	private Class<T> type;

	private String name;

	private T value;

	public ActionParameter(String name, Class<T> type) {
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
	public T getValue() {
		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#setValue(T)
	 */
	@Override
	public void setValue(T value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#equals(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionParameter#toString()
	 */
	@Override
	public String toString() {
		String name = this.name;
		String value = this.value.toString();
		return "('" + name + "', " + value + ")";
	}

}
