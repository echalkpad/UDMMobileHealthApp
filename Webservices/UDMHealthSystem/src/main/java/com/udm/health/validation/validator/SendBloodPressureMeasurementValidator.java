package com.udm.health.validation.validator;

import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;
import com.udm.health.validation.ValidationResults;

public class SendBloodPressureMeasurementValidator extends AbstractValidator<SendBloodPressureMeasurementRequest>{

	@Override
	public boolean shouldValidate(Object object) {
		return SendBloodPressureMeasurementRequest.class == object.getClass();
	}

	@Override
	public ValidationResults validate(SendBloodPressureMeasurementRequest object) {
		ValidationHelper validationHelper = new ValidationHelper();
		
		validationHelper.notBlank("email", object.getEmail());
		validationHelper.notBlank("measurementDate", object.getMeasurementDate());
		validationHelper.notBlank("measurementTime", object.getMeasurementTime());
		validationHelper.notBlank("systolic", object.getSystolic());
		validationHelper.notBlank("diastolic", object.getDiastolic());
		
		return validationHelper.getValidationResults();
	}

}
