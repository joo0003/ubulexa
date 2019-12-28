package es.ubu.ubulexa.core.handlers.intents;

import es.ubu.ubulexa.core.handlers.intents.matchers.FirstTimeIntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.FirstTimeIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class FirstTimeIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public FirstTimeIntentHandler(
      FirstTimeIntentMatcher matcher,
      FirstTimeIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}