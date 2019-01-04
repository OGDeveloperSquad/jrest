package com.og.jrest.http.response;

import com.og.jrest.http.Header;

/**
 * An abstraction of HTTPResponse and its required functionality.
 * 
 * @author Matthew.Shoemaker
 *
 */
public interface IResponse {

	/**
	 * Convert this Response into an array of bytes representing the HTTP response message that will be sent back to the
	 * web client.
	 * 
	 * @return the array of bytes that will be streamed back to the web client as a fully formed HTTP response.
	 */
	byte[] getBytes();

	/**
	 * Set the response code of this Response to the given responseCode.
	 * 
	 * @param responseCode new response code of this Response.
	 */
	void setStatusCode(int responseCode);

	/**
	 * Reports whether this Response contains a header with the given key.
	 * 
	 * @param key The key of the header under search.
	 * @return True if this Response containsa header with the given key.
	 */
	boolean hasHeader(String key);

	/**
	 * Given a key, will return the Header for this Response with the given key. If no key is found, null is returned.
	 * 
	 * @param key the key of the Header under search.
	 * @return The Header with the given key. Null is returned if no Header is found.
	 */
	Header getHeader(String key);

	/**
	 * Given a key, will delete the Header in this Response with a matching key, and return the header. If no key is
	 * found, null is returned.
	 * 
	 * @param key the key of the Header under search.
	 * @return The Header with the given key. Null is returned if no Header is found.
	 */
	Header deleteHeader(String key);

	/**
	 * Add the given header to this Response. If a header already exists with the same key, it is overwritten
	 * completely.
	 * 
	 * @param header the Header to be added to this.
	 */
	void addHeader(Header header);

	/**
	 * Given a key and the values of an HTTP header, will add a new Header to this Response with the given key and
	 * values. If a Header with the given key already exists, then it is overwritten completely.
	 * 
	 * @param key    the key of the header to be added.
	 * @param values the values of the header to be added.
	 */
	void addHeader(String key, String[] values);

	/**
	 * Given a key of an HTTP header and an array of values, will append the given values to the existing Header with
	 * the given key. If the header does not already exist, it will be created.
	 * 
	 * @param key    the key of the header to be added.
	 * @param values the values of the header to be added.
	 */
	void appendHeader(String key, String[] values);

	/**
	 * Returns the body of this Response.
	 * 
	 * @return the body of this Response.
	 */
	Object getBody();

	/**
	 * Reports whether this Response has a body.
	 * 
	 * @return True if this response has a body. False Otherwise;
	 */
	boolean hasBody();

}