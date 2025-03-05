package com.example.jwtExam.jwt.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenizer {
    private final byte[] accesssSecret;
    private final byte[] refreshSecret;

    public static Long ACCESS_TOKEN_EXPIRE_CONMT = 30 * 60 * 1000L; // 유지시간 30분
    public static Long REFRESH_TOKEN_EXPIRE_COUNT = 7 * 24 * 60 * 30 * 1000L; // 유지시간 7일

    public JwtTokenizer(@Value("${jwt.secretKey}")String accesssSecret, @Value("${jwt.refreshKey}")String refreshSecret) {
        this.accesssSecret = accesssSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);
    }

    private String createToken(Long id, String email,String name, String username, List<String> roles,
                               Long expires, byte[] SecretKey){

        // 고유한 식별자 값으로 넣는게 좋다.
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("name", name);
        claims.put("username", username);
        claims.put("userId",id);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expires)) //expries 값이 1000 * 60 * 30 = 30분
                .signWith(getSigningKey(SecretKey))
                .compact();
    }

    private static Key getSigningKey(byte[] secretkey){
        return Keys.hmacShaKeyFor(secretkey);
    }

    // ACCESS TOKEN 생성
    public String createAccessToken(Long id, String email,String name, String username, List<String> roles){
        return createToken(id, email,name, username, roles, ACCESS_TOKEN_EXPIRE_CONMT, accesssSecret);
    }

    // REFRESH TOKEN 생성
    public String createRefreshToken(Long id, String email,String name,  String username, List<String> roles){
        return createToken(id, email, name, username, roles, ACCESS_TOKEN_EXPIRE_CONMT, refreshSecret);
    }
}
