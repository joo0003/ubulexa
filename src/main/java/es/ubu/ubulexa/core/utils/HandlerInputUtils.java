package es.ubu.ubulexa.core.utils;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.response.ResponseBuilder;
import java.util.function.Predicate;
import jodd.petite.meta.PetiteBean;

@PetiteBean
public class HandlerInputUtils {

  public AttributesManager getAttributesManager(HandlerInput handlerInput) {
    return handlerInput.getAttributesManager();
  }

  public RequestEnvelope getRequestEnvelope(HandlerInput handlerInput) {
    return handlerInput.getRequestEnvelope();
  }

  public ResponseBuilder getResponseBuilder(HandlerInput handlerInput) {
    return handlerInput.getResponseBuilder();
  }

  public boolean matches(HandlerInput handlerInput, Predicate<HandlerInput> predicate) {
    return handlerInput.matches(predicate);
  }
}
