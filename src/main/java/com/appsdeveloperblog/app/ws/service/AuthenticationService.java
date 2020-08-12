package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.exception.AuthenticationException;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

public interface AuthenticationService {
	
	UserDTO authenticate(String userName, String password) throws AuthenticationException;
	String issuseAccessToken(UserDTO userProfile) throws AuthenticationException;
	void resetSecurityCridentials(String password, UserDTO userProfile);

}
