package com.og.jrest.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.logging.Log;

class ControllerContext implements IControllerContext {

	private String name;
	// class is a java keyword, so convention is to use clazz
	private Class<?> clazz;
	private List<IActionMethod> actions;

	public ControllerContext(Controller controller) {
		this.name = controller.getClass().getName();
		this.clazz = controller.getClass();
		this.actions = new ArrayList<>();

		Method[] methods = clazz.getDeclaredMethods();
		this.setActions(methods);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IControllerData#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IControllerData#getControllerInstance()
	 */
	@Override
	public Controller getControllerInstance() {
		try {
			return (Controller) this.clazz.newInstance();
		} catch (InstantiationException e) {
			Log.exception(e);
		} catch (IllegalAccessException e) {
			Log.exception(e);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.og.jrest.reflection.IControllerData#getAction(java.lang.String)
	 */
	@Override
	public IActionMethod getAction(String name) {
		for (IActionMethod action : this.actions) {
			if (action.getName().equals(name))
				return action;
		}

		return null;
	}

	private void setActions(Method[] methods) {
		for (Method method : methods) {
			if (isPublic(method)) {
				IActionMethod action = ReflectionFactory.newActionMethod(method);
				this.actions.add(action);
			}
		}
	}

	private static boolean isPublic(Method method) {
		return Modifier.isPublic(method.getModifiers());
	}

}
