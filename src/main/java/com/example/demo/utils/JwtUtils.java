package com.example.demo.utils;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.entity.SignUp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static String secret = "This_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(SignUp signUp){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(signUp.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // optional claims
       // claims.put("type", user.getUserType());
        claims.put("name", signUp.getUsername());
        claims.put("emailId", signUp.getEmail());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }
}

