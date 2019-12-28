package es.ubu.ubulexa.core.handlers.intents;

import es.ubu.ubulexa.core.handlers.intents.matchers.CalendarIntentMatcher;
import es.ubu.ubulexa.core.handlers.intents.models.CalendarIntentHandlerModel;
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