package es.ubu.ubulexa.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class JwtUtils {

  public Algorithm algorithm(String secret) {
    return Algorithm.HMAC256(secret);
  }

  public JWTCreator.Builder builder() {
    return JWT.create();
  }

  public JWTVerifier verifier(Algorithm algorithm) {
    return JWT.require(algorithm).build();
  }
}
