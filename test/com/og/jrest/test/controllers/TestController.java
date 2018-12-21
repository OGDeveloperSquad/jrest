package com.og.jrest.test.controllers;

import com.og.jrest.logging.Log;

public class TestController {
	public String variable = "empty";

	public void stuff() {
		Log.info("Empty parameter stuff method called");
	}

	public void stuff(String id) {

		Log.info("Success!!!!! Method was invoked with a variable value of " + variable);

	}

}
