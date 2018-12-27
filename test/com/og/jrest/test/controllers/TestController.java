package com.og.jrest.test.controllers;

import com.og.jrest.api.Controller;
import com.og.jrest.http.response.BaseResponse;
import com.og.jrest.http.response.TextResponse;
import com.og.jrest.logging.Log;

public class TestController extends Controller {

	public BaseResponse testMethod(String id) {
		Log.info("Controller method 'testMethod' has been invoked");
		TextResponse response = new TextResponse();
		response.setBody(this.request.toString());

		return response;
	}

}
