package com.og.jrest.http;

public class HTTPHeader {

	private String key;

	private String[] values;

	public HTTPHeader(String headerLine) {
		int colonIndex = headerLine.indexOf(":");
		String key = headerLine.substring(0, colonIndex);
		String vals = headerLine.substring(colonIndex + 1);

		this.key = key;
		this.values = vals.split(",");
	}

	public HTTPHeader(String key, String[] values) {
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return this.key;
	}

	public String[] getValues() {
		return this.values;
	}

	public String getValuesJoined() {
		return String.join(",", this.values);
	}

	@Override
	public String toString() {
		String key = this.getKey();
		String values = this.getValuesJoined();
		return String.format("%s: %s", key, values);
	}

}
