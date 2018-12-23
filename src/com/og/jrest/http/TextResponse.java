package com.og.jrest.http;

public class TextResponse extends HTTPResponse {

	public TextResponse() {
		super();
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] body = ((String) this.body).getBytes();

		return this.concatenateBytes(headers, body);
	}

}
