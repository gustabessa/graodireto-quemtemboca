package com.graodireto.quemtemboca.authentication;

import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.graodireto.quemtemboca.config.JWTProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenAuthenticationService {

	private static JWTProperties jwtProperties;
	
	static final long EXPIRATION_TIME = 86_400_000;
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
    public TokenAuthenticationService(@Qualifier("JWTProperties") JWTProperties jwtProperties) {
		TokenAuthenticationService.jwtProperties = jwtProperties;
    }
	
	static void addAuthentication(HttpServletResponse response, String username) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
			String username = Jwts.parserBuilder()
				    .setSigningKey(key)
				    .build()
				    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				    .getBody()
					.getSubject();
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
			}
		}
		return null;
	}

}
