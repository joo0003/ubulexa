package es.ubu.ubulexa.requesthandlers;

import es.ubu.ubulexa.requesthandlers.matchers.CancelIntentMatcher;
import es.ubu.ubulexa.requesthandlers.models.CancelIntentHandlerModel;
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