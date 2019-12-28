package es.ubu.ubulexa.core.matchers.requests;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface RequestMatcher {

  boolean match(HandlerInput handlerInput);
}
