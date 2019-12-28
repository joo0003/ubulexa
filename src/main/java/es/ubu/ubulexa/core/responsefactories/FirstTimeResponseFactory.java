package es.ubu.ubulexa.core.responsefactories;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.tools.voicetransformers.VoiceTransformer;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.props.Props;

@PetiteBean
public class FirstTimeResponseFactory extends AbstractResponseFactory {

  @Override
  public Optional<Response> build(HandlerInput handlerInput) {
    ResponseBuilder builder = responseBuilder(handlerInput);

    VoiceTransformer voiceTransformer = resolveVoiceTransformer(handlerInput);

    Props dictionary = resolveDictionary(handlerInput);

    String speechText = dictionary.getValue(
        Constants.FIRST_TIME_SPEECH_TEXT_KEY
    );
    speechText = voiceTransformer.transform(speechText);

    builder.withSpeech(speechText);

    builder.withShouldEndSession(false);

    return builder.build();
  }
}
