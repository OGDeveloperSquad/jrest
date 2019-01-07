package com.og.jrest.routing;

public class RouteComparator implements IRouteComparator {

	private String[] uriSegments;

	public RouteComparator(String requestedUri) {
		String[] segments = RouteUtility.splitIntoSegments(requestedUri);
		this.uriSegments = segments;
	}

	@Override
	public boolean matches(IRouteTemplate template) {
		IPathSegment[] templateSegments = template.getSegments();

		if (!this.hasViableParameterCount(template))
			return false;

		for (int i = 0; i < uriSegments.length; i++) {
			String uriSegment = this.uriSegments[i];
			IPathSegment templateSegment = templateSegments[i];
			if (!segmentsMatch(uriSegment, templateSegment))
				return false;
		}

		return true;
	}

	/**
	 * If the number of segments in the URI is less than or equal to the total
	 * length of the template, and has at least as many segments as are required by
	 * the template, then this template is viable. Just need to check that the
	 * static segments match.
	 * 
	 * @param template
	 * @return
	 */
	private boolean hasViableParameterCount(IRouteTemplate template) {
		int numberOfOptionalParams = template.optionalParamCount();
		int totalNumberOfTemplateParams = template.getSegments().length;
		int lengthOfUri = this.uriSegments.length;
		int lowerRangeOfParams = totalNumberOfTemplateParams - numberOfOptionalParams;

		boolean smallEnough = lengthOfUri <= totalNumberOfTemplateParams;
		boolean bigEnough = lengthOfUri >= lowerRangeOfParams;

		return smallEnough && bigEnough;
	}

	private boolean segmentsMatch(String uriSegment, IPathSegment templateSegment) {
		boolean result = true;
		if (templateSegment.isStaticSegment()) {
			result = uriSegment.equals(templateSegment.getText());
		}

		return result;
	}

}
