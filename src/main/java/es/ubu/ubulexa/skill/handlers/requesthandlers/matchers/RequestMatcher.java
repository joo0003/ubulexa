package es.ubu.ubulexa.skill.handlers.requesthandlers.matchers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface RequestMatcher {

  boolean match(HandlerInput handlerInput);
}
