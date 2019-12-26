package es.ubu.ubulexa.skill.handlers.intents;

import es.ubu.ubulexa.skill.handlers.intents.matchers.NavigateHomeIntentMatcher;
import es.ubu.ubulexa.skill.handlers.intents.models.NavigateHomeIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class NavigateHomeIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public NavigateHomeIntentHandler(
      NavigateHomeIntentMatcher matcher,
      NavigateHomeIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}