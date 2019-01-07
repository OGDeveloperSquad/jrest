package com.og.jrest.routing;

import java.util.LinkedList;
import java.util.List;

import com.og.jrest.exceptions.InvalidRouteException;

public class RouteTemplate implements IRouteTemplate {

	private List<IPathSegment> pathSegments;
	private String defaultController;
	private String defaultAction;
	private String name;
	private int numberOfOptionalParams;

	public RouteTemplate(String name) {
		this.pathSegments = new LinkedList<>();
		this.defaultController = null;
		this.defaultAction = null;
		this.name = name;
	}

	@Override
	public void setDefaultController(String controller) {
		this.defaultController = controller;
	}

	@Override
	public void setDefaultAction(String action) {
		this.defaultAction = action;
	}

	@Override
	public IPathSegment[] getSegments() {
		return (IPathSegment[]) this.pathSegments.toArray();
	}

	@Override
	public String getDefaultController() {
		return this.defaultController;
	}

	@Override
	public String getDefaultAction() {
		return this.defaultAction;
	}

	@Override
	public void addSegment(IPathSegment segment) throws InvalidRouteException {
		this.extractDefaults(segment);
		if (segment.isOptional())
			this.numberOfOptionalParams++;
		this.pathSegments.add(segment);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	private void extractDefaults(IPathSegment segment) throws InvalidRouteException {
		this.extractDefaultController(segment);
		this.extractDefaultAction(segment);
	}

	private void extractDefaultController(IPathSegment segment) throws InvalidRouteException {
		if (segment.isControllerSegment()) {
			if (this.defaultController != null)
				throw new InvalidRouteException("Multiple controllers specified in route '" + this.name + "'");
			this.defaultController = segment.getDefault();
		}
	}

	private void extractDefaultAction(IPathSegment segment) throws InvalidRouteException {
		if (segment.isActionSegment()) {
			if (this.defaultAction != null)
				throw new InvalidRouteException("Multiple actions specified in route '" + name + "'");
			this.defaultAction = segment.getDefault();
		}
	}

	@Override
	public int optionalParamCount() {
		return this.numberOfOptionalParams;
	}

	@Override
	public String getControllerNameFromUri(String uri) {
		String name = null;
		String[] uriSegments = RouteUtility.splitIntoSegments(uri);
		for (int i = 0; i < this.pathSegments.size(); i++) {
			IPathSegment templateSegment = this.pathSegments.get(i);
			String uriSegment = uriSegments[i];
			if (templateSegment.isControllerSegment())
				name = uriSegment;
		}
		return null;
	}

	@Override
	public String getActionNameFromUri(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

}
