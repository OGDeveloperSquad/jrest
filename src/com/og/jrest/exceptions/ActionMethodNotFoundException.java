package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ActionMethodNotFoundException extends JRestException {

	private static final String HEADER = "ActionMethodNotFoundException:   ";
	private static final int STATUS_CODE = 404;

	public ActionMethodNotFoundException(String controllerName, String actionName) {
		super(String.format("%SUnable to find action method '%s' on controller '%sController'", HEADER, actionName,
				controllerName));
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}

}
