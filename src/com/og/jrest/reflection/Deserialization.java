package com.og.jrest.reflection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.og.jrest.exceptions.JRestServerException;
import com.og.jrest.exceptions.ParameterBindingException;
import com.og.jrest.logging.Log;

public class Deserialization {

	public static <T> T convertJsonToObject(Class<T> type, String json) throws JRestServerException {
		try {
			json = java.net.URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			Log.exception(e1);
		}
		ObjectMapper mapper = new ObjectMapper();
		T object;
		try {
			object = mapper.readValue(json, type);
		} catch (IOException e) {
			throw new ParameterBindingException(e.getMessage(), e);
		}

		return object;
	}

}
