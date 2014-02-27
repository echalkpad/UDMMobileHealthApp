package com.udm.health.validation.validator;

import com.udm.health.domain.ws.LoginRequest;
import com.udm.health.validation.ValidationResults;

public class LoginValidator extends AbstractValidator<LoginRequest>{

	@Override
	public boolean shouldValidate(Object object) {
		return LoginRequest.class == object.getClass();
	}

	@Override
	public ValidationResults validate(LoginRequest object) {
		ValidationHelper validationHelper = new ValidationHelper();
		
		validationHelper.notBlank("email", object.getEmail());
		validationHelper.notBlank("password", object.getPassword());
		
		return validationHelper.getValidationResults();
	}

}
