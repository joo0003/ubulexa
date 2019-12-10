package es.ubu.ubulexa.exceptionhandlers;

import es.ubu.ubulexa.exceptionhandlers.matchers.NoFirstTimeUserEventFoundExceptionMatcher;
import es.ubu.ubulexa.exceptionhandlers.models.NoFirstTimeUserEventFoundExceptionHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class NoFirstTimeUserEventFoundExceptionHandler extends AbstractExceptionHandler {

  @PetiteInject
  public NoFirstTimeUserEventFoundExceptionHandler(
      NoFirstTimeUserEventFoundExceptionMatcher matcher,
      NoFirstTimeUserEventFoundExceptionHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}