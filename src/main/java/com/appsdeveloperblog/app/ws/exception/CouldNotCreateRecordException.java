package com.appsdeveloperblog.app.ws.exception;

public class CouldNotCreateRecordException extends RuntimeException{

	private static final long serialVersionUID = -5870778022120442575L;

	public CouldNotCreateRecordException(String message)
    {
        super(message);
    }
}
