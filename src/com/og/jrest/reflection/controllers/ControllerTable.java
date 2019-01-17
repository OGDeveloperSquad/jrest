package com.og.jrest.reflection.controllers;

import java.util.ArrayList;
import java.util.List;

import com.og.jrest.exceptions.ControllerPackageNotFoundException;
import com.og.jrest.reflection.ReflectionFactory;

public class ControllerTable {

	private static List<IControllerPackage> controllerPackages;

	static {
		ControllerTable.controllerPackages = new ArrayList<>();
	}

	private ControllerTable() {
		// prevent instantiation
	};

	public static void registerControllers() throws InstantiationException, IllegalAccessException {
		// TODO read package names from api.config
		// String[] packageNames = ConfigurationManager.getControllerPackageNames();

		String packageName = "com.og.jrest.test.controllers";
		ControllerTable.registerControllerPackage(packageName);
	}

	public static List<IControllerPackage> getPackages() {
		return ControllerTable.controllerPackages;
	}

	public static IControllerPackage getPackage(String packageName) throws ControllerPackageNotFoundException {
		for (IControllerPackage controllerPackage : ControllerTable.controllerPackages) {
			if (controllerPackage.getPackageName().equalsIgnoreCase(packageName))
				return controllerPackage;
		}

		throw new ControllerPackageNotFoundException("Unable to find package '" + packageName + "'");
	}

	public static boolean hasPackage(String packageName) {
		for (IControllerPackage controllerPackage : ControllerTable.controllerPackages) {
			if (controllerPackage.getPackageName().equalsIgnoreCase(packageName))
				return true;
		}

		return false;
	}

	private static void registerControllerPackage(String packageName) throws InstantiationException, IllegalAccessException {
		IControllerPackage controllerPackage = ReflectionFactory.newControllerPackage(packageName);
		ControllerTable.controllerPackages.add(controllerPackage);
	}

}
