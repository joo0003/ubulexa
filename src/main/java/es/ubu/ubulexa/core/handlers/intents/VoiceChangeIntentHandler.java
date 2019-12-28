package es.ubu.ubulexa.core.handlers.intents;

import es.ubu.ubulexa.core.handlers.intents.matchers.VoiceChangeIntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.VoiceChangeIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class VoiceChangeIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public VoiceChangeIntentHandler(
      VoiceChangeIntentMatcher matcher,
      VoiceChangeIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}