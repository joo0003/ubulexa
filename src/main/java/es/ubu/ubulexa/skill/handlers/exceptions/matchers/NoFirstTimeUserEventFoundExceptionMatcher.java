package es.ubu.ubulexa.skill.handlers.exceptions.matchers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.exceptions.NoFirstTimeUserEventFoundException;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class NoFirstTimeUserEventFoundExceptionMatcher extends AbstractExceptionMatcher {

  public boolean match(HandlerInput handlerInput, Throwable throwable) {
    return super.match(throwable, NoFirstTimeUserEventFoundException.class);
  }
}