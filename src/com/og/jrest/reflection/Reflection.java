package com.og.jrest.reflection;

import java.util.ArrayList;
import java.util.List;

import com.og.jrest.routing.RouteUtility;

public class Reflection {

	protected static List<IControllerPackage> controllerPackages;

	static {
		Reflection.controllerPackages = new ArrayList<>();
	}

	private static void registerControllerPackage(String packageName)
			throws InstantiationException, IllegalAccessException {
		IControllerPackage controllerPackage = ReflectionFactory.newControllerPackage(packageName);
		Reflection.controllerPackages.add(controllerPackage);
	}

	public static void RegisterControllers() throws InstantiationException, IllegalAccessException {
		// TODO read package names from api.config
		// String[] packageNames = ConfigurationManager.getControllerPackageNames();

		String packageName = "com.og.jrest.test.controllers";
		Reflection.registerControllerPackage(packageName);
	}

	public static IControllerContext getControllerContext(String name) {
		for (IControllerPackage controllerPackage : Reflection.controllerPackages) {
			for (IControllerContext controller : controllerPackage.getAllControllerContexts()) {
				String controllerToFind = RouteUtility.getControllerName(name);
				if (controller.getName().equalsIgnoreCase(controllerToFind))
					return controller;
			}
		}

		return null;
	}

}
