package com.appsdeveloperblog.app.ws.ui.model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationDetails {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private String id;
	private String token;
	
	
	  
}
