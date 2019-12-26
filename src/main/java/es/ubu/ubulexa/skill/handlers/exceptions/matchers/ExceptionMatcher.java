package es.ubu.ubulexa.skill.handlers.exceptions.matchers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface ExceptionMatcher {

  boolean match(HandlerInput handlerInput, Throwable throwable);
}
