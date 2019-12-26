package es.ubu.ubulexa.skill.handlers.requesthandlers;

import es.ubu.ubulexa.skill.handlers.requesthandlers.matchers.LaunchRequestMatcher;
import es.ubu.ubulexa.skill.handlers.requesthandlers.models.LaunchRequestHandlerModel;
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