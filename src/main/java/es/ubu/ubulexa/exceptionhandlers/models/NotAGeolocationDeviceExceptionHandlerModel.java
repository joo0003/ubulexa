package es.ubu.ubulexa.exceptionhandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.Constants;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class NotAGeolocationDeviceExceptionHandlerModel extends AbstractExceptionHandlerModel {

  private static final String SPEECH_TEXT_KEY = "NotAGeolocationDeviceExceptionHandlerModel.speechtext";

  public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
    Props dictionary = dictionaryPropsResolver.resolve(handlerInput);

    String speechText = dictionary.getValue(SPEECH_TEXT_KEY);
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .build();
  }
}