package es.ubu.ubulexa.core.tools;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class UserLocaleResolver {

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
