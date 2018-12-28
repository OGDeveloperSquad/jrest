package com.og.jrest.http;

/**
 * This class is a model for an HTTP header. It contains the key of the header
 * and all comma delimited values of the header.
 * 
 * @author matthew.shoemaker
 *
 */
public class Header {

	private String key;

	private String[] values;

	private static final String VALUE_SEPARATOR = ",";

	private static final String KEY_SEPARATOR = ":";

	/**
	 * Constructor to initialize this given a string containing the raw HTTP text
	 * declaring the header.
	 * 
	 * @param headerLine raw HTTP line declaring a header
	 */
	public Header(String headerLine) {
		int colonIndex = headerLine.indexOf(KEY_SEPARATOR);
		String key = headerLine.substring(0, colonIndex);
		String values = headerLine.substring(colonIndex + 1).trim();

		this.key = key;
		this.values = this.splitValues(values);
	}

	/**
	 * Constructor to initialize this header with the given key and values.
	 * 
	 * @param key    the key of this header
	 * @param values the values of this header
	 */
	public Header(String key, String[] values) {
		this.key = key;
		this.values = values;
	}

	/**
	 * Constructor to initialize this header with the given key and values.
	 * 
	 * @param key    the key of this header
	 * @param values the values of this header
	 */
	public Header(String key, String values) {
		this.key = key;
		this.values = this.splitValues(values);
	}

	/**
	 * Returns the key of this header.
	 * 
	 * @return key of this header.
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Returns the values of this header as an array of strings.
	 * 
	 * @return the values of this header as an array of strings.
	 */
	public String[] getValues() {
		return this.values;
	}

	/**
	 * Returns the values of this header as they would appear in a raw HTTP message.
	 * 
	 * @return the values of this header as they would appear in a raw HTTP message.
	 */
	public String getValuesJoined() {
		return String.join(VALUE_SEPARATOR, this.values);
	}

	@Override
	public String toString() {
		String key = this.getKey();
		String values = this.getValuesJoined();
		return String.format("%s%s %s", key, KEY_SEPARATOR, values);
	}

	/**
	 * Given a string of header values as they would appear in a raw HTTP message,
	 * will return an array of values as strings.
	 * 
	 * @param values string of values as they would appear in a raw HTTP message
	 * @return array of header values as strings
	 */
	private String[] splitValues(String values) {
		return values.split(VALUE_SEPARATOR);
	}

}
