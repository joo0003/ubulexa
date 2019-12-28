package es.ubu.ubulexa.core.handlers.intents;

import es.ubu.ubulexa.core.handlers.intents.matchers.HelpIntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.HelpIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class HelpIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public HelpIntentHandler(
      HelpIntentMatcher matcher,
      HelpIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}