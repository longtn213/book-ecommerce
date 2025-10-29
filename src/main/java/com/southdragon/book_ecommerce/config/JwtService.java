package com.southdragon.book_ecommerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SECRET_KEY = "THIS_IS_MY_SECRET_KEY_FOR_BOOK_ECOMMERCE_2025_VERY_SECRET";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Tạo token JWT
    public String generateToken(String email) {
        long expiration = 1000 * 60 * 60 * 24; // 24h
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Lấy email từ token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Kiểm tra token hợp lệ
    public boolean isTokenValid(String token, String userEmail) {
        final String extractedEmail = extractEmail(token);
        return extractedEmail.equals(userEmail) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
