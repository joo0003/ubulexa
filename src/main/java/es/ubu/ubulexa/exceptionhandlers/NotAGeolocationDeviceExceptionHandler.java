package es.ubu.ubulexa.exceptionhandlers;

import es.ubu.ubulexa.exceptionhandlers.matchers.NotAGeolocationDeviceExceptionMatcher;
import es.ubu.ubulexa.exceptionhandlers.models.NotAGeolocationDeviceExceptionHandlerModel;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class NotAGeolocationDeviceExceptionHandler extends AbstractExceptionHandler {

  @PetiteInject
  public NotAGeolocationDeviceExceptionHandler(
      NotAGeolocationDeviceExceptionMatcher matcher,
      NotAGeolocationDeviceExceptionHandlerModel handlerModel
  ) {
    setMatcher(matcher);
    setHandlerModel(handlerModel);
  }
}