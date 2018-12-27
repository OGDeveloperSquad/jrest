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
	},

	COPY {
		@Override
		public String toString() {
			return "COPY";
		}
	},

	LINK {
		@Override
		public String toString() {
			return "LINK";
		}
	},

	UNLINK {
		@Override
		public String toString() {
			return "INLINK";
		}
	},

	PURGE {
		@Override
		public String toString() {
			return "PURGE";
		}
	},

	LOCK {
		@Override
		public String toString() {
			return "LOCK";
		}
	},

	UNLOCK {
		@Override
		public String toString() {
			return "UNLOCK";
		}
	},

	PROPFIND {
		@Override
		public String toString() {
			return "PROPFIND";
		}
	},

	VIEW {
		@Override
		public String toString() {
			return "VIEW";
		}
	}

}
