package es.ubu.ubulexa.core.handlers.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class SessionEndedRequestHandlerModel extends AbstractRequestHandlerModel {

  public Optional<Response> handle(HandlerInput handlerInput) {
    return responseBuilder(handlerInput).build();
  }
}