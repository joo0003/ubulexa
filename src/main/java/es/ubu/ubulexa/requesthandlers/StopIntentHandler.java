package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.StopIntentMatcher;
import es.ubu.ubulexa.requesthandlers.models.StopIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class StopIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public StopIntentHandler(
      StopIntentMatcher matcher,
      StopIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}