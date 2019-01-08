package com.og.jrest.routing;

import com.og.jrest.exceptions.InvalidRouteException;

/**
 * Implementation of a path segment.
 * 
 * @author Matthew.Shoemaker
 *
 */
class PathSegment implements IPathSegment {

	private static final String START_IDENTIFIER = "{";
	private static final String END_IDENTIFIER = "}";
	private static final String DEFAULT_IDENTIFIER = "=";
	private static final String OPTIONAL_IDENTIFIER = "?";
	private static final String CONTROLLER_IDENTIFIER = START_IDENTIFIER + "controller";
	private static final String ACTION_IDENTIFIER = START_IDENTIFIER + "action";

	private String text;

	public PathSegment(String segment) throws InvalidRouteException {
		this.text = segment;
		this.checkForViolations();
	}

	private void checkForViolations() throws InvalidRouteException {
		if (this.isControllerSegment() && this.isOptional())
			throw new InvalidRouteException("Controller cannot be marked as optional.");
		if (this.isActionSegment() && this.hasDefault() && this.isOptional())
			throw new InvalidRouteException("Action cannot be optional if default is specified.");
	}

	@Override
	public boolean isOptional() {
		boolean isOptional = this.text.endsWith(OPTIONAL_IDENTIFIER + END_IDENTIFIER);
		return isOptional;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean isControllerSegment() {
		boolean isControllerSegment = this.text.startsWith(CONTROLLER_IDENTIFIER) && this.text.endsWith(END_IDENTIFIER);
		return isControllerSegment;
	}

	@Override
	public boolean isActionSegment() {
		boolean isActionSegment = this.text.startsWith(ACTION_IDENTIFIER) && this.text.endsWith(END_IDENTIFIER);
		return isActionSegment;
	}

	@Override
	public boolean isParameterSegment() {
		boolean wrappedWithIdentifiers = this.text.startsWith(START_IDENTIFIER) && this.text.endsWith(END_IDENTIFIER);
		return wrappedWithIdentifiers && !this.isControllerSegment() && !this.isActionSegment();
	}

	@Override
	public boolean isStaticSegment() {
		boolean isStaticSegment = !this.text.startsWith(START_IDENTIFIER);
		return isStaticSegment;
	}

	@Override
	public boolean hasDefault() {
		boolean isAbleToBeDefaulted = this.isControllerSegment() && this.isActionSegment();
		if (isAbleToBeDefaulted)
			return this.text.startsWith(CONTROLLER_IDENTIFIER + DEFAULT_IDENTIFIER)
					|| this.text.startsWith(ACTION_IDENTIFIER + DEFAULT_IDENTIFIER);

		return false;
	}

	@Override
	public String getDefault() {
		int start = this.text.indexOf("=") + 1;
		int end = this.text.indexOf("}");
		String defaultText = this.text.substring(start, end);
		return defaultText;
	}

	@Override
	public String getParameterName() {
		int start = 1;
		int end = this.text.indexOf(END_IDENTIFIER);
		if (this.isOptional())
			end = this.text.indexOf(OPTIONAL_IDENTIFIER);
		String paramName = this.text.substring(start, end);

		return paramName;
	}

}
