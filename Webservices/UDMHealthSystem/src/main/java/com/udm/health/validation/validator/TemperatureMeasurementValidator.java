package com.udm.health.validation.validator;

import com.udm.health.domain.ws.TemperatureMeasurementRequest;
import com.udm.health.validation.ValidationResults;

public class TemperatureMeasurementValidator extends AbstractValidator<TemperatureMeasurementRequest>{

	@Override
	public boolean shouldValidate(Object object) {
		return TemperatureMeasurementRequest.class == object.getClass();
	}

	@Override
	public ValidationResults validate(TemperatureMeasurementRequest object) {
		ValidationHelper validationHelper = new ValidationHelper();
		
		validationHelper.notBlank("email", object.getEmail());
		return validationHelper.getValidationResults();
	}

}
