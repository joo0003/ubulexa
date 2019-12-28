package es.ubu.ubulexa.core.matchers.intents;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class FallbackIntentMatcher extends AbstractIntentMatcher {

  public boolean match(HandlerInput handlerInput) {
    return super.match(handlerInput, Constants.FALLBACK_INTENT);
  }
}