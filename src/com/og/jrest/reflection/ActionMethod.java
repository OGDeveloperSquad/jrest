package com.og.jrest.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

class ActionMethod implements IActionMethod {

	private String name;

	private List<IActionParameter<?>> params;

	public ActionMethod(Method method) {
		this.name = method.getName();
		this.setParams(method.getParameters());
	}

	public ActionMethod(String name, Parameter[] params) {
		this.name = name;
		this.params = new ArrayList<>();
		this.setParams(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionMethod#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionMethod#setname(java.lang.String)
	 */
	@Override
	public void setname(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionMethod#getParam(java.lang.String)
	 */
	@Override
	public IActionParameter<?> getParam(String name) {
		for (IActionParameter<?> param : this.params) {
			if (param.getName().equals(name))
				return param;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IActionMethod#getParams()
	 */
	@Override
	public IActionParameter<?>[] getParams() {
		return (IActionParameter<?>[]) this.params.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.og.jrest.reflection.IActionMethod#addParam(java.lang.reflect.Parameter)
	 */
	@Override
	public void addParam(Parameter param) {
		Class<?> paramType = param.getClass();
		IActionParameter<?> actionParam = ReflectionFactory.newActionParameter(param.getName(), paramType);
		this.params.add(actionParam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.og.jrest.reflection.IActionMethod#setParams(java.lang.reflect.Parameter[]
	 * )
	 */
	@Override
	public void setParams(Parameter[] params) {
		for (Parameter param : params) {
			this.addParam(param);
		}
	}
}
