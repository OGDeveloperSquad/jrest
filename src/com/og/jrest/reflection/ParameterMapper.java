package com.og.jrest.reflection;

import com.og.jrest.routing.RouteParameter;

public class ParameterMapper {

	public static <T> Object map(RouteParameter routeParam, IActionParameter<T> param) {
		String paramType = param.getType().getName();
		String paramValue = routeParam.getValue();
		Object parameter = null;
		switch (paramType) {
		case "int":
			parameter = Integer.parseInt(paramValue);
			break;
		case "java.lang.String":
			parameter = routeParam.getValue();
			break;
		}

		return parameter;
	}

	public static Object[] map(RouteParameter[] routeParams, IActionParameter<?>[] params) {
		Object[] parameters = new Object[routeParams.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = map(routeParams[i], params[i]);
			parameters[i] = parameter;
		}

		return parameters;
	}

}
