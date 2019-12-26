package es.ubu.ubulexa.skill.handlers.exceptions.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;

public interface ExceptionHandlerModel {

  Optional<Response> handle(HandlerInput handlerInput, Throwable throwable);
}
