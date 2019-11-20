package es.ubu.ubulexa.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.Constants;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class LaunchRequestHandlerModel extends AbstractRequestHandlerModel {

  public Optional<Response> handle(HandlerInput handlerInput) {
    String speechText = getSpeechText(handlerInput);
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .build();
  }

  private String getSpeechText(HandlerInput handlerInput) {
    Props dictionary = dictionaryPropsResolver.resolve(handlerInput);
    return dictionary.getValue(Constants.DICT_KEY_NO_STATUES_NEAR_BY);
  }
}