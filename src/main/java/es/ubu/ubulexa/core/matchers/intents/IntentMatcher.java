package es.ubu.ubulexa.core.matchers.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface IntentMatcher {

  boolean match(HandlerInput handlerInput);
}
