package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.SessionEndedRequestMatcher;
import es.ubu.ubulexa.requesthandlers.models.SessionEndedRequestHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class SessionEndedRequestHandler extends AbstractIntentHandler {

  @PetiteInject
  public SessionEndedRequestHandler(
      SessionEndedRequestMatcher matcher,
      SessionEndedRequestHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}