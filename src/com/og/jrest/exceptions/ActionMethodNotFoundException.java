package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ActionMethodNotFoundException extends JRestException {

	public ActionMethodNotFoundException(String controllerName, String actionName) {
		super(String.format("Unable to find action method '%s' on controller '%sController'", actionName,
				controllerName));

		this.setStatusCode(404);
	}

}
