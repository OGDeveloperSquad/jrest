package com.og.jrest.http;



public class TextResponse extends Response {
	
	private String body;

	public TextResponse(String body) {
		super();
		this.body = body;
	}



	@Override
	public byte[] getBytes() {
		// turn components of this into byte arrays
		
		byte[] headBytes = this.getReponseLineAndHeaders().getBytes();
		byte[] bodyBytes = this.body.getBytes();
		
		//create new byte array and copy
		byte[] bytes = new byte[headBytes.length + bodyBytes.length];
		
		for(int i = 0; i < headBytes.length; i++) {
			bytes[i] = headBytes[i];
		}
		
		for(int j = 0; j < bodyBytes.length; j++) {
			bytes[headBytes.length + j] = bodyBytes[j];
		}
		
		
		return bytes;
	}
	
	
	
}
