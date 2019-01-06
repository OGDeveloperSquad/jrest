package com.og.jrest.routing;

import com.og.jrest.exceptions.InvalidRouteException;

/**
 * Class to hold the data for a specified route template.
 * 
 * @author Matthew.Shoemaker
 *
 */
public interface IRouteTemplate {

	/**
	 * Sets the name of this template.
	 * 
	 * @param name
	 *            name of this template
	 */
	public void setName(String name);

	/**
	 * Returns the name of this template.
	 * 
	 * @return the name of this template
	 */
	public String getName();

	/**
	 * Adds a new segment to this template. Segments are ordered, so it is inserted
	 * after the last segment, or as the first if there are no segments in this.
	 * 
	 * @param segment
	 *            path segment
	 * @throws InvalidRouteException
	 */
	public void addSegment(IPathSegment segment) throws InvalidRouteException;

	/**
	 * Specifies the controller that should be invoked by default if no controller
	 * can be found.
	 * 
	 * @param controller
	 *            name of the controller to be invoked as a default
	 */
	public void setDefaultController(String controller);

	/**
	 * Specifies the default action to invoke.
	 * 
	 * @param action
	 *            default action name
	 */
	public void setDefaultAction(String action);

	/**
	 * Returns an array of strings with the raw text of each segment, in the order
	 * that they appear in the template.
	 * 
	 * @return
	 */
	public IPathSegment[] getSegments();

	/**
	 * Returns the name of the default controller for this template.
	 * 
	 * @return name of the default controller
	 */
	public String getDefaultController();

	/**
	 * Returns the name of the default action for this template.
	 * 
	 * @return name of the default action
	 */
	public String getDefaultAction();

	public int optionalParamCount();

}
