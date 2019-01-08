package com.og.jrest.reflection;

import java.lang.reflect.Method;

import com.og.jrest.api.Controller;

public class ReflectionFactory {

	public static IActionMethod newActionMethod(Method method) {
		return new ActionMethod(method);
	}

	public static <T> IActionParameter<T> newActionParameter(String name, Class<T> type) {
		return new ActionParameter<T>(name, type);
	}

	public static IControllerContext newControllerData(Controller controller) {
		return new ControllerContext(controller);
	}

	public static IControllerPackage newControllerPackage(String packageName)
			throws InstantiationException, IllegalAccessException {
		return new ControllerPackage(packageName);
	}

}
