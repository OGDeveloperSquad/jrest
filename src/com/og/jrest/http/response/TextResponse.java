package com.og.jrest.http.response;

import com.og.jrest.http.Header;

public class TextResponse extends Response {

	public TextResponse() {
		super();
	}

	public TextResponse(String text) {
		super();

		this.setBody(text);

		Header contentType = new Header(CONTENT_TYPE_KEY, "text/plain");
		this.addHeader(contentType);
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;

		if (this.hasBody()) {
			byte[] body = ((String) this.getBody()).getBytes();
			result = this.concatenateBytes(headers, body);
		}

		return result;
	}

}
