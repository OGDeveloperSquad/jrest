package com.og.jrest.reflection.models;

import java.util.List;

import com.og.jrest.api.IModel;
import com.og.jrest.exceptions.ModelNotFoundException;

public class ModelPackage {

	public List<ModelContext> modelContexts;

	public ModelPackage(String packageName) throws ModelNotFoundException {
		List<IModel> models = ModelLoader.getModelsInPackage(packageName);
		for (IModel model : models) {
			ModelContext context = new ModelContext(model);
			modelContexts.add(context);
		}
	}

	public List<ModelContext> getModels() {
		return this.modelContexts;
	}

	public boolean hasModel(String fullyQualifiedName) {
		for (ModelContext model : this.modelContexts) {
			if (model.getClass().getName().equalsIgnoreCase(fullyQualifiedName))
				return true;
		}

		return false;
	}

	public ModelContext getModel(String fullyQualifiedName) throws ModelNotFoundException {
		for (ModelContext model : this.modelContexts) {
			if (model.getClass().getName().equalsIgnoreCase(fullyQualifiedName))
				return model;
		}

		throw new ModelNotFoundException(fullyQualifiedName);
	}

}
