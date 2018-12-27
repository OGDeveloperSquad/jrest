package com.og.jrest.http.response;

public class TextResponse extends BaseResponse {

	public TextResponse() {
		super();
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;
		// If there is a body, concatenate to the headers
		if (this.body != null) {
			byte[] body = ((String) this.body).getBytes();
			result = this.concatenateBytes(headers, body);
		}

		return result;
	}

}
