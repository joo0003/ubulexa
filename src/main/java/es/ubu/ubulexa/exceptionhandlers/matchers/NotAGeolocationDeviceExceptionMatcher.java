package es.ubu.ubulexa.exceptionhandlers.matchers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class NotAGeolocationDeviceExceptionMatcher extends AbstractExceptionMatcher {

  public boolean match(HandlerInput handlerInput, Throwable throwable) {
    //return super.match(throwable, NotAGeolocationDeviceException.class);
    return false;
  }
}