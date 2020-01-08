package es.ubu.ubulexa.core.matchers.intents;


import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractIntentMatcher implements IntentMatcher {

  protected boolean match(HandlerInput handlerInput, String intentName) {
    try {
      return handlerInput.matches(intentName(intentName));
    } catch (Exception e) {
      ExceptionUtils.log(e);
      return false;
    }
  }
}