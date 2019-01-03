package com.og.jrest.http;

/**
 * Enum to represent supported HTTP verbs.
 * 
 * @author matthew.shoemaker
 *
 */
public enum Verb {

	GET {
		@Override
		public String toString() {
			return "GET";
		}
	},

	POST {
		@Override
		public String toString() {
			return "POST";
		}
	},

	PUT {
		@Override
		public String toString() {
			return "PUT";
		}
	},

	DELETE {
		@Override
		public String toString() {
			return "DELETE";
		}
	},

	PATCH {
		@Override
		public String toString() {
			return "PATCH";
		}
	},

	OPTIONS {
		@Override
		public String toString() {
			return "GET";
		}
	}

}
