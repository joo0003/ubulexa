package es.ubu.ubulexa.requesthandlers.matchers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class NavigateHomeIntentMatcher extends AbstractIntentMatcher {

  public boolean match(HandlerInput handlerInput) {
    return super.match(handlerInput, Constants.NAVIGATE_HOME_INTENT);
  }
}