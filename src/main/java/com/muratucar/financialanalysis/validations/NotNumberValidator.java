package com.muratucar.financialanalysis.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNumberValidator implements ConstraintValidator<NotNumber, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		  
		   try {
			   Double.parseDouble(value);
			   return false;
		} catch (Exception e) {
			 return true;
		}
	}
}
