package com.udm.health.validation.validator;

import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.validation.ValidationResults;

public class CreateUserRequestValidator extends AbstractValidator<CreateUserRequest>{

	@Override
	public boolean shouldValidate(Object object) {
		return CreateUserRequest.class == object.getClass();
	}

	@Override
	public ValidationResults validate(CreateUserRequest object) {
		ValidationHelper validationHelper = new ValidationHelper();
		
		validationHelper.notNull("email", object.getEmail());
		
		return validationHelper.getValidationResults();
	}

}
