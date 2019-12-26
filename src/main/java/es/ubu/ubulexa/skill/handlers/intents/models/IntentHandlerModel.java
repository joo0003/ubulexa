package es.ubu.ubulexa.skill.handlers.intents.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;

public interface IntentHandlerModel {

  Optional<Response> handle(HandlerInput handlerInput);
}
