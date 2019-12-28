package es.ubu.ubulexa.core.matchers.requests;


import static com.amazon.ask.request.Predicates.requestType;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Request;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public abstract class AbstractRequestMatcher implements RequestMatcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestMatcher.class);

  protected boolean match(HandlerInput handlerInput, Class<? extends Request> requestType) {
    try {
      return handlerInput.matches(requestType(requestType));
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
      return false;
    }
  }
}