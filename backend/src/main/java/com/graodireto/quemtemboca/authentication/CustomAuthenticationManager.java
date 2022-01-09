package com.graodireto.quemtemboca.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.graodireto.quemtemboca.entity.User;
import com.graodireto.quemtemboca.service.UserService;

import io.jsonwebtoken.io.Encoders;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication != null) {
			String email = (String) authentication.getPrincipal();
			String password = (String) authentication.getCredentials();
			String encodedEmail = Encoders.BASE64.encode(email.getBytes());
			User user = userService.findByEmailAndPassword(encodedEmail, password);
			if (user == null) {				
				throw new BadCredentialsException("Usuário inexistente.");
			}
			authentication = new CustomAuthenticationToken(email, null, user.getName());
		}
		return authentication;
	}

}
