package com.pragma.emason.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Service
@Getter
public class JwtHandler {

    private String secret = "mi_clave_secreta_fija_que_debe_ser_larga_123456";  // Usa la misma clave secreta que en el segundo proyecto




    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }



    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }



    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public List<GrantedAuthority> getRoles(String token) {
        Claims claims = getAllClaims(token);
        String roles = claims.get("roles", String.class);  // Debes definir cómo están estructurados los roles en el JWT
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

