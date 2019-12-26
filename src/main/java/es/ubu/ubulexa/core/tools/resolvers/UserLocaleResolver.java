package es.ubu.ubulexa.core.tools.resolvers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.skill.handlers.intents.matchers.AbstractIntentMatcher;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class UserLocaleResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIntentMatcher.class);

  public String resolve(HandlerInput handlerInput) {
    return Constants.ES_ES_LOCALE_CODE;

    /*
    try {
      String locale = handlerInput.getRequestEnvelope().getRequest().getLocale();
      if (Constants.ES_ES_LOCALE_CODE.equals(locale)) {
        return Constants.ES_ES_LOCALE_CODE;
      }
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
    return Constants.EN_US_LOCALE_CODE;
     */
  }
}
