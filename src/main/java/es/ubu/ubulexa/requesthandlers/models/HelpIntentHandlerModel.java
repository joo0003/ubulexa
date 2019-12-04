package es.ubu.ubulexa.requesthandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class HelpIntentHandlerModel extends AbstractIntentHandlerModel {

  private static final String SPEECH_TEXT_KEY = "HelpIntentHandlerModel.speechtext";

  public Optional<Response> handle(HandlerInput handlerInput) {
    Props dictionary = dictionaryPropsResolver.resolve(handlerInput);
    String speechText = dictionary.getValue(SPEECH_TEXT_KEY);
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .build();
  }
}