package com.og.jrest.routing;

public interface IRouteTemplate {

	public void addSegment(IPathSegment segment);

	public void setDefaultController(String controller);

	public void setDefaultAction(String action);

	public String[] getSegments();

	public String getDefaultController();

	public String getDefaultAction();

}
