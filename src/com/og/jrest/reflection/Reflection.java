package com.og.jrest.reflection;

import com.og.jrest.routing.RouteUtility;

public class Reflection {

	public static IControllerContext getControllerContext(String name) {
		for (IControllerPackage controllerPackage : ControllerTable.getPackages()) {
			for (IControllerContext controller : controllerPackage.getAllControllerContexts()) {
				String controllerToFind = RouteUtility.getControllerName(name);
				if (controller.getName().equalsIgnoreCase(controllerToFind))
					return controller;
			}
		}

		return null;
	}

}
