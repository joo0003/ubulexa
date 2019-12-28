package es.ubu.ubulexa.core.handlers.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;

public interface RequestHandlerModel {

  Optional<Response> handle(HandlerInput handlerInput);
}
