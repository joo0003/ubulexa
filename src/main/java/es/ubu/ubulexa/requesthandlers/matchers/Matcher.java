package es.ubu.ubulexa.requesthandlers.matchers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface Matcher {

  boolean match(HandlerInput handlerInput);
}
