package es.ubu.ubulexa.exceptionhandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;

public interface HandlerModel {

  Optional<Response> handle(HandlerInput handlerInput, Throwable throwable);
}
