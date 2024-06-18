package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.SignUp;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private String secret = "qwertyuiopkjjhgfdsazxcvdzdbnweryqwertyuiopkjjhgfdsazxcvdzdbnwery"; // Ensure it's long
																								// enough for HMAC512

	public String generateJwt(SignUp signUp) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			String jwt = JWT.create().withClaim("id", signUp.getId())
					.withClaim("issuedAt", new Date(System.currentTimeMillis()))
					.withClaim("expiryAt", new Date(System.currentTimeMillis() + 3600000))
					.withClaim("name", signUp.getUsername()).withClaim("email", signUp.getEmail())
					.withClaim("ph_Num", signUp.getPhNum()).withClaim("gender", signUp.getGender()).sign(algorithm);
			takeData(jwt);
			return jwt;
		} catch (Exception e) {
			return e.toString();
		}
	}

	private Key getSigningKey() {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_16);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String tokenVerify(String token) {
		try {
			// Note: Here the parser is set to use the same algorithm and key
			Algorithm algorithm = Algorithm.HMAC512(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return "success";
		} catch (JwtException e) {
			return e.toString();
		}
	}

	public String takeData(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();

			// Verify and decode the JWT
			DecodedJWT jwt = verifier.verify(token);

			System.out.println("Token is valid: " + jwt.getToken());
			System.out.println("Name: " + jwt.getClaim("name").asString());
			System.out.println("Email: " + jwt.getClaim("email").asString());
		} catch (Exception e) {
			// Handle the exception
			System.err.println("Invalid Token: " + e.getMessage());
		}
		return null;
	}
}
