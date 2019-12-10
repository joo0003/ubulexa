package es.ubu.ubulexa.tools;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.ubu.ubulexa.utils.JwtUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class AccessTokenParser {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(AccessTokenParser.class);

  private JwtUtils jwtUtils;
  private SystemEnvReader systemEnvReader;

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setJwtUtils(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  public DecodedJWT parse(String token) {
    Algorithm algorithm = jwtUtils.algorithm(systemEnvReader.jwtSecret());
    JWTVerifier jwtVerifier = jwtUtils.verifier(algorithm);
    try {
      return jwtVerifier.verify(token);
    } catch (JWTVerificationException e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
      return null;
    }
  }
}
