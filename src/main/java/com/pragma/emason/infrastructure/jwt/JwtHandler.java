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


    // Validar el token usando la misma llave HMAC
    public Boolean validateToken(String token) {
        try {
            // Parseamos y validamos el token con la clave secreta
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Verificamos que el token no esté expirado
            return !claims.getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            // Si ocurre alguna excepción, el token es inválido
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }



    // Obtener un reclamo específico del token
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }



    // Obtener todos los claims (reclamos) del token
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)  // Usa la misma llave para obtener los claims
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    // Obtener roles del token
    public List<GrantedAuthority> getRoles(String token) {
        Claims claims = getAllClaims(token);
        String roles = claims.get("roles", String.class);  // Verifica cómo estructuraste los roles en el JWT
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

