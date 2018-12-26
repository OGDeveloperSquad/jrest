package com.og.jrest.http;

public class ErrorResponse extends HTTPResponse {

	public ErrorResponse(int statusCode) {
		super();
		this.responseCode = new HTTPResponseCode(statusCode);
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;
		if (body != null) {
			byte[] body = ((String) this.body).getBytes();
			this.concatenateBytes(headers, body);
		}
		return result;
	}

}
