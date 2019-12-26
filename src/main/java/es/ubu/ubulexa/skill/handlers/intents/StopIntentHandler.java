package es.ubu.ubulexa.skill.handlers.intents;

import es.ubu.ubulexa.skill.handlers.intents.matchers.StopIntentMatcher;
import es.ubu.ubulexa.skill.handlers.intents.models.StopIntentHandlerModel;
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