package com.og.jrest.http;

/**
 * Enum to represent supported HTTP Versions.
 * 
 * @author matthew.shoemaker
 *
 */
public enum Version {

	HTTP0_9 {
		@Override
		public String toString() {
			return "HTTP/0.9";
		}
	},

	HTTP1_1 {
		@Override
		public String toString() {
			return "HTTP/1.1";
		}
	},

	HTTP1_0 {
		@Override
		public String toString() {
			return "HTTP/1.0";
		}
	},

	HTTP2_0 {
		@Override
		public String toString() {
			return "HTTP/2.0";
		}
	};

	/**
	 * Return the corresponding enum based on the string value of an HTTP Version.
	 * For example, the string "HTTP/1.1" will return the HTTP11 enum.
	 * 
	 * @param version
	 *            HTTP Version as it appears in the request
	 * @return enum corresponding to the version
	 */
	public static Version fromString(String version) {
		Version enumVersion = null;
		// Eclipse let me indent the cases -___-
		switch (version) {
		case "HTTP/0.9":
			enumVersion = HTTP0_9;
			break;
		case "HTTP/1.0":
			enumVersion = HTTP1_0;
			break;
		case "HTTP/1.1":
			enumVersion = HTTP1_1;
			break;
		case "HTTP/2.0":
			enumVersion = HTTP2_0;
			break;
		}

		return enumVersion;
	}

}
