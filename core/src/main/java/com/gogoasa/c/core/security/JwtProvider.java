package com.gogoasa.c.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtProvider {
    private static final String AUTHORITIES = "authorities";

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username, Long ttl, Set<String> authorities) {
        Date expriationDate = Date.from(ZonedDateTime.now().plusMinutes(ttl).toInstant());

        return Jwts.builder()
            .setSubject(username)
            .claim(AUTHORITIES, authorities)
            .setExpiration(expriationDate)
            .signWith(getJwtKey(), SignatureAlgorithm.HS512)
            .compact();
    }

    public Key getJwtKey() {
        byte[] keyByteArray = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyByteArray);
    }

    public boolean validateToken(String token) {

        if (token.isBlank()) {
            throw new IllegalArgumentException("JWT is blank!");
        }

        try {

            Jwts.parserBuilder()
                .setSigningKey(getJwtKey())
                .build()
                .parseClaimsJws(token);

        } catch (JwtException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("JWT is blank!");
        }

        return true;
    }

    public Authentication doAuthentication(String token) {
        try {

            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getJwtKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            //noinspection unchecked
            Set<SimpleGrantedAuthority> grantedAuthorities = ((ArrayList<String>) claims.get(AUTHORITIES)).stream()
                .filter(claim -> claim != null && !claim.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

            User user = new User(claims.getSubject(), "", grantedAuthorities);

            return new UsernamePasswordAuthenticationToken(user, "", grantedAuthorities);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT!", e);
        }
    }

    public String resolveToken(String authorizationHeader) {

        if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.contains("Bearer")) {
            return "";
        }

        return authorizationHeader.substring(7);
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}