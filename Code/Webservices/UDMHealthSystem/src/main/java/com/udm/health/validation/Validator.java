package com.udm.health.validation;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.udm.health.validation.validator.AbstractValidator;

public class Validator {

	private List<AbstractValidator<?>> validators;
	
	public ValidationResults validate(Object object) {
		Validate.notNull(object, "Cannot validate null object.");
		for (AbstractValidator validator : validators) {
			if (validator.shouldValidate(object)) {
				ValidationResults results = validator.validate(object);
				results.setValidatedObjectClass(object.getClass());
				return results;
			}
		}
		throw new IllegalArgumentException(String.format("There is no validator registered for %s.", object));
	}
	
	public void setValidators(List<AbstractValidator<?>> validators) {
		for (AbstractValidator<?> validator : validators) {
			validator.setValidator(this);
		}
		this.validators = validators;
	}
	
}
