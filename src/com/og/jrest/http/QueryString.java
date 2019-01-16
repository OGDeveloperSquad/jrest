package com.og.jrest.http;

import java.util.ArrayList;
import java.util.List;

public class QueryString {

	private List<QueryParameter> parameters;

	private static final String PARAMETER_SEPARATOR = "&";

	public QueryString() {
		this.initializeParameters();
	}

	public QueryString(String rawQueryString) {
		String[] parameters = rawQueryString.split(PARAMETER_SEPARATOR);
		this.initializeParameters(parameters);
	}

	private void initializeParameters(String... parameters) {
		this.parameters = new ArrayList<>();
		for (String parameter : parameters)
			this.parameters.add(new QueryParameter(parameter));
	}

	public boolean hasParameter(String key) {
		for (QueryParameter parameter : this.parameters) {
			if (parameter.getKey().equals(key))
				return true;
		}

		return false;
	}

	public QueryParameter getParameter(String key) {
		for (QueryParameter parameter : this.parameters) {
			if (key.equals(parameter.getKey()))
				return parameter;
		}

		throw null;
	}

}
