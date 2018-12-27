package com.og.jrest.api;

import com.og.jrest.http.Request;

/**
 * This is the base class for all Controller classes implemented by the client.
 * Any api controller implemented by the client must extend this class.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class Controller {

	/**
	 * The request object that was received from the web client calling the api.
	 */
	public Request request;

}
