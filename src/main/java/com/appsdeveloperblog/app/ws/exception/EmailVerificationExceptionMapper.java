package com.appsdeveloperblog.app.ws.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Provider
public class EmailVerificationExceptionMapper implements ExceptionMapper<EmailVerificationException> {

	@Override
	public Response toResponse(EmailVerificationException exception) {
		ErrorMessage errorMessage = new ErrorMessage(
				exception.getMessage(),
				ErrorMessages.EMAIL_ADDRESS_NOT_VERIFIED.name(),
				"http://appsdeveloperblog.com");
		
		return Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build();
	}

}
