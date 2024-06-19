package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.SignUp;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private String secret = "qwertyuiopkjjhgfdsazxcvdzdbnwery";

	public String generateJwt(SignUp signUp) {
		try {
			String jwt = Jwts.builder().header().and().claim("id", signUp.getId())
					.claim("issuedAt", new Date(System.currentTimeMillis()))
					.claim("expiryAt", new Date(System.currentTimeMillis() + 3600000))
					.claim("name", signUp.getUsername()).claim("email", signUp.getEmail())
					.claim("ph_Num", signUp.getPhNum()).claim("gender", signUp.getGender()).signWith(getSigningKey())
					.compact();
			takeData(jwt);
			return jwt;
		} catch (Exception e) {
			return e.toString();
		}
	}

	private Key getSigningKey() {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
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

	String cla = "";

	public String takeData(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();

			// Verify and decode the JWT
			DecodedJWT jwt = verifier.verify(token);

			System.out.println("Token is valid: " + jwt.getToken());
			System.out.println("Name: " + jwt.getClaim("name").asString());
			System.out.println("Email: " + jwt.getClaim("email").asString());
			// cla=jwt.getClaim("name")+"\n"+jwt.getClaim("email");

		} catch (Exception e) {
			// Handle the exception
			System.err.println("Invalid Token: " + e.getMessage());
		}
		// return null;
		return cla;
	}
}