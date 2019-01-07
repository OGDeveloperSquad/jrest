package com.og.jrest.routing;

public class RouteParser {

	public static ParsedRoute parse(IRouteTemplate template, String uri) {
		ParsedRoute route = new ParsedRoute();

		String[] uriSegments = RouteUtility.splitIntoSegments(uri);
		IPathSegment[] templateSegments = template.getSegments();
		for (int i = 0; i < uriSegments.length; i++) {
			String uriSegment = uriSegments[i];
			IPathSegment templateSegment = templateSegments[i];
			if (templateSegment.isControllerSegment())
				route.setControllerName(uriSegment);
			else if (templateSegment.isActionSegment())
				route.setControllerName(uriSegment);
			else if (templateSegment.isParameterSegment())
				route.addParameter(templateSegment.getParameterName(), uriSegment);
		}

		if (route.getControllerName() == null)
			route.setControllerName(template.getDefaultController());
		if (route.getActionName() == null)
			route.setActionName(template.getDefaultAction());

		return null;
	}

}
