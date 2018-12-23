package com.og.jrest.routing;

import java.lang.reflect.Method;

import com.og.jrest.api.Controller;

public interface IRouteResult {

	/**
	 * Given the name of a controller, loads that controller via reflection and
	 * stores it in this class for use at runtime.
	 * 
	 * @param controllerName
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	void setController(String controllerName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException;

	/**
	 * Given the name of a method belonging to this.controller and matches the list
	 * of parameters of this, loads that method via reflection and stores it for
	 * runtime invocation.
	 * 
	 * @param actionName name of a method that belongs to this controller with a
	 *                   list of parameters matching this.params
	 * @throws NoSuchMethodException
	 * 
	 * @requires Controller MUST already be loaded and the parameter list MUST
	 *           already be complete before calling this method.
	 */
	void setAction(String actionName) throws NoSuchMethodException;

	/**
	 * Get the controller loaded via reflection.
	 * 
	 * @return the controller that was loaded via reflection
	 */
	Controller getController();

	/**
	 * Gets the action method for invocation.
	 * 
	 * @return the action method to be invoked
	 */
	Method getAction();

	/**
	 * Returns an array of the parameters to be fed to be action method.
	 * 
	 * @return parameters for this action method.
	 */
	Object[] getParameters();

	/**
	 * Given a parameter name and a parameter value, adds it to this list of
	 * parameters. The parameter collection is ordered, so parameters must be added
	 * in the order that they will appear in the action method.
	 * 
	 * @param name  name of the parameter
	 * @param value value of the parameter to be fed to the action method
	 */
	<T> void addParam(String name, T value);

}
