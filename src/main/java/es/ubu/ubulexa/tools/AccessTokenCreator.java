package es.ubu.ubulexa.tools;

import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import es.ubu.ubulexa.utils.JwtUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class AccessTokenCreator {

  private SystemEnvReader systemEnvReader;
  private JwtUtils jwtUtils;

  @PetiteInject
  public void setJwtUtils(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  public String create(String username, String token) {
    Algorithm algorithm = jwtUtils.algorithm(systemEnvReader.jwtSecret());

    Builder jwtBuilder = jwtUtils.builder();
    jwtBuilder.withIssuer(username);
    jwtBuilder.withSubject(token);
    return jwtBuilder.sign(algorithm);
  }
}
