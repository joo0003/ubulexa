package es.ubu.ubulexa.core.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.MoodleTokenJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.UserIdJwtClaimExtractor;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.UsernameJwtClaimExtractor;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractRequestInterceptorStep implements RequestInterceptorStep {

  private UserIdJwtClaimExtractor userIdJwtClaimExtractor;
  private UsernameJwtClaimExtractor usernameJwtClaimExtractor;
  private MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor;
  private AppConfig appConfig;

  @PetiteInject
  public void setMoodleTokenJwtClaimExtractor(
      MoodleTokenJwtClaimExtractor moodleTokenJwtClaimExtractor) {
    this.moodleTokenJwtClaimExtractor = moodleTokenJwtClaimExtractor;
  }

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setUsernameJwtClaimExtractor(
      UsernameJwtClaimExtractor usernameJwtClaimExtractor) {
    this.usernameJwtClaimExtractor = usernameJwtClaimExtractor;
  }

  @PetiteInject
  public void setUserIdJwtClaimExtractor(
      UserIdJwtClaimExtractor userIdJwtClaimExtractor) {
    this.userIdJwtClaimExtractor = userIdJwtClaimExtractor;
  }

  @Override
  public abstract void run(HandlerInput handlerInput);

  public AppConfig appConfig() {
    return appConfig;
  }

  public String extractUserId(HandlerInput handlerInput) {
    return userIdJwtClaimExtractor.extract(handlerInput);
  }

  public String extractUsername(HandlerInput handlerInput) {
    return usernameJwtClaimExtractor.extract(handlerInput);
  }

  public String extractMoodleToken(HandlerInput handlerInput) {
    return moodleTokenJwtClaimExtractor.extract(handlerInput);
  }
}
