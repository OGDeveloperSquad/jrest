package com.og.jrest.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.og.jrest.api.Controller;

public class ControllerLoader {

	public static Controller loadController(String name) throws InstantiationException, IllegalAccessException {
		// Initialize to null so the result will be null if no class found
		Controller controller = null;
		// Name of the package containing all of the controllers
		String packageName = "com.og.jrest.test.controllers";
		// Open a reflection utility for the package containing the controllers
		Reflections reflections = new Reflections(packageName);

		// Get all classes in the package that extend the Controller class
		Set<Class<? extends Controller>> allClasses = reflections.getSubTypesOf(Controller.class);

		// Iterate over the controllers and look for one with a matching name to the one
		// requested
		Iterator<Class<? extends Controller>> iterator = allClasses.iterator();
		while (iterator.hasNext()) {
			Class<? extends Controller> clazz = iterator.next();
			String className = clazz.getName();
			// If find a match. instantiate it and return the instance
			if (className.equalsIgnoreCase(String.format("%s.%scontroller", packageName, name))) {
				controller = clazz.newInstance();
			}
		}

		return controller;
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
