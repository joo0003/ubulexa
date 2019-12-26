package es.ubu.ubulexa.skill.handlers.intents;

import es.ubu.ubulexa.skill.handlers.intents.matchers.CalendarIntentMatcher;
import es.ubu.ubulexa.skill.handlers.intents.matchers.VoiceChangeIntentMatcher;
import es.ubu.ubulexa.skill.handlers.intents.models.CalendarIntentHandlerModel;
import es.ubu.ubulexa.skill.handlers.intents.models.VoiceChangeIntentHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class CalendarIntentHandler extends AbstractIntentHandler {

  @PetiteInject
  public CalendarIntentHandler(
      CalendarIntentMatcher matcher,
      CalendarIntentHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}