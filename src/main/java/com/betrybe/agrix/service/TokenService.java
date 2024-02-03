package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.model.entities.Person;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 Classe da configuração de segurança do token.
 */

@Service
public class TokenService {
  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   Método que gera o token.
   */
  public String generateToken(UserDetails user) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   Método que determina o Tempo de expiração fo token.
   */

  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  /**
   Método que valida o token.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
