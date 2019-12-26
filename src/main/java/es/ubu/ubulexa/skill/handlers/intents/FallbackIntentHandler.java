package es.ubu.ubulexa.skill.handlers.intents;

import es.ubu.ubulexa.skill.handlers.intents.matchers.FallbackIntentMatcher;
import es.ubu.ubulexa.skill.handlers.intents.models.FallbackIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class FallbackIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public FallbackIntentHandler(
      FallbackIntentMatcher matcher,
      FallbackIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}