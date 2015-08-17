package com.nexbis.fwcms.domain;

import java.sql.Timestamp;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class SessionUser extends AbstractDomain implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2040955408012465994L;
	
	private String username;
	private String token;
	private Timestamp authenticationDateTime;
	
	public SessionUser(){
		
	}
	
	public SessionUser(String username, String token, Timestamp authenticationDateTime){
		this.username = username;
		this.token = token;
		this.authenticationDateTime = authenticationDateTime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getAuthenticationDateTime() {
		return authenticationDateTime;
	}
	public void setAuthenticationDateTime(Timestamp authenticationDateTime) {
		this.authenticationDateTime = authenticationDateTime;
	}
	
	

}
