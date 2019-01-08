package com.og.jrest.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.exceptions.ActionMethodInvocationException;
import com.og.jrest.exceptions.ActionMethodNotFoundException;
import com.og.jrest.exceptions.ParameterBindingException;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteParameter;

class ControllerContext implements IControllerContext {

	private String name;
	// class is a java keyword, so convention is to use clazz
	private Class<?> clazz;
	private List<IActionMethod> actions;

	public ControllerContext(Controller controller) {
		this.name = controller.getClass().getSimpleName();
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

	@Override
	public IActionMethod getAction(String name, String[] parameterNames) throws ActionMethodNotFoundException {
		for (IActionMethod action : this.actions) {
			String actionName = action.getName();
			if (actionName.equalsIgnoreCase(name) && this.parametersMatch(action, parameterNames)) {
				return action;
			}
		}

		throw new ActionMethodNotFoundException(name, this.name);
	}

	private boolean parametersMatch(IActionMethod action, String[] parameterNames) {
		IActionParameter<?>[] params = action.getParams();
		if (params.length != parameterNames.length)
			return false;

		for (int i = 0; i < params.length; i++) {
			IActionParameter<?> param = params[i];
			String name = parameterNames[i];
			if (!param.getName().equals(name))
				return false;
		}

		return true;
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

	@Override
	public IActionMethod getAction(String name) throws ActionMethodNotFoundException {
		return this.getAction(name, new String[0]);
	}

	@Override
	public IResponse invoke(Controller controller, String actionName, RouteParameter[] params)
			throws ActionMethodNotFoundException, ParameterBindingException, ActionMethodInvocationException {
		List<String> parameterNames = new ArrayList<>();
		for (RouteParameter param : params)
			parameterNames.add(param.getName());
		IActionMethod action = this.getAction(actionName,
				(String[]) parameterNames.toArray(new String[parameterNames.size()]));
		IResponse response = action.invoke(controller, params);

		return response;
	}

}
