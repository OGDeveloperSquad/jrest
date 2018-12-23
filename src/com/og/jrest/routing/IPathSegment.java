package com.og.jrest.routing;

public interface IPathSegment {

	boolean isOptional();

	String getText();

	void setText(String text);

}
