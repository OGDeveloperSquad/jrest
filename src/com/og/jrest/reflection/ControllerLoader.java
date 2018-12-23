package com.og.jrest.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import com.og.jrest.api.Controller;

public class ControllerLoader {

	public static Controller loadController(String name)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> controller = Class.forName(name + "Controller");
		return (Controller) controller.newInstance();
	}

	public static Method loadAction(Controller controller, String actionName, List<ActionParameter> params)
			throws NoSuchMethodException {
		Method action = null;
		Method[] methods = controller.getClass().getDeclaredMethods();
		// Look at all the methods for the controller
		for (Method method : methods) {
			String methodName = method.getName();
			int paramCount = method.getParameterCount();
			// If the method names match and the number of parameters match, proceed
			if (methodName.equals(actionName) && paramCount == params.size()) {
				// If the current method has no parameters, then we've found our method
				if (paramCount < 1) {
					action = method;
				} else if (matchingParams(method, params)) {
					// the parameter list of this method matches, so we've found our method
					action = method;
				}
			}
		}

		// If no method was found, throw exception up the stack
		if (action == null)
			throw new NoSuchMethodException(
					String.format("Unable to load method '%s' in controller '%s' with parameters %s", actionName,
							controller.getClass().toString(), params.toArray().toString()));
		return action;
	}

	private static boolean matchingParams(Method method, List<ActionParameter> params) {
		boolean result = false;
		// Check to see if all parameters of the current method match the route
		Parameter[] methodParams = method.getParameters();
		for (int i = 0; i < method.getParameterCount(); i++) {
			Parameter methodParam = methodParams[i];
			// If the parameters dont match, return false
			String methodParamName = methodParam.getName();
			String routeParamName = params.get(i).getName();
			if (!methodParamName.equals(routeParamName)) {
				return false;
			} else {
				// If the parameter names do match, set the type of the parameter
				// params.get(i).setType(methodParam.getClass());
				// We've matched all the parameters successfully, so we've found our method
				if (i == method.getParameterCount() - 1) {
					result = true;
				}
			}
		}

		return result;
	}

}
