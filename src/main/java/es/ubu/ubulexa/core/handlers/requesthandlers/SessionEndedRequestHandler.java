package es.ubu.ubulexa.core.handlers.requesthandlers;

import es.ubu.ubulexa.core.handlers.requesthandlers.matchers.SessionEndedRequestMatcher;
import es.ubu.ubulexa.core.handlers.requesthandlers.models.SessionEndedRequestHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class SessionEndedRequestHandler extends AbstractRequestHandler {

  @PetiteInject
  public SessionEndedRequestHandler(
      SessionEndedRequestMatcher matcher,
      SessionEndedRequestHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}