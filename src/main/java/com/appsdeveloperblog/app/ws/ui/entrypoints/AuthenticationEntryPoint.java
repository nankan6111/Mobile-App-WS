package com.appsdeveloperblog.app.ws.ui.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.appsdeveloperblog.app.ws.service.AuthenticationService;
import com.appsdeveloperblog.app.ws.service.impl.AuthenticationServiceImpl;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.request.LoginCredential;
import com.appsdeveloperblog.app.ws.ui.model.response.AuthenticationDetails;

@Path("/authentication")
public class AuthenticationEntryPoint {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public AuthenticationDetails uerLogin(LoginCredential loginCredential) {
		
		AuthenticationDetails returnValue = new AuthenticationDetails();
		
		AuthenticationService authenticationService = new AuthenticationServiceImpl();
		UserDTO authenticatedUser =  authenticationService.authenticate(loginCredential.getUserName(), loginCredential.getUserPassword());
		
		// Reset Access Token
		authenticationService.resetSecurityCridentials(loginCredential.getUserPassword(), authenticatedUser);
		
		String accessToken = authenticationService.issuseAccessToken(authenticatedUser);
		
		returnValue.setId(authenticatedUser.getUserId());
		returnValue.setToken(accessToken);
		
		return returnValue;
	}

}
