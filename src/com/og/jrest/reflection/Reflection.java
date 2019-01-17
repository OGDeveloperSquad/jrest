package com.og.jrest.reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.og.jrest.reflection.controllers.ControllerTable;
import com.og.jrest.reflection.controllers.IControllerContext;
import com.og.jrest.reflection.controllers.IControllerPackage;
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

	public static <T> List<T> loadSubclassesInPackage(Class<T> clazz, String packageName) throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends T>> controllerClasses = reflections.getSubTypesOf(clazz);
		List<T> controllers = new ArrayList<>();
		for (Class<? extends T> controllerClass : controllerClasses) {
			T controller = controllerClass.newInstance();
			controllers.add(controller);
		}

		return controllers;
	}

}
