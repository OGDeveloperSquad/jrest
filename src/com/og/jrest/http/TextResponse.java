package com.og.jrest.http;

public class TextResponse extends HTTPResponse {

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] body = ((String) this.body).getBytes();

		return this.concatenateBytes(headers, body);
	}

	@Override
	public void setBody(Object body) {
		this.body = body;
	}

}
