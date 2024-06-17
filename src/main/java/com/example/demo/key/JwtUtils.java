package com.example.demo.key;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.entity.SignUp;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private String secret = "qwertyuiopkjjhgfdsazxcvdzdbnwery";

	public String generateJwt(SignUp signUp) {
		try {
			String jwt = Jwts.builder().header().add("id", signUp.getId())
					.add("issuedAt", new Date(System.currentTimeMillis()))
					.add("expiryAt", new Date((System.currentTimeMillis()) + 60)).and()
					.claim("name", signUp.getUsername()).claim("email", signUp.getEmail())
					.claim("ph_Num", signUp.getPhNum()).claim("gender", signUp.getGender()).signWith(getSigningKey())
					.compact();
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

			Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token);
			return "success";

		} catch (JwtException e) {

			return e.toString();
		}
	}
}