package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.HelpIntentMatcher;
import es.ubu.ubulexa.requesthandlers.models.HelpIntentHandlerModel;
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