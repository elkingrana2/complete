package com.example.parqueaderoapi.security;

import java.util.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class TokenUtils {

  // Generar el token
  private final static String ACCESS_TOKEN_SECRET = "xyz3LdajjedeWSWDEdaod390dkelmcK";
  // vida util del token (6 horas)
  private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60L;

  public static String createToken(String nombre, String correo) {
    Long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
    Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

    // produce un token que sera enviado al cliente

    Map<String, Object> extra = new HashMap<>();

    extra.put("nombre", nombre);

    return Jwts.builder()
        .setSubject(correo)
        .setExpiration(expirationDate)
        .addClaims(extra)
        .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
        .compact();
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    try {

      Claims claims = Jwts.parserBuilder()
          .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
          .build()
          .parseClaimsJws(token)
          .getBody();

      String correo = claims.getSubject();
      // String nombre = (String) claims.get("nombre");

      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

      return new UsernamePasswordAuthenticationToken(correo, "", Collections.emptyList());

    } catch (JwtException e) {
      return null;
    }
  }
}
