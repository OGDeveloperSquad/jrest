package com.og.jrest.reflection.models;

import java.lang.reflect.Field;

import com.og.jrest.api.IModel;
import com.og.jrest.exceptions.ParameterBindingException;

public class ModelField {

	private Field field;
	private IModel model;

	public ModelField(IModel model, Field field) {
		this.field = field;
		this.model = model;
	}

	public String getName() {
		return field.getName();
	}

	public Class<?> getType() {
		return field.getType();
	}

	public void setValue(Object value) throws ParameterBindingException {
		try {
			this.field.set(model, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ParameterBindingException("Unable to set value of parameter '" + this.getName()
					+ "' in the Model '" + this.model.getClass().getName() + "'", e);
		}
	}

	public Object getValue() throws ParameterBindingException {
		try {
			return this.field.get(model);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ParameterBindingException("Unable to read value of parameter '" + this.getName()
					+ "' in the Model '" + this.model.getClass().getName() + "'", e);
		}
	}

}
