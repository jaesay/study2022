package com.example.keywordalarmappbackend.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenProvider {
  private static final String SECRET_KEY = "FlRpX30pMqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M1Oicegkpi46AdvrcX1E6CmTUBc6IFbTPiD";

  public String validateAndGetUserId(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  public String create(final Authentication authentication) {
    ApplicationOAuth2User userPrincipal = (ApplicationOAuth2User) authentication.getPrincipal();
    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

    return Jwts.builder()
        .setSubject(userPrincipal.getName())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();
  }
}
