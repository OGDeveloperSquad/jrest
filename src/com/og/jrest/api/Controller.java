package com.og.jrest.api;

import com.og.jrest.http.HTTPRequest;

/**
 * This is the base class for all Controller classes implemented by the client.
 * Any api controller implemented by the client must extend this class.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class Controller {

	public HTTPRequest request;

	public String reflectionTest;

}
