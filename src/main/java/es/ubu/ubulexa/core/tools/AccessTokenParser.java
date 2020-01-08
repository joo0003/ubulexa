package es.ubu.ubulexa.core.tools;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import es.ubu.ubulexa.core.utils.JwtUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class AccessTokenParser {

  private JwtUtils jwtUtils;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setJwtUtils(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  public DecodedJWT parse(String token) {
    Algorithm algorithm = jwtUtils.algorithm(appConfig.jwtSecret());
    JWTVerifier jwtVerifier = jwtUtils.verifier(algorithm);
    try {
      return jwtVerifier.verify(token);
    } catch (JWTVerificationException e) {
      ExceptionUtils.log(e);
      return null;
    }
  }
}
