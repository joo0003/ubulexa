package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.LaunchRequestMatcher;
import es.ubu.ubulexa.requesthandlers.models.LaunchRequestHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class LaunchRequestHandler extends AbstractIntentHandler {

  @PetiteInject
  public LaunchRequestHandler(
      LaunchRequestMatcher matcher,
      LaunchRequestHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}