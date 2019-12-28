package es.ubu.ubulexa.core.handlers.intents;

import es.ubu.ubulexa.core.handlers.intents.matchers.CancelIntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.CancelIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class CancelIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public CancelIntentHandler(
      CancelIntentMatcher matcher,
      CancelIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}