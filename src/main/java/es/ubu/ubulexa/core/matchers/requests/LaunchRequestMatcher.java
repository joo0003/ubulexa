package es.ubu.ubulexa.core.matchers.requests;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class LaunchRequestMatcher extends AbstractRequestMatcher {

  public boolean match(HandlerInput handlerInput) {
    return super.match(handlerInput, LaunchRequest.class);
  }
}
