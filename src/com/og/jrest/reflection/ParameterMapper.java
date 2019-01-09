package com.og.jrest.reflection;

import com.og.jrest.routing.RouteParameter;

public class ParameterMapper {

	public static <T> Object mapToObjecct(RouteParameter routeParam, IActionParameter<T> param) {
		String paramType = param.getType().getName();
		String paramValue = routeParam.getValue();
		Object parameter = null;
		// I hate this. Must be a better way...
		switch (paramType) {
			case "int":
				parameter = Integer.parseInt(paramValue);
				break;

			case "double":
				parameter = Double.parseDouble(paramValue);
				break;

			case "long":
				parameter = Long.parseLong(paramValue);
				break;

			case "short":
				parameter = Short.parseShort(paramValue);
				break;

			case "float":
				parameter = Float.parseFloat(paramValue);
				break;

			case "boolean":
				if ("true".equalsIgnoreCase(paramValue))
					parameter = true;
				else if ("false".equalsIgnoreCase(paramValue))
					parameter = false;
				break;

			case "byte":
				parameter = Byte.parseByte(paramValue);
				break;

			case "char":
				parameter = paramValue.charAt(0);
				break;

			case "java.lang.String":
				parameter = routeParam.getValue();
				break;
		}

		return parameter;
	}

	public static Object[] mapToObjects(RouteParameter[] routeParams, IActionParameter<?>[] params) {
		Object[] parameters = new Object[routeParams.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = mapToObjecct(routeParams[i], params[i]);
			parameters[i] = parameter;
		}

		return parameters;
	}

}
