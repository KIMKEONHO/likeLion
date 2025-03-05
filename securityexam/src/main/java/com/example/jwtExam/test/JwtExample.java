package com.example.jwtExam.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtExample {
    public static void main(String[] args) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//        String secretKey = "asdzxcvased12345";
//        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
//        SecretKey key = Keys.hmacShaKeyFor(bytes);

        String jwt = Jwts.builder()
                .setIssuer("carami-app")
                .setSubject("carami123")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .claim("role","ADMIN")
                .signWith(secretKey)
                .compact();

        System.out.println(jwt);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt).getBody();

        System.out.println(claims.getExpiration());
        System.out.println(claims.getSubject());
        System.out.println(claims.getAudience());
    }
}
