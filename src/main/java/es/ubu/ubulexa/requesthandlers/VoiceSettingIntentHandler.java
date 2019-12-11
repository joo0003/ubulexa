package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.VoiceSettingIntentMatcher;
import es.ubu.ubulexa.requesthandlers.models.VoiceSettingIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class VoiceSettingIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public VoiceSettingIntentHandler(
      VoiceSettingIntentMatcher matcher,
      VoiceSettingIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}