package es.ubu.ubulexa.skill.handlers.exceptions;

import es.ubu.ubulexa.skill.handlers.exceptions.matchers.NoFirstTimeUserEventFoundExceptionMatcher;
import es.ubu.ubulexa.skill.handlers.exceptions.models.NoFirstTimeUserEventFoundExceptionHandlerModel;
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