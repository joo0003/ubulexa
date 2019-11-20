package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.NavigateHomeIntentMatcher;
import es.ubu.ubulexa.requesthandlers.models.NavigateHomeIntentHandlerModel;
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