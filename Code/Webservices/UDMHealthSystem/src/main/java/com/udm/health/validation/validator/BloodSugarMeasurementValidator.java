package com.udm.health.validation.validator;

import com.udm.health.domain.ws.BloodSugarMeasurementRequest;
import com.udm.health.validation.ValidationResults;

public class BloodSugarMeasurementValidator extends AbstractValidator<BloodSugarMeasurementRequest>{

	@Override
	public boolean shouldValidate(Object object) {
		return BloodSugarMeasurementRequest.class == object.getClass();
	}

	@Override
	public ValidationResults validate(BloodSugarMeasurementRequest object) {
		ValidationHelper validationHelper = new ValidationHelper();
		
		validationHelper.notBlank("email", object.getEmail());
		validationHelper.notBlank("measurementDate", object.getMeasurementDate());
		validationHelper.notBlank("measurementTime", object.getMeasurementTime());

		
		return validationHelper.getValidationResults();

	}

}
