package es.ubu.ubulexa.core.matchers.requests;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.SessionEndedRequest;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class SessionEndedRequestMatcher extends AbstractRequestMatcher {

  public boolean match(HandlerInput handlerInput) {
    return super.match(handlerInput, SessionEndedRequest.class);
  }

}
