package com.og.jrest.test.controllers;

import com.og.jrest.api.Controller;
import com.og.jrest.http.HTTPResponse;
import com.og.jrest.http.TextResponse;
import com.og.jrest.logging.Log;

public class TestController extends Controller {

	public HTTPResponse testMethod(String id) {
		Log.info("Controller method 'testMethod' has been invoked");
		TextResponse response = new TextResponse();
		response.setBody(this.request.toString());

		return response;
	}

}
