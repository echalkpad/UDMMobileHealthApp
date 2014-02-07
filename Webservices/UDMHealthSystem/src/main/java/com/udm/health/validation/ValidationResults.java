package com.udm.health.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResults {

	private Class<?> validatedObjectClass;
	private List<String> validationErrors;
	
	public ValidationResults() {
		validationErrors = new ArrayList<String>();
	}
	
	public boolean hasValidationErrors() {
		return ! validationErrors.isEmpty();
	}
	
	public List<String> getValidationErrors() {
		return validationErrors;
	}
	
	public void addValidationError(String validationError) {
		validationErrors.add(validationError);
	}
	
	public Class<?> getValidatedObjectClass() {
		return validatedObjectClass;
	}
	
	public void setValidatedObjectClass(Class<?> validatedObjectClass) {
		this.validatedObjectClass = validatedObjectClass;
	}
}
