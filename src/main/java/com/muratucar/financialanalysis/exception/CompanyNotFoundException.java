package com.muratucar.financialanalysis.exception;

public class CompanyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5920295046649906778L;

	
	public CompanyNotFoundException() {
         super("Company Not Found");
         
	}
    public CompanyNotFoundException(String message) {
    	
    	 super(message);
    }
}
