package com.graodireto.quemtemboca.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -1829551478484743897L;
	
	private String name;
	
	public CustomAuthenticationToken(Object principal, Object credentials, String name) {
		super(principal, credentials);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrincipal() {
		return (String) super.getPrincipal();
	}

}
