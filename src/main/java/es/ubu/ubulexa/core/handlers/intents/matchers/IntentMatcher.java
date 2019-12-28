package es.ubu.ubulexa.core.handlers.intents.matchers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface IntentMatcher {

  boolean match(HandlerInput handlerInput);
}
