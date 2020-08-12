package com.appsdeveloperblog.app.ws.exception;

public class MissingRequiredFieldException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3609665373368180932L;
	
	public MissingRequiredFieldException(String message) {
		super(message);
	}

}
