package com.og.jrest.api;

import com.og.jrest.http.Request;
import com.og.jrest.security.identity.User;

/**
 * This is the base class for all Controller classes implemented by the client. Any api controller implemented by the
 * client must extend this class.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class Controller {

	/**
	 * The request object that was received from the web client calling the api.
	 */
	protected Request request;

	/**
	 * The User that called the api
	 */
	protected User user;

}
