package es.ubu.ubulexa.skill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.tools.ResponseFactoryResolver;
import es.ubu.ubulexa.core.tools.jwtclaimextractors.UserIdJwtClaimExtractor;
import es.ubu.ubulexa.skill.responsefactories.ResponseFactory;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractHandlerModel implements HandlerModel {

  private ResponseFactoryResolver responseFactoryResolver;
  private UserIdJwtClaimExtractor userIdJwtClaimExtractor;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setUserIdJwtClaimExtractor(
      UserIdJwtClaimExtractor userIdJwtClaimExtractor) {
    this.userIdJwtClaimExtractor = userIdJwtClaimExtractor;
  }

  @PetiteInject
  public void setResponseFactoryResolver(
      ResponseFactoryResolver responseFactoryResolver) {
    this.responseFactoryResolver = responseFactoryResolver;
  }

  protected AppConfig getAppConfig() {
    return appConfig;
  }

  protected String extractUserId(HandlerInput handlerInput) {
    return userIdJwtClaimExtractor.extract(handlerInput);
  }

  protected Optional<Response> buildResponse(
      HandlerInput handlerInput,
      ResponseTemplate responseTemplate
  ) {
    ResponseFactory responseFactory = responseFactoryResolver.resolve(responseTemplate);
    if (null == responseFactory) {
      return Optional.empty();
    }
    return responseFactory.build(handlerInput, responseTemplate);
  }
}