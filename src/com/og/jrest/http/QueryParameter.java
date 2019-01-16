package com.og.jrest.http;

public class QueryParameter {

	private String key;
	private String value;

	public QueryParameter(String raw) {
		this.parseRawParameter(raw);
	}

	public QueryParameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	private void parseRawParameter(String raw) {
		String[] keyValuePair = raw.split("=");
		this.key = keyValuePair[0];
		this.value = keyValuePair[1];
	}

}
