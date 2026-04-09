package com.thinkdifferent.aipicturebackend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // ⚠️ 必须 >= 32 字节
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor("mysecretkeymysecretkey123456".getBytes());

    private static final long EXPIRATION = 1000 * 60 * 60; // 1小时

    // 生成 token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    // 解析 token
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 校验 token
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}