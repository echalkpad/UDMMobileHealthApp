package com.udm.health.validation.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.udm.health.validation.ErrorType;
import com.udm.health.validation.ValidationResults;

public class ValidationHelper {

	private ValidationResults results;
	
	public ValidationHelper() {
		this.results = new ValidationResults();
	}

	public void addError(String errorMessage) {
		results.addValidationError(errorMessage);
	}
	
	public boolean notNull(String objectName, String objectValue) {
		boolean notNull = objectValue != null;
		if ( ! notNull) {
			addError(objectName +" " + ErrorType.NULL.getErrorDescription());
		}
		return notNull;
	}

	public boolean notBlank(String objectName, String objectValue)  {
		boolean isBlank = StringUtils.isBlank(objectValue);
		if (isBlank) {
			addError(objectName +" " + ErrorType.BLANK.getErrorDescription());
		}
		return ! isBlank;
	}
	
	
	public boolean lengthEqualsTo(int length, String objectName, String objectValue) {
		boolean notBlank = notBlank(objectName, objectValue);
		if (notBlank) {
			boolean islengthDifferent = objectValue.length() != length;
			if (islengthDifferent) {
				addError(objectName +" " + ErrorType.SIZE.getErrorDescription());
			}
			return !islengthDifferent;
		}
		return notBlank;
	}
	
		
	public ValidationResults getValidationResults() {
		return results;
	}
	public boolean isNotNumber(String objectName, String objectValue){
		boolean isNumber = NumberUtils.isNumber(objectValue);
		if (!isNumber) {
			addError(objectName +" " + ErrorType.NOT_A_NUMBER.getErrorDescription());
		}
		return ! isNumber;
		
	}
	
}
