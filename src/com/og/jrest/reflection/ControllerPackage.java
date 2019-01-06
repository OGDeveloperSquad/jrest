package com.og.jrest.reflection;

import java.util.ArrayList;
import java.util.List;

import com.og.jrest.api.Controller;
import com.og.jrest.exceptions.ControllerNotFoundException;

class ControllerPackage implements IControllerPackage {

	private String packageName;

	private List<IControllerContext> controllerContexts;

	public ControllerPackage(String packageName) throws InstantiationException, IllegalAccessException {
		this.packageName = packageName;
		this.controllerContexts = new ArrayList<>();
		Controller[] controllers = ControllerLoader.loadControllersInPackage(packageName);
		this.setControllerData(controllers);
	}

	private void setControllerData(Controller[] controllers) {
		for (Controller controller : controllers) {
			IControllerContext data = ReflectionFactory.newControllerData(controller);
			this.controllerContexts.add(data);
		}
	}

	@Override
	public String getPackageName() {
		return this.packageName;
	}

	@Override
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public ControllerContext[] getAllControllerContexts() {
		return (ControllerContext[]) this.controllerContexts.toArray();
	}

	@Override
	public boolean hasController(String name) {
		for (IControllerContext controller : this.controllerContexts) {
			String controllerName = controller.getClass().getName();
			if (controllerName.equals(name))
				return true;
		}

		return false;
	}

	@Override
	public IControllerContext getControllerContext(String name) throws ControllerNotFoundException {
		for (IControllerContext controller : this.controllerContexts) {
			String controllerName = controller.getClass().getName();
			if (controllerName.equals(name))
				return controller;
		}

		throw new ControllerNotFoundException(name);
	}

}
