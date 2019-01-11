package com.og.jrest.reflection.models;

import java.util.List;

import com.og.jrest.api.IModel;
import com.og.jrest.exceptions.ModelNotFoundException;
import com.og.jrest.reflection.Reflection;

public class ModelLoader {

	public static List<IModel> getModelsInPackage(String packageName) throws ModelNotFoundException {
		try {
			List<IModel> models = Reflection.loadSubclassesInPackage(IModel.class, packageName);
			return models;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO come up with a better exception
			throw new ModelNotFoundException("Something went wrong", e);
		}
	}

}
