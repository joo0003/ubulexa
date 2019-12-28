package es.ubu.ubulexa.core.handlers.requesthandlers;

import es.ubu.ubulexa.core.handlers.requesthandlers.matchers.LaunchRequestMatcher;
import es.ubu.ubulexa.core.handlers.requesthandlers.models.LaunchRequestHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class LaunchRequestHandler extends AbstractRequestHandler {

  @PetiteInject
  public LaunchRequestHandler(
      LaunchRequestMatcher matcher,
      LaunchRequestHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}