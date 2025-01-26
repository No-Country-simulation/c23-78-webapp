package com.trackmyfix.trackmyfix.configs.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JWTService {

    @Value(value="${app.security.jwt.secret-key}")
    private String secretkey;
    @Value(value="${app.security.jwt.expiration}")
    private String jwtExpiration;
    @Value(value="${app.security.jwt.refresh-token.expiration}")
    private String jwtRefreshExpiration;

    public Map<String, String> generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, String> tokenInfo = new HashMap<>();
        Date issuedAt = new Date(System.currentTimeMillis());
        Date access_expiration = new Date(System.currentTimeMillis() + Integer.parseInt(jwtExpiration));
        Date refresh_expiration = new Date(System.currentTimeMillis() + Integer.parseInt(jwtRefreshExpiration));

        String access_token = Jwts.builder()
                .claims()
                .add(claims)
                .add("type", "access")
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(access_expiration)
                .issuer("trackmyfix")
                .and()
                .signWith(getKey())
                .compact();

        String refresh_token = Jwts.builder()
                .claims()
                .add(claims)
                .add("type", "refresh")
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(refresh_expiration)
                .issuer("trackmyfix")
                .and()
                .signWith(getKey())
                .compact();

        tokenInfo.put("refresh_token", refresh_token);
        tokenInfo.put("access_token", access_token);
        tokenInfo.put("token_type", "Bearer");
        tokenInfo.put("issued_at", issuedAt.toString());
        tokenInfo.put("expires_in", access_expiration.toString());
        tokenInfo.put("refresh_expires", refresh_expiration.toString());
        return tokenInfo;
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
