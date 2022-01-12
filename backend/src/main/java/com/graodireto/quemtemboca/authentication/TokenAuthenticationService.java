package com.graodireto.quemtemboca.authentication;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.graodireto.quemtemboca.config.JWTProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class TokenAuthenticationService {

	private static JWTProperties jwtProperties;
	
	// 1 dia de validade
	static final long EXPIRATION_TIME = 86_400_000;
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
    public TokenAuthenticationService(@Qualifier("JWTProperties") JWTProperties jwtProperties) {
		TokenAuthenticationService.jwtProperties = jwtProperties;
    }
	
	static void addAuthentication(HttpServletResponse response, CustomAuthenticationToken authentication) throws IOException {
		String JWT = buildJwt(authentication.getName(), authentication.getPrincipal());
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		response.getWriter().write(authentication.getName());
	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			Claims claims = null;
			try {
				claims = parseJwt(token);
			} catch (ExpiredJwtException ex) {
				claims = ex.getClaims();
				if (claims != null && claims.getSubject() != null) {
					String JWT = buildJwt((String) claims.get("name"), (String) claims.getSubject());
					response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
				}
			} finally {
				if (claims != null && claims.getSubject() != null) {
					return new CustomAuthenticationToken(claims.getSubject(), null, (String) claims.get("name"));
				}
			}
		}
		return null;
	}
	
	public static String buildJwt(String name, String principal) {
		Map<String, String> claims = new HashMap<String, String>();
		claims.put("name", name);
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 10L))
				.setSubject(principal)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	public Claims parseJwt(String token) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody();
		} catch (SignatureException ex) {
			return null;
		}
		return claims;
	}

}
