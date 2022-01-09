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
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenAuthenticationService {

	private static JWTProperties jwtProperties;
	
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
    public TokenAuthenticationService(@Qualifier("JWTProperties") JWTProperties jwtProperties) {
		TokenAuthenticationService.jwtProperties = jwtProperties;
    }
	
	static void addAuthentication(HttpServletResponse response, CustomAuthenticationToken authentication) throws IOException {
		Map<String, String> claims = new HashMap<String, String>();
		claims.put("name", authentication.getName());
		
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
		String JWT = Jwts.builder().setClaims(claims).setSubject(authentication.getPrincipal())
				.signWith(key, SignatureAlgorithm.HS512).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		response.getWriter().write(authentication.getName());
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
			Claims claims = Jwts.parserBuilder()
						.setClock(getNewClock())
						.setSigningKey(key)
						.build()
						.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
						.getBody();
			if (claims != null && claims.getSubject() != null) {
				return new CustomAuthenticationToken(claims.getSubject(), null, (String) claims.get("name"));
			}
		}
		return null;
	}
	
	public Clock getNewClock() {
		return new Clock() {
			
            @Override
            public Date now() {
                return new Date(System.currentTimeMillis()/2);
            }
		};
	}

}
