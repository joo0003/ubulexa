package es.ubu.ubulexa.core.matchers.intents;


import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public abstract class AbstractIntentMatcher implements IntentMatcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      AbstractIntentMatcher.class);

  protected boolean match(HandlerInput handlerInput, String intentName) {
    try {
      return handlerInput.matches(intentName(intentName));
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
      return false;
    }
  }
}