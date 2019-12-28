package es.ubu.ubulexa.core.handlers.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class LaunchRequestHandlerModel extends AbstractRequestHandlerModel {

  public Optional<Response> handle(HandlerInput handlerInput) {
    ResponseTemplate responseTemplate = new ResponseTemplate();
    responseTemplate.setClazz(LaunchRequestHandlerModel.class);

    return buildResponse(handlerInput, responseTemplate);
  }
}