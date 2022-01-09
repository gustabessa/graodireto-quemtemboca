package com.graodireto.quemtemboca.authentication;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.graodireto.quemtemboca.config.JWTProperties;
import com.graodireto.quemtemboca.entity.User;
import com.graodireto.quemtemboca.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenAuthenticationService {

	@Autowired
	private UserService userService;
	
	private static JWTProperties jwtProperties;
	
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
    public TokenAuthenticationService(@Qualifier("JWTProperties") JWTProperties jwtProperties) {
		TokenAuthenticationService.jwtProperties = jwtProperties;
    }
	
	static void addAuthentication(HttpServletResponse response, CustomAuthenticationToken authentication) throws IOException {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
		String JWT = Jwts.builder().setSubject(authentication.getPrincipal())
				.signWith(key, SignatureAlgorithm.HS512).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		response.getWriter().write(authentication.getName());
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
			String email = Jwts.parserBuilder()
				    .setSigningKey(key)
				    .build()
				    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				    .getBody()
					.getSubject();
			if (email != null) {
				User user = userService.findByEmail(email);
				if (user != null) {
					return new CustomAuthenticationToken(email, null, user.getName());
				}
			}
		}
		return null;
	}

}
