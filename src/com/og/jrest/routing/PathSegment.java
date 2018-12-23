package com.og.jrest.routing;

/**
 * Implementation of a path segment.
 * 
 * @author Matthew.Shoemaker
 *
 */
class PathSegment implements IPathSegment {

	private String text;

	private boolean optional = false;

	public PathSegment(String segment) {
		this.text = segment;
		this.optional = false;
		if (this.text.endsWith("?}")) {
			this.optional = true;
		}
	}

	@Override
	public void setOptional(boolean isOptional) {
		this.optional = isOptional;
	}

	@Override
	public boolean isOptional() {
		return this.optional;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

}
