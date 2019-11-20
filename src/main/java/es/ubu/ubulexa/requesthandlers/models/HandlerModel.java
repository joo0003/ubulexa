package es.ubu.ubulexa.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;

public interface HandlerModel {

  Optional<Response> handle(HandlerInput handlerInput);
}
