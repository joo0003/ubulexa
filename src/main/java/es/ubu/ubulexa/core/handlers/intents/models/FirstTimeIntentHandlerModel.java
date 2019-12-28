package es.ubu.ubulexa.core.handlers.intents.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class FirstTimeIntentHandlerModel extends AbstractIntentHandlerModel {

  public Optional<Response> handle(HandlerInput handlerInput) {
    ResponseTemplate responseTemplate = new ResponseTemplate();
    responseTemplate.setClazz(FirstTimeIntentHandlerModel.class);
    responseTemplate.setEndSession(false);

    return buildResponse(handlerInput, responseTemplate);
  }
}