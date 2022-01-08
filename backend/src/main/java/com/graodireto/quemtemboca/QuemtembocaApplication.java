package com.graodireto.quemtemboca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuemtembocaApplication {

	public static void main(String[] args) {
		// Para gerar a Key do JWT:
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//		Encoders.BASE64.encode(key.getEncoded()); // ta041HkHiFyI/VupEHAC8mpEa6S7qK0FSawo7D4HcaJ7cUqyI8sR6QkjXQIKMibhm7YYGRcR2gyFiz/ijiIHGA==
		SpringApplication.run(QuemtembocaApplication.class, args);
	}

}
