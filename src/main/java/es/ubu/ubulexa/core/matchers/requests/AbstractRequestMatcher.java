package es.ubu.ubulexa.core.matchers.requests;


import static com.amazon.ask.request.Predicates.requestType;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Request;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public abstract class AbstractRequestMatcher implements RequestMatcher {

  protected boolean match(HandlerInput handlerInput, Class<? extends Request> requestType) {
    try {
      return handlerInput.matches(requestType(requestType));
    } catch (Exception e) {
      ExceptionUtils.log(e);
      return false;
    }
  }
}