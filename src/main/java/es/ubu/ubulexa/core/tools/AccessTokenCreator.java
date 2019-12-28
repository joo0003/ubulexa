package es.ubu.ubulexa.core.tools;

import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.utils.JwtUtils;
import es.ubu.ubulexa.core.utils.LocalDateTimeUtils;
import java.sql.Date;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class AccessTokenCreator {

  private AppConfig appConfig;
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
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  public String create(String userId, String username, String moodleToken) {
    Algorithm algorithm = jwtUtils.algorithm(appConfig.jwtSecret());

    Builder jwtBuilder = jwtUtils.builder();
    jwtBuilder.withSubject(userId);
    jwtBuilder.withClaim(Constants.JWT_USER_ID_CLAIM, userId);
    jwtBuilder.withClaim(Constants.JWT_USERNAME_CLAIM, username);
    jwtBuilder.withClaim(Constants.JWT_MOODLE_TOKEN_CLAIM, moodleToken);
    jwtBuilder.withIssuedAt(Date.valueOf(localDateTimeUtils.nowUtc().toLocalDate()));
    return jwtBuilder.sign(algorithm);
  }
}
