package com.og.jrest.reflection;

import com.og.jrest.exceptions.InvalidActionParameterException;
import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteParameter;

public class ParameterMapper {
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String LONG = "long";
	private static final String SHORT = "short";
	private static final String FLOAT = "float";
	private static final String BOOLEAN = "boolean";
	private static final String BYTE = "byte";
	private static final String CHAR = "char";
	private static final String STRING = "java.lang.String";

	public static <T> Object mapToObjecct(RouteParameter routeParam, IActionParameter<T> param)
			throws InvalidActionParameterException {
		String paramType = param.getType().getName();
		String paramValue = routeParam.getValue();
		Object parameter = null;
		// I hate this. Must be a better way...
		try {
			switch (paramType) {
				case INT:
					parameter = Integer.parseInt(paramValue);
					break;

				case DOUBLE:
					parameter = Double.parseDouble(paramValue);
					break;

				case LONG:
					parameter = Long.parseLong(paramValue);
					break;

				case SHORT:
					parameter = Short.parseShort(paramValue);
					break;

				case FLOAT:
					parameter = Float.parseFloat(paramValue);
					break;

				case BOOLEAN:
					if ("true".equalsIgnoreCase(paramValue))
						parameter = true;
					else if ("false".equalsIgnoreCase(paramValue))
						parameter = false;
					break;

				case BYTE:
					parameter = Byte.parseByte(paramValue);
					break;

				case CHAR:
					parameter = paramValue.charAt(0);
					break;

				case STRING:
					parameter = routeParam.getValue();
					break;
			}
		} catch (Exception exception) {
			InvalidActionParameterException ex = new InvalidActionParameterException("Unable to map the value '"
					+ routeParam.getValue() + "' to the type '" + param.getType().toString() + "'");
			Log.exception(ex);
			throw ex;
		}

		return parameter;
	}

	public static Object[] mapToObjects(RouteParameter[] routeParams, IActionParameter<?>[] params)
			throws InvalidActionParameterException {
		Object[] parameters = new Object[routeParams.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = mapToObjecct(routeParams[i], params[i]);
			parameters[i] = parameter;
		}

		return parameters;
	}

}
