package com.og.jrest.test.controllers;

import com.og.jrest.annotation.Delete;
import com.og.jrest.annotation.Get;
import com.og.jrest.annotation.Post;
import com.og.jrest.annotation.Put;
import com.og.jrest.api.Controller;
import com.og.jrest.http.response.BaseResponse;
import com.og.jrest.http.response.TextResponse;
import com.og.jrest.logging.Log;

public class TestController extends Controller {

	private BaseResponse getResponse(String calledMethod, String verb, Object... params) {
		Log.info("Controller action '" + calledMethod + "' has been invoked via '" + verb
				+ "' request with parameters:");
		for (Object param : params)
			Log.info("\t" + param.getClass().getSimpleName() + ":    '" + param.toString() + "'");
		Log.info("\n");
		TextResponse response = new TextResponse();
		response.setBody(this.request.toString());

		return response;
	}

	@Get
	public BaseResponse actionTestGet(int id) {
		return getResponse("actionTestGet", "GET", id);
	}

	@Post
	public BaseResponse actionTestPost(String id) {
		return getResponse("actionTestPost", "POST", id);
	}

	@Get
	public BaseResponse get() {
		return getResponse("get", "GET");
	}

	@Post
	public BaseResponse post() {
		return getResponse("post", "POST");
	}

	@Put
	public BaseResponse put() {
		return getResponse("put", "PUT");
	}

	@Delete
	public BaseResponse delete() {
		return getResponse("delete", "DELETE");
	}

	@Get("annotationTest")
	public BaseResponse get(String id) {
		return getResponse("get", "GET", id);
	}

	@Post("annotationTest")
	public BaseResponse post(String id) {
		return getResponse("post", "POST", id);
	}

	@Put("annotationTest")
	public BaseResponse put(String id) {
		return getResponse("put", "PUT", id);
	}

	@Delete("annotationTest")
	public BaseResponse delete(String id) {
		return getResponse("delete", "DELETE", id);
	}

}
