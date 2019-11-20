package es.ubu.ubulexa.exceptionhandlers.matchers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface Matcher {

  boolean match(HandlerInput handlerInput, Throwable throwable);
}
