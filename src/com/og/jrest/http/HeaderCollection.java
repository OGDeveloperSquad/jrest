package com.og.jrest.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderCollection implements Iterable<Header> {

	private List<Header> headers;

	public HeaderCollection() {
		this.createNewRep();
	}

	private void createNewRep() {
		this.headers = new ArrayList<>();
	}

	public Header getHeader(String key) {
		for (Header header : this.headers) {
			boolean isMatch = key.equalsIgnoreCase(header.getKey());
			if (isMatch) {
				return header;
			}
		}

		return null;
	}

	public boolean contains(String key) {
		return this.getHeader(key) != null;
	}

	public boolean remove(String key) {
		Header header = this.getHeader(key);
		return this.headers.remove(header);
	}

	public void add(Header header) {
		this.headers.remove(header);
		this.headers.add(header);
	}

	@Override
	public Iterator<Header> iterator() {
		return this.headers.iterator();
	}

}
