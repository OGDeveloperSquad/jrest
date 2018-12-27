package com.og.jrest.http;

/**
 * Enum to represent supported HTTP Versions.
 * 
 * @author matthew.shoemaker
 *
 */
public enum Version {

	HTTP11 {
		@Override
		public String toString() {
			return "HTTP/1.1";
		}
	},

	HTTP10 {
		@Override
		public String toString() {
			return "HTTP/1.0";
		}
	},

	HTTP20 {
		@Override
		public String toString() {
			return "HTTP/2.0";
		}
	};

	/**
	 * Return the corresponding enum based on the string value of an HTTP Version.
	 * For example, the string "HTTP/1.1" will return the HTTP11 enum.
	 * 
	 * @param version HTTP Version as it appears in the request
	 * @return enum corresponding to the version
	 */
	public static Version fromString(String version) {
		Version enumVersion = null;
		// Why wont eclipse let me indent the cases -___-
		switch (version) {
		case "HTTP/1.1":
			enumVersion = HTTP11;
			break;
		case "HTTP/1.0":
			enumVersion = HTTP10;
			break;
		case "HTTP/2.0":
			enumVersion = HTTP20;
		}

		return enumVersion;
	}

}
