package com.nexbis.fwcms.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nexbis.fwcms.domain.common.AbstractDomain;
import com.nexbis.fwcms.domain.common.BaseDomain;

public class User extends AbstractDomain implements BaseDomain,UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2067296792771324323L;
	
	private String username;
	private String password;
	private String applicationId;
	
	public User(){
		
	}
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
}
