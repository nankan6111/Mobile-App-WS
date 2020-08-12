package com.appsdeveloperblog.app.ws.exception;

public class CouldNotDeleteRecordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3643498677287147977L;

	public CouldNotDeleteRecordException(String message)
    {
        super(message);
    }
}

