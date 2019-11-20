package es.ubu.ubulexa.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.Constants;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class StopIntentHandlerModel extends AbstractIntentHandlerModel {

  public Optional<Response> handle(HandlerInput handlerInput) {
    Props dictionary = dictionaryPropsResolver.resolve(handlerInput);
    String speechText = dictionary.getValue(Constants.DICT_KEY_GOODBYE);
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .build();
  }
}