package com.pragma.emason.infrastructure.jwt;

import com.pragma.emason.application.util.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
@Service
@Getter
public class JwtHandler {

    private final SecretKey key = Keys.hmacShaKeyFor(ApplicationConstants.SECRET_KEY_PASSWORD.getBytes());



    public String getToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // Use the email as the subject
                .setIssuedAt(new Date())  // Emission date
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.TOKEN_EXPIRATION_TIME))  // Expiration date
                .signWith(key)  // Sign with the secret key
                .compact();  // Compact and return the token
    }



    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }



    // Validate the token using the same HMAC key
    public Boolean validateToken(String token) {
        try {
            // Parse and validate the token with the secret key
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check that the token is not expired
            return !claims.getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            // If an exception occurs, the token is invalid
            return false;
        }
    }



    // Get a specific claim from the token
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }



    // Get all claims from the token
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)  // Use the same key to retrieve claims
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    // Get roles from the token
    public List<GrantedAuthority> getRoles(String token) {
        Claims claims = getAllClaims(token);
        String roles = claims.get("roles", String.class);  // Check how you structured the roles in the JWT
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

