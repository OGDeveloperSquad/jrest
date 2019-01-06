package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ControllerNotFoundException extends JRestException {

	public ControllerNotFoundException(String controllerName) {
		super(String.format("Unable to find controller with name '%sController'", controllerName));

		this.setStatusCode(404);
	}

}
