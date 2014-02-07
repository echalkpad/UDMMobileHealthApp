package com.udm.health.validation.validator;

import com.udm.health.validation.ValidationResults;
import com.udm.health.validation.Validator;

public abstract class AbstractValidator<T> {

	public abstract boolean shouldValidate(Object object);
	public abstract ValidationResults validate(T object);
	
	private Validator validator;

		
	// Populated by Validator
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
