package com.og.jrest.http.response;

public class EmptyResponse extends Response {

	public EmptyResponse() {
		super();
	}

	@Override
	public byte[] getBytes() {
		String response = this.getReponseLineAndHeaders() + System.lineSeparator();
		return response.getBytes();
	}

}
