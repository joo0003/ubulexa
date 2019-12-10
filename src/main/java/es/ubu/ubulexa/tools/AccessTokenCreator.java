package es.ubu.ubulexa.tools;

import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.utils.JwtUtils;
import es.ubu.ubulexa.utils.LocalDateTimeUtils;
import java.sql.Date;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class AccessTokenCreator {

  private SystemEnvReader systemEnvReader;
  private JwtUtils jwtUtils;
  private LocalDateTimeUtils localDateTimeUtils;

  @PetiteInject
  public void setLocalDateTimeUtils(LocalDateTimeUtils localDateTimeUtils) {
    this.localDateTimeUtils = localDateTimeUtils;
  }

  @PetiteInject
  public void setJwtUtils(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  public String create(String userId, String username, String token) {
    Algorithm algorithm = jwtUtils.algorithm(systemEnvReader.jwtSecret());

    Builder jwtBuilder = jwtUtils.builder();
    jwtBuilder.withSubject(userId);
    jwtBuilder.withClaim(Constants.JWT_USERNAME_CLAIM, username);
    jwtBuilder.withClaim(Constants.JWT_TOKEN_CLAIM, token);
    jwtBuilder.withIssuedAt(Date.valueOf(localDateTimeUtils.nowUtc().toLocalDate()));
    return jwtBuilder.sign(algorithm);
  }
}
